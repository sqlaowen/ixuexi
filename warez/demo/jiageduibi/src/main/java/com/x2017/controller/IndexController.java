package com.x2017.controller;

import com.x2017.util.HttpReqUtil;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;

/**
 * Created by iyou on 2017/2/4.
 */

@Controller
public class IndexController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @ResponseBody
    @RequestMapping(value = "/index")
    public PriceData getPriceData(String url) {
        PriceData priceData = new PriceData();
        if (StringUtils.isBlank(url)) {
            log.error("请求参数为空...");
            return priceData;
        }
        url = "http://www.gwdang.com/trend?url=" + url;
        String html = retryHttp(url, "GBK");
        if (StringUtils.isBlank(html)) {
            log.error("请求页面未响应，对应url:{}", url);
            return priceData;
        }
        Document doc = Jsoup.parse(html);

        String title = "", dp_ids = "", dp_id = "";
        if (0 < doc.select("#prodct_title").size()) {
            title = doc.select("#prodct_title").get(0).text();
        }
        if (0 < doc.select("#big_price_chart").size()) {
            dp_ids = doc.select("#big_price_chart").get(0).attr("dp_ids");
            dp_id = doc.select("#big_price_chart").get(0).attr("dp_id");
        }
        if (StringUtils.isBlank(title)) {
            log.warn("未能解析出title元素");
        }
        if (StringUtils.isBlank(dp_ids)) {
            log.warn("未能解析出dp_ids元素");
        }
        if (StringUtils.isBlank(dp_id)) {
            log.error("未能解析出一个个必要元素，dp_id:{}", dp_id);
            return priceData;
        }

        url = String.format("http://www.gwdang.com/app/price_trend/?dp_ids=%s&dp_id=%s&days=90", dp_ids, dp_id);
        String txt = retryHttp(url, "GBK");
        if (StringUtils.isBlank(txt)) {
            log.error("请求JSON未响应，对应url:{}", url);
            return priceData;
        }

        priceData.setTitle(title);
        priceData.setData(txt.substring(1, txt.length() - 1));
        return priceData;
    }

    // http 请求, 如果异常最多重试三次
    private String retryHttp(String url, String charset) {
        String html = HttpReqUtil.httpGetRequest(url, charset);
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

    public class PriceData implements Serializable {

        private String title;
        private String data;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

    }
}
