package com.jd.test;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by wenshiwei on 2017/7/21.
 */
public class SpiderTest {

    private static Logger log = LoggerFactory.getLogger(SpiderTest.class);

    public static void main(String[] args) throws Exception {
        String loginUrl = "http://fin.soa.pop.jd.com/manager/doLogin.action";
        Map params = new HashMap();
        params.put("username", "root");
        params.put("password", "123456");
        Map map = httpPost(loginUrl, params, null, null);
        if ("false".equals(map.get("isSuccess"))) {
            log.error("=======>>登录失败...");
            return;
        }
        Header header = (Header) map.get("header");
        System.out.println(header);
    }

    /**
     * get登录
     *
     * @param url
     * @param headerList
     * @param charset
     * @return
     */
    public static Map<String, Object> httpGet(String url, List<Header> headerList, String charset) throws Exception {
        Map<String, Object> map = new HashMap<>();
        if (StringUtils.isBlank(charset)) {
            charset = "UTF-8";
        }

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpRequestBase httpRequest = new HttpGet(url);
        if (null != headerList) {
            for (Header header : headerList) {
                httpRequest.addHeader(header);
            }
        }
        CloseableHttpResponse httpResponse = httpclient.execute(httpRequest);
        if (HttpStatus.SC_MOVED_TEMPORARILY == httpResponse.getStatusLine().getStatusCode()) { //302跳转
            String location = httpResponse.getFirstHeader("Location").getValue();
            log.warn("GET 302跳转地址:{}, 来源地址:{}", location, httpRequest.getRequestLine().getUri());

        } else if (HttpStatus.SC_OK == httpResponse.getStatusLine().getStatusCode()) {
            String html = EntityUtils.toString(httpResponse.getEntity(), charset);
            map.put("html", html);
        }
        List<Header> headers = new ArrayList<>();
        if (null != httpResponse.getFirstHeader("Set-Cookie")) {
            headers.add(new BasicHeader("Cookie", httpResponse.getFirstHeader("Set-Cookie").getValue()));
        }
        map.put("headerList", headers);
        if (null != httpclient) {
            httpclient.close();
        }
        return map;
    }

    public static Map httpPost(String url, Map<String, String> params, List<Header> headerList, String charset) throws Exception {
        Map map = new HashMap();
        map.put("isSuccess", false);

        if (StringUtils.isBlank(charset)) {
            charset = "UTF-8";
        }
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httPost = new HttpPost(url);
        if (null != headerList) {
            for (Header header : headerList) {
                httPost.addHeader(header);
            }
        }
        if (null != params && 0 < params.size()) {
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            Set<String> keySet = params.keySet();
            for (String key : keySet) {
                nvps.add(new BasicNameValuePair(key, String.valueOf(params.get(key))));
            }
            httPost.setEntity(new UrlEncodedFormEntity(nvps, charset));

        }
        CloseableHttpResponse httpResponse = httpclient.execute(httPost);

        if (HttpStatus.SC_OK == httpResponse.getStatusLine().getStatusCode()) {
            map.put("isSuccess", true);
        }
        if (null != httpResponse.getFirstHeader("Set-Cookie")) {
            Header header = new BasicHeader("Cookie", httpResponse.getFirstHeader("Set-Cookie").getValue());
            map.put("header", header);
        }


        if (null != httpclient) {
            httpclient.close();
        }
        return map;
    }

}
