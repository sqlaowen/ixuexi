package com.laowen.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HttpUtil {

    private static Logger log = LoggerFactory.getLogger(HttpUtil.class);

    /**
     * post 请求
     *
     * @param url
     * @param params
     * @param headers
     * @return
     */
    public static String httpPost(String url, Map<String, Object> params, Map<String, String> headers) {
        return httpPost(url, params, headers, "UTF-8");
    }

    /**
     * post 请求
     *
     * @param url
     * @param params
     * @param headers
     * @param charset
     * @return
     */
    public static String httpPost(String url, Map<String, Object> params, Map<String, String> headers, String charset) {

        if (StringUtils.isBlank(charset)) {
            charset = "UTF-8";
        }
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 请求参数设置
        // httpclient = HttpClients.custom().setDefaultRequestConfig(RequestConfig.custom().setConnectionRequestTimeout(2000).setConnectTimeout(2000).setSocketTimeout(2000).build()).build();
        HttpPost httpPost = buildHttpPostWithBodyParams(url, params, charset);
        String revStr = invokeReq(httpclient, httpPost, headers, charset);
        try {
            if (null != httpclient) {
                httpclient.close();
            }
        } catch (IOException e) {
            log.error("资源释放异常,对应url:{}, 对应异常:{}", httpPost.getRequestLine().getUri(), e.getMessage());
        }
        return revStr;
    }

    /**
     * post 请求
     *
     * @param url
     * @param params  格式 : key1=value1&key2=value2
     * @param headers
     * @param charset
     * @return
     */
    public static String httpPostString(String url, String params, Map<String, String> headers, String charset) {

        if (StringUtils.isBlank(charset)) {
            charset = "UTF-8";
        }
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        if (StringUtils.isNotBlank(params)) {
            httpPost.setEntity(new StringEntity(params, charset));
        }
        String revStr = invokeReq(httpclient, httpPost, headers, charset);
        try {
            if (null != httpclient) {
                httpclient.close();
            }
        } catch (IOException e) {
            log.error("资源释放异常,对应url:{}, 对应异常:{}", httpPost.getRequestLine().getUri(), e.getMessage());
        }
        return revStr;
    }

    /**
     * get 请求
     *
     * @param url
     * @param headers
     * @return
     */
    public static String httpGet(String url, Map<String, String> headers) {
        return httpGet(url, headers, "UTF-8");
    }

    /**
     * get 请求
     *
     * @param url
     * @param headers
     * @param charset
     * @return
     */
    public static String httpGet(String url, Map<String, String> headers, String charset) {

        if (StringUtils.isBlank(charset)) {
            charset = "UTF-8";
        }
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        String rev = invokeReq(httpclient, httpGet, headers, charset);
        try {
            if (null != httpclient) {
                httpclient.close();
            }
        } catch (IOException e) {
            log.error("资源释放异常,对应url:{}, 对应异常:{}", httpGet.getRequestLine().getUri(), e.getMessage());
        }
        return rev;
    }


    private static PoolingHttpClientConnectionManager connMgr;
    private static RequestConfig requestConfig;
    private static final int MAX_TIMEOUT = 7000;

    static {
        // 设置连接池
        connMgr = new PoolingHttpClientConnectionManager();
        // 设置连接池大小
        connMgr.setMaxTotal(100);
        connMgr.setDefaultMaxPerRoute(connMgr.getMaxTotal());

        RequestConfig.Builder configBuilder = RequestConfig.custom();
        // 设置连接超时
        configBuilder.setConnectTimeout(MAX_TIMEOUT);
        // 设置读取超时
        configBuilder.setSocketTimeout(MAX_TIMEOUT);
        // 设置从连接池获取连接实例的超时
        configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);
        // 在提交请求之前 测试连接是否可用
        //configBuilder.setStaleConnectionCheckEnabled(true);
        requestConfig = configBuilder.build();
    }

    /**
     * 发送 SSL POST 请求（HTTPS），K-V形式
     *
     * @param url           API接口URL
     * @param params        参数map
     * @param requestMethod 请求方式:GET, POST
     * @param charset       字符编码
     * @return
     */
    public static String sslPost(String url, String requestMethod, Map<String, Object> params, String charset) {
        if (StringUtils.isBlank(charset)) {
            charset = "UTF-8";
        }

        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
        HttpRequestBase httpRequest = null;
        if (StringUtils.isBlank(requestMethod) || "GET".equalsIgnoreCase(requestMethod)) {
            httpRequest = new HttpGet(url);
        } else if ("POST".equalsIgnoreCase(requestMethod)) {
            httpRequest = new HttpPost(url);
        }
        CloseableHttpResponse httpResponse = null;

        try {
            httpRequest.setConfig(requestConfig);
            if (httpRequest instanceof HttpPost && null != params && 0 < params.size()) {
                List<NameValuePair> pairList = new ArrayList<NameValuePair>(params.size());
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
                    pairList.add(pair);
                }
                ((HttpPost) httpRequest).setEntity(new UrlEncodedFormEntity(pairList, charset));
            }
            httpResponse = httpClient.execute(httpRequest);
            if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                log.error("请求异常, 返回状态码:{} ,对应请求url:{}", httpResponse.getStatusLine().getStatusCode(), httpRequest.getRequestLine().getUri());
                return null;
            }
            HttpEntity entity = httpResponse.getEntity();
            if (entity == null) {
                log.error("响应为null, 对应请求url:{}", httpRequest.getRequestLine().getUri());
                return null;
            }
            return EntityUtils.toString(entity, charset);
        } catch (Exception e) {
            log.error("请求异常, 对应请求url:{}, 异常提示:{}", httpRequest.getRequestLine().getUri(), e.getMessage());
        } finally {
            if (httpResponse != null) {
                try {
                    EntityUtils.consume(httpResponse.getEntity());
                } catch (IOException e) {
                    log.error("释放请求异常, 对应请求url:{}, 异常提示:{}", httpRequest.getRequestLine().getUri(), e.getMessage());
                }
            }
        }
        return null;
    }

    /**
     * 发送 SSL POST 请求（HTTPS），K-V形式
     *
     * @param url           API接口URL
     * @param dataStr       参数string
     * @param requestMethod 请求方式:GET, POST
     * @param charset       字符编码
     * @return
     */
    public static String sslPostString(String url, String requestMethod, String dataStr, String charset) {
        if (StringUtils.isBlank(charset)) {
            charset = "UTF-8";
        }

        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
        HttpRequestBase httpRequest = null;
        if (StringUtils.isBlank(requestMethod) || "GET".equalsIgnoreCase(requestMethod)) {
            httpRequest = new HttpGet(url);
        } else if ("POST".equalsIgnoreCase(requestMethod)) {
            httpRequest = new HttpPost(url);
        }
        CloseableHttpResponse httpResponse = null;

        try {
            httpRequest.setConfig(requestConfig);
            if (httpRequest instanceof HttpPost && StringUtils.isNotBlank(dataStr)) {
                ((HttpPost) httpRequest).setEntity(new StringEntity(dataStr, charset));
            }
            httpResponse = httpClient.execute(httpRequest);
            if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                log.error("请求异常, 返回状态码:{} ,对应请求url:{}", httpResponse.getStatusLine().getStatusCode(), httpRequest.getRequestLine().getUri());
                return null;
            }
            HttpEntity entity = httpResponse.getEntity();
            if (entity == null) {
                log.error("响应为null, 对应请求url:{}", httpRequest.getRequestLine().getUri());
                return null;
            }
            return EntityUtils.toString(entity, charset);
        } catch (Exception e) {
            log.error("请求异常, 对应请求url:{}, 异常提示:{}", httpRequest.getRequestLine().getUri(), e.getMessage());
        } finally {
            if (httpResponse != null) {
                try {
                    EntityUtils.consume(httpResponse.getEntity());
                } catch (IOException e) {
                    log.error("释放请求异常, 对应请求url:{}, 异常提示:{}", httpRequest.getRequestLine().getUri(), e.getMessage());
                }
            }
        }
        return null;
    }

    /**
     * 创建SSL安全连接
     *
     * @return
     */
    private static SSLConnectionSocketFactory createSSLConnSocketFactory() {
        SSLConnectionSocketFactory sslsf = null;
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {

                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            sslsf = new SSLConnectionSocketFactory(sslContext, new X509HostnameVerifier() {

                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }

                public void verify(String host, SSLSocket ssl) throws IOException {
                }

                public void verify(String host, X509Certificate cert) throws SSLException {
                }

                public void verify(String host, String[] cns, String[] subjectAlts) throws SSLException {
                }
            });
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return sslsf;
    }

    //
    private static String invokeReq(CloseableHttpClient httpclient, HttpRequestBase httpRequest, Map<String, String> headers, String charset) {

        CloseableHttpResponse httpResponse = null;

        try {
            if (null != headers && 0 < headers.size()) {
                headers.keySet().forEach(key -> {
                    httpRequest.setHeader(key, headers.get(key));
                });
            }
            httpResponse = httpclient.execute(httpRequest);
            if (HttpStatus.SC_OK != httpResponse.getStatusLine().getStatusCode()) {
                log.error("请求异常, 返回状态码:{} ,对应请求:{}", httpResponse.getStatusLine().getStatusCode(), httpRequest.getRequestLine().getUri());
                return null;
            }
            return EntityUtils.toString(httpResponse.getEntity(), charset);

        } catch (IOException e) {
            log.error("执行请求失败, 对应url:{} \r对应异常:{} \r对应堆栈:{}", httpRequest.getRequestLine().getUri(), e.getMessage(), e.getStackTrace());
        } finally {
            try {
                if (null != httpclient) {
                    httpclient.close();
                }
                if (httpResponse != null) {
                    EntityUtils.consume(httpResponse.getEntity());
                }
            } catch (Exception e) {
                log.error("释放资源异常, 对应请求url:{}, 异常提示:{}", httpRequest.getRequestLine().getUri(), e.getMessage());
            }
        }
        return null;

    }

    //
    private static HttpPost buildHttpPostWithBodyParams(String url, Map<String, Object> map, String charset) {
        HttpPost httPost = new HttpPost(url);
        if (null != map && 0 < map.size()) {
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            Set<String> keySet = map.keySet();
            for (String key : keySet) {
                nvps.add(new BasicNameValuePair(key, String.valueOf(map.get(key))));
            }
            try {
                httPost.setEntity(new UrlEncodedFormEntity(nvps, charset));
            } catch (UnsupportedEncodingException e) {
                log.error("设置POST请求body出错,对应body:{}, 对应异常:{}", map, e.getMessage());
            }
        }
        return httPost;
    }

}
