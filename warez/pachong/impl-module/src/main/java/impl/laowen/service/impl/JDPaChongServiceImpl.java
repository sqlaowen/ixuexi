package impl.laowen.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import impl.laowen.bean.ECGoods;
import impl.laowen.bean.ECHistoryPrice;
import impl.laowen.dao.ECGoodsMapper;
import impl.laowen.dao.ECHistoryPriceMapper;
import impl.laowen.enums.ECPlatformEnum;
import impl.laowen.service.ECGoodsService;
import impl.laowen.service.PaChongService;
import impl.laowen.utils.HttpReqUtil;
import impl.laowen.utils.PageRes;
import impl.laowen.utils.RandomGUID;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by iyou on 2016/12/18.
 */
public class JDPaChongServiceImpl implements PaChongService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private Map<String, String> urlMap; //sku大类url列表
    private String skuListCss; //sku列表css
    private String skuIDCss; //sku id css
    private String skuImgCss; // sku img css
    private String skuUrlCss; // sku url css
    private String skuNoteCss; //sku 描述css
    private String pageTotalCss; //总页数
    private String priceUrl;// 价格地址
    private ECGoodsService ecGoodsService;
    private ECGoodsMapper ecGoodsMapper;
    private ECHistoryPriceMapper ecHistoryPriceMapper;
    private Integer threadPoolSize;

    //region 注入

    public void setUrlMap(Map<String, String> urlMap) {
        this.urlMap = urlMap;
    }

    public void setSkuListCss(String skuListCss) {
        this.skuListCss = skuListCss;
    }

    public void setSkuIDCss(String skuIDCss) {
        this.skuIDCss = skuIDCss;
    }

    public void setSkuImgCss(String skuImgCss) {
        this.skuImgCss = skuImgCss;
    }

    public void setSkuUrlCss(String skuUrlCss) {
        this.skuUrlCss = skuUrlCss;
    }

    public void setSkuNoteCss(String skuNoteCss) {
        this.skuNoteCss = skuNoteCss;
    }

    public void setPageTotalCss(String pageTotalCss) {
        this.pageTotalCss = pageTotalCss;
    }

    public void setPriceUrl(String priceUrl) {
        this.priceUrl = priceUrl;
    }

    public void setEcGoodsService(ECGoodsService ecGoodsService) {
        this.ecGoodsService = ecGoodsService;
    }

    public void setEcGoodsMapper(ECGoodsMapper ecGoodsMapper) {
        this.ecGoodsMapper = ecGoodsMapper;
    }

    public void setEcHistoryPriceMapper(ECHistoryPriceMapper ecHistoryPriceMapper) {
        this.ecHistoryPriceMapper = ecHistoryPriceMapper;
    }

    public void setThreadPoolSize(Integer threadPoolSize) {
        this.threadPoolSize = threadPoolSize;
    }

    //endregion

    @Override
    public void grabSku() {
        if (null == urlMap || 0 == urlMap.size()) {
            return;
        }
        ExecutorService executorService = Executors.newFixedThreadPool(threadPoolSize);
        List<Callable<Integer>> callableList = new ArrayList<>();
        urlMap.values().forEach(url -> {
            callableList.add(() -> {
                //以下两个配合, 在同一个list页中去重
                //保存某个list中的所有不相同的sku
                Set<String> skuSet = new HashSet<String>();
                //保存所有sku不相同的goods
                Map<String, ECGoods> ecGoodsMap = new HashMap<String, ECGoods>();

                //解析第一页
                Integer totalPage = parseSku(String.format(url + "&page=%s", 1), skuSet, ecGoodsMap);
                //解析剩余页数
                for (int j = 1; j < totalPage; j++) {
                    parseSku(String.format(url + "&page=%s", j + 1), skuSet, ecGoodsMap);
                }

                log.warn("抓取{}商品完毕", url);
                //========保存数据
                return insertBatchSKU(skuSet, ecGoodsMap);
            });
        });

        try {
            executorService.invokeAll(callableList);
            executorService.shutdown();
        } catch (InterruptedException e) {
            log.error("线程被中断异常:{}", e.getMessage());
        }

    }

    @Override
    public void grabPrice() {
        //200一页
        PageRes<ECGoods> pageRes = ecGoodsService.selectList(1, 200);
        log.debug("抓取价格共有 {} 个SKU...", pageRes.getTotal());
        ExecutorService executorService = Executors.newFixedThreadPool(threadPoolSize);
        List<Callable<Integer>> callableList = new ArrayList<>();
        //解析
        for (int i = 0; i < pageRes.getPages(); i++) {

            int pageNum = i + 1;
            log.debug("正在解析第{}页商品价格...", pageNum);
            callableList.add(() -> {
                //每个线程里保存的对象
                Map<String, ECHistoryPrice> ecHistoryPriceMap = new HashMap<>();
                parsePrice(ecGoodsService.selectList(pageNum, 200), ecHistoryPriceMap);
                List<String> goodsIdList = new ArrayList<String>(ecHistoryPriceMap.keySet());
                List<String> existGoodsIdList = ecHistoryPriceMapper.selectBatchByGoodsIdList(goodsIdList);//已存在的
                goodsIdList.removeAll(existGoodsIdList);//取差集
                if (0 == goodsIdList.size()) {
                    return 0;
                }
                List<ECHistoryPrice> ecHistoryPriceList = new ArrayList<ECHistoryPrice>();
                goodsIdList.forEach(x -> {
                    ecHistoryPriceList.add(ecHistoryPriceMap.get(x));
                });
                int affectsInt = ecHistoryPriceMapper.insertBatch(ecHistoryPriceList);
                log.info("新获取 {} 个价格", affectsInt);
                return affectsInt;
            });
        }
        try {
            executorService.invokeAll(callableList);
            executorService.shutdown();
        } catch (InterruptedException e) {
            log.error("线程被中断异常:{}", e.getMessage());
        }
    }

    //region 抓取商品SKU

    //解析商品
    private Integer parseSku(String url, Set<String> skuSet, Map<String, ECGoods> ecGoodsMap) {

        String html = retryHttp(url);
        if (StringUtils.isBlank(html)) {
            log.error("http请求失败, 对应 url:{}", url);
            return 0;
        }
        Document doc = Jsoup.parse(html);
        Integer totalPage = 1;//默认只有一页
        if (0 == doc.select(pageTotalCss).size()) {
            log.warn("页数css选择器没有匹配结果, 对应url:{}...", url);
        } else {
            totalPage = Integer.valueOf(doc.select(pageTotalCss).get(0).text());
        }
        log.debug("正在解析商品list页 url:{}, 共有 {} 页", url, totalPage);

        parseSKUDocument(doc, skuSet, ecGoodsMap);

        return totalPage;
    }

    //解析html, 填充skuSet和ecGoodsMap
    private void parseSKUDocument(Document doc, Set<String> skuSet, Map<String, ECGoods> ecGoodsMap) {
        Elements elements = doc.select(skuListCss);
        if (null == elements) {
            log.error("列表页:{}解析失败...", skuListCss);
            return;
        }

        elements.forEach(x -> {
            String skuId = null, skuImg = null, skuUrl = null;

            //region 过滤条件
            if (0 < x.select(skuIDCss).size()) {
                skuId = x.select(skuIDCss).get(0).attr("data-sku");
            }

            if (0 < x.select(skuImgCss).size()) {
                if (x.select(skuImgCss).get(0).hasAttr("data-lazy-img")) {
                    skuImg = StringUtils.removeStart(x.select(skuImgCss).get(0).attr("data-lazy-img"), "//");
                } else {
                    skuImg = StringUtils.removeStart(x.select(skuImgCss).get(0).attr("src"), "//");
                }
            }
            if (0 < x.select(skuUrlCss).size()) {
                skuUrl = StringUtils.removeStart(x.select(skuUrlCss).get(0).attr("href"), "//");
            }
            if (null == skuId && null != skuUrl) {
                Pattern pattern = Pattern.compile("item\\.jd\\.com\\/(\\d+)\\.html.*");
                Matcher matcher = pattern.matcher(skuUrl);
                if (matcher.find()) {
                    skuId = matcher.group(1);
                }
            }
            if (null == skuId) {
                log.warn("css选择器,skuId:{}, skuUrl:{}; 至少有一个没有匹配结果...", skuId, skuUrl);
                return;//任何一个选取不到时， continue下一个
            }
            //endregion

            if (skuSet.contains(skuId) || ecGoodsMap.keySet().contains(skuId)) {
                return;//已包含sku时,跳过
            }

            skuSet.add(skuId);//加入到set中

            ECGoods ecGoods = new ECGoods();
            ecGoods.setGoodsId(new RandomGUID().toString());
            ecGoods.setPlatformId(ECPlatformEnum.PLATFORM_JD.getId());
            ecGoods.setSkuState(0);
            ecGoods.setCreateTime(new Date());

            ecGoods.setSkuId(skuId);
            ecGoods.setSkuImg(skuImg);
            ecGoods.setSkuUrl(skuUrl);
            if (0 < x.select(skuNoteCss).size()) {
                ecGoods.setSkuNote(new String(x.select(skuNoteCss).get(0).text().trim().getBytes(), Charset.forName("UTF-8")));
            }
            if (0 < x.select(skuIDCss).size()) {
                ecGoods.setSkuVender(x.select(skuIDCss).get(0).attr("venderid"));
                ecGoods.setSkuShop(x.select(skuIDCss).get(0).attr("jdzy_shop_id"));
            }

            ecGoodsMap.put(skuId, ecGoods);//加入到map中
        });
    }


    //保存数据
    private Integer insertBatchSKU(Set<String> skuSet, Map<String, ECGoods> ecGoodsMap) {

        List<String> skuList = new ArrayList<String>() {
            {
                addAll(skuSet);
            }
        };
        List<String> subSkuList = null;//每200条分一页
        int affectsInt = 0;
        List<String> existSkuList = null;//已存在库里的
        for (int i = 0; i < skuList.size(); i += 200) {
            if (i + 200 > skuList.size()) {
                subSkuList = skuList.subList(i, skuList.size());
            } else {
                subSkuList = skuList.subList(i, i + 200);
            }
            existSkuList = ecGoodsMapper.selectBatchSKUListBySKUList(ECPlatformEnum.PLATFORM_JD.getId(), subSkuList);
            subSkuList.removeAll(existSkuList);//求差集
            if (0 == subSkuList.size()) {
                continue;
            }
            List<ECGoods> ecGoodsList = new ArrayList<ECGoods>();
            subSkuList.forEach(x -> {
                if (ecGoodsMap.keySet().contains(x)) {
                    ecGoodsList.add(ecGoodsMap.get(x));
                }
            });
            affectsInt += ecGoodsMapper.insertBatch(ecGoodsList);
        }
        log.info("新增 {} 个SKU", affectsInt);
        return affectsInt;
    }

    //endregion

    //region 抓取价格

    //解析价格, 填充 ecHistoryPriceMap
    private void parsePrice(PageRes<ECGoods> pageRes, Map<String, ECHistoryPrice> ecHistoryPriceMap) {

        pageRes.getList().forEach(x -> {
            String json = retryHttp(String.format(priceUrl, x.getSkuId()));
            if (StringUtils.isBlank(json)) {
                log.error("http请求失败, 对应 url:{}", String.format(priceUrl, x.getSkuId()));
                return;
            }
            JSONArray jsonArray = JSON.parseArray(json);
            JSONObject obj = (JSONObject) jsonArray.get(0);
            ECHistoryPrice ecHistoryPrice = new ECHistoryPrice();
            ecHistoryPrice.setGoodsId(x.getGoodsId());
            ecHistoryPrice.setSkuPrice(obj.get("p").toString());

            if (ecHistoryPrice.getSkuPrice().startsWith("-")) {
                ecHistoryPrice.setPriceState(1);//商品以下架
            } else {
                ecHistoryPrice.setPriceState(0);
            }
            ecHistoryPrice.setPriceId(new RandomGUID().toString());
            ecHistoryPrice.setCreateTime(new Date());

            ecHistoryPriceMap.put(x.getGoodsId(), ecHistoryPrice);
        });
    }
    //endregion

    // http 请求, 如果异常最多重试三次
    private String retryHttp(String url) {
        String html = HttpReqUtil.httpGetRequest(url);
        if (StringUtils.isBlank(html)) {
            for (int i = 0; i < 2; i++) {
                log.error("http请求失败, 执行第 {} 次重试, 对应 url:{}", i + 1, url);
                try {
                    Thread.currentThread().sleep(50);
                } catch (InterruptedException e) {
                    log.error("线程:{} sleep异常:{}", Thread.currentThread(), e.getLocalizedMessage());
                }
                html = HttpReqUtil.httpGetRequest(url);
                if (StringUtils.isNotBlank(html)) {
                    break;
                }
            }
        }
        return html;
    }
}
