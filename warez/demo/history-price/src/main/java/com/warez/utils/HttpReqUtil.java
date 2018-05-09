package com.warez.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HttpReqUtil {

    private static Logger log = LoggerFactory.getLogger(HttpReqUtil.class);

    /**
     * post 请求
     *
     * @param url
     * @param map
     * @return
     */
    public static String httpPostRequest(String url, Map<String, Object> map) {
        return httpPostRequest(url, map, "UTF-8");
    }

    /**
     * post 请求
     *
     * @param url
     * @param map
     * @param charset
     * @return
     */
    public static String httpPostRequest(String url, Map<String, Object> map, String charset) {

        if (log.isDebugEnabled()) {
            log.debug("POST请求地址:{}, 请求参数:{}", url, map);
        }
        if (StringUtils.isBlank(charset)) {
            charset = "UTF-8";
        }
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 请求参数设置
        // httpclient = HttpClients.custom().setDefaultRequestConfig(RequestConfig.custom().setConnectionRequestTimeout(2000).setConnectTimeout(2000).setSocketTimeout(2000).build()).build();
        HttpPost httpPost = buildHttpPostWithBodyParams(url, map, charset);
        String revStr = invokeReq(httpclient, httpPost, charset);
        try {
            if (null != httpclient) {
                httpclient.close();
            }
        } catch (IOException e) {
            log.error("资源释放异常,对应请求:{}, 对应异常:{}", httpPost.getRequestLine(), e.getMessage());
        }
        return revStr;
    }

    /**
     * get 请求
     *
     * @param url
     * @return
     */
    public static String httpGetRequest(String url) {
        return httpGetRequest(url, "UTF-8");
    }

    /**
     * get 请求
     *
     * @param url
     * @param charset
     * @return
     */
    public static String httpGetRequest(String url, String charset) {
        if (log.isDebugEnabled()) {
            log.debug("GET请求地址:{}", url);
        }
        if (StringUtils.isBlank(charset)) {
            charset = "UTF-8";
        }
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        String revStr = invokeReq(httpclient, httpGet, charset);
        try {
            if (null != httpclient) {
                httpclient.close();
            }
        } catch (IOException e) {
            log.error("资源释放异常,对应请求:{}, 对应异常:{}", httpGet.getRequestLine(), e.getMessage());
        }
        return revStr;
    }

    //
    private static String invokeReq(HttpClient httpclient, HttpUriRequest httpRequest, String charset) {

        try {
            if (log.isDebugEnabled()) {
                log.debug(String.format("执行请求:%s", httpRequest.getRequestLine()));
            }
            HttpResponse httpResponse = httpclient.execute(httpRequest);
            if (HttpStatus.SC_OK != httpResponse.getStatusLine().getStatusCode()) {
                log.error(String.format("请求异常, 返回状态码:%s ,对应请求:%s", httpResponse.getStatusLine().getStatusCode(), httpRequest.getRequestLine()));
                return "";
            }

            return EntityUtils.toString(httpResponse.getEntity(), charset);
        } catch (Exception e) {
            log.error(String.format("执行请求失败:%s \r对应异常:%s \r对应堆栈:%s", httpRequest.getRequestLine(), e.getMessage(), e.getStackTrace()));
            return "";
        }
    }

    //
    private static HttpPost buildHttpPostWithBodyParams(String url, Map<String, Object> map, String charset) {
        HttpPost httpost = new HttpPost(url);
        if (null != map && 0 < map.size()) {
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            Set<String> keySet = map.keySet();
            for (String key : keySet) {
                nvps.add(new BasicNameValuePair(key, String.valueOf(map.get(key))));
            }
            try {
                httpost.setEntity(new UrlEncodedFormEntity(nvps, charset));
            } catch (UnsupportedEncodingException e) {
                log.error("设置POST请求body出错,对应body:{}, 对应异常:{}", map, e.getMessage());
            }
        }
        return httpost;
    }

}
