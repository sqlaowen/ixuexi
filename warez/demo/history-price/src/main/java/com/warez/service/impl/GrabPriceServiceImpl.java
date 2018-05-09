package com.warez.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.warez.bean.ECGoods;
import com.warez.bean.ECHistoryPrice;
import com.warez.enums.ECPlatformEnum;
import com.warez.service.ECGoodsService;
import com.warez.service.ECHistoryPriceService;
import com.warez.service.GrabPriceService;
import com.warez.utils.HttpReqUtil;
import com.warez.utils.PageRes;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by iyou on 2016/12/18.
 */
public class GrabPriceServiceImpl implements GrabPriceService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private List<String> urlList; //sku大类url列表
    private String skuListCss; //sku列表css
    private String skuIDCss; //sku id css
    private String skuImgCss; // sku img css
    private String skuUrlCss; // sku url css
    private String skuNoteCss; //sku 描述css
    private String pageTotalCss; //总页数
    private String priceUrl;// 价格地址
    private ECGoodsService ecGoodsService;
    private ECHistoryPriceService ecHistoryPriceService;

    //region 注入
    public void setUrlList(List<String> urlList) {
        this.urlList = urlList;
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

    public void setEcHistoryPriceService(ECHistoryPriceService ecHistoryPriceService) {
        this.ecHistoryPriceService = ecHistoryPriceService;
    }

    //endregion

    @Override
    public void grabSku() {

        String url = null;
        for (int i = 0; i < urlList.size(); i++) {
            if (!urlList.get(i).startsWith("https://list.jd.com/list.html")) {
                log.error("此程序只解析list页，对应错误url:{}", urlList.get(i));
                continue;
            }
            log.info("正在解析商品list页 url:{}", urlList.get(i));

            //解析第一页
            url = String.format(urlList.get(i) + "&page=%s", 1);
            Integer totalPage = parseSku(url);
            if (0 == totalPage) {
                log.warn("解析商品list页失败, 跳过此商品, 对应请求 url:{}", urlList.get(i));
                continue; //请求失败时, 跳过此商品
            }

            log.info("正在解析商品list页 url:{}, 共有 {} 页", urlList.get(i), totalPage);

            // 重复商品太多, 这里只解析前 8 页, 前几页基本不重复
            if (8 < totalPage) {
                totalPage = 8;
            }

            //解析剩余页数
            for (int j = 1; j < totalPage; j++) {
                url = String.format(urlList.get(i) + "&page=%s", j + 1);
                parseSku(url);
            }
        }
    }

    @Override
    public void grabPrice() {
        PageRes<ECGoods> pageRes = ecGoodsService.selectList(1, 60);

        parsePrice(pageRes); //解析第一页价格

        //解析剩余页数
        for (int i = 1; i < pageRes.getPages(); i++) {
            pageRes = ecGoodsService.selectList(i + 1, 60);
            parsePrice(pageRes);
        }
    }

    //解析价格
    private void parsePrice(PageRes<ECGoods> pageRes) {
        ExecutorService exec = Executors.newFixedThreadPool(20);
        List<Callable<String>> callableList = new ArrayList<>();
        pageRes.getList().forEach(x -> {
            callableList.add(() -> {
                String json = json = retryHttp(String.format(priceUrl, x.getSkuId()));
                if (StringUtils.isBlank(json)) {
                    log.error("http请求失败, 对应 url:{}", String.format(priceUrl, x.getSkuId()));
                    return null;
                }
                JSONArray jsonArray = JSON.parseArray(json);
                JSONObject obj = (JSONObject) jsonArray.get(0);
                ECHistoryPrice ecHistoryPrice = new ECHistoryPrice();
                ecHistoryPrice.setGoodsId(x.getGoodsId());
                ecHistoryPrice.setSkuPrice(obj.get("p").toString());
                return ecHistoryPriceService.insertOne(ecHistoryPrice);
            });
        });
        try {
            exec.invokeAll(callableList);
            exec.shutdown();
        } catch (InterruptedException e) {
            log.error("线程被中断异常:{}", e.getMessage());
        }
        //每解析一页线程休息2秒
        try {
            log.info("于{},解析完成一页价格, 休息一下...", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
            Thread.sleep(500);
        } catch (InterruptedException e) {
            log.error("线程sleep异常：" + e.getLocalizedMessage());
        }
    }

    //解析商品
    private Integer parseSku(String url) {
        String html = retryHttp(url);
        if (StringUtils.isBlank(html)) {
            log.error("http请求失败, 对应 url:{}", url);
            return 0;
        }
        Document doc = Jsoup.parse(html);
        parseDocument(doc);
        if (0 == doc.select(pageTotalCss).size()) {
            log.warn("页数css选择器没有匹配结果...");
            return 0;
        }
        return Integer.valueOf(doc.select(pageTotalCss).get(0).text());
    }

    // http 请求, 如果异常最多重试三次
    private String retryHttp(String url) {
        String html = HttpReqUtil.httpGetRequest(url);
        if (StringUtils.isBlank(html)) {
            for (int i = 0; i < 3; i++) {
                log.error("http请求失败, 执行第 {} 次重试, 对应 url:{}", i + 1, url);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    log.error("线程sleep异常：" + e.getLocalizedMessage());
                }
                html = HttpReqUtil.httpGetRequest(url);
                if (StringUtils.isNotBlank(html)) {
                    break;
                }
            }
        }
        return html;
    }

    //解析html
    private void parseDocument(Document doc) {
        Elements elements = doc.select(skuListCss);
        if (null == elements) {
            log.error("列表页:{}解析失败...", skuListCss);
            return;
        }
        ExecutorService exec = Executors.newFixedThreadPool(20);
        List<Callable<String>> callableList = new ArrayList<>();
        elements.forEach(x -> {
            if (0 == x.select(skuIDCss).size()
                    || 0 == x.select(skuImgCss).size()
                    || 0 == x.select(skuUrlCss).size()
                    || 0 == x.select(skuNoteCss).size()) {
                log.warn("css选择器, 至少有一个没有匹配结果...");
                return;//任何一个选取不到时， continue下一个
            }
            callableList.add(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    ECGoods ecGoods = new ECGoods();
                    ecGoods.setPlatformId(ECPlatformEnum.PLATFORM_JD.getId());
                    ecGoods.setSkuId(x.select(skuIDCss).get(0).attr("data-sku"));
                    if (x.select(skuImgCss).get(0).hasAttr("data-lazy-img")) {
                        ecGoods.setSkuImg(StringUtils.removeStart(x.select(skuImgCss).get(0).attr("data-lazy-img"), "//"));
                    } else {
                        ecGoods.setSkuImg(StringUtils.removeStart(x.select(skuImgCss).get(0).attr("src"), "//"));
                    }
                    ecGoods.setSkuUrl(StringUtils.removeStart(x.select(skuUrlCss).get(0).attr("href"), "//"));
                    ecGoods.setSkuNote(new String(x.select(skuNoteCss).get(0).text().trim().getBytes(), Charset.forName("UTF-8")));
                    ecGoods.setSkuVender(x.select(skuIDCss).get(0).attr("venderid"));
                    ecGoods.setSkuShop(x.select(skuIDCss).get(0).attr("jdzy_shop_id"));
                    return ecGoodsService.insertOne(ecGoods);
                }
            });
        });
        try {
            exec.invokeAll(callableList);
            exec.shutdown();
        } catch (InterruptedException e) {
            log.error("线程被中断异常:{}", e.getMessage());
        }
        //每解析一页线程休息2秒
        try {
            log.info("于{},解析完成一页商品, 休息一下...", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
            Thread.sleep(2 * 1000);
        } catch (InterruptedException e) {
            log.error("线程sleep异常：" + e.getLocalizedMessage());
        }
    }
}
