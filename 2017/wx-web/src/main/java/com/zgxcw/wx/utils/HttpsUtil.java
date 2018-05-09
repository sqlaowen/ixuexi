package com.zgxcw.wx.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 访问网络用到的工具类
 */
public class HttpsUtil {

  private static Logger log = LoggerFactory.getLogger(HttpsUtil.class);
  
  /**
   * 发起Https请求
   * 
   * @param reqUrl 请求的URL地址
   * @param requestMethod 请求方式:GET,POST
   * @param dataStr 请求参数
   * @return String
   */
  public static String getHttpsResponse(String reqUrl, String requestMethod, String dataStr) {
    log.debug("https请求地址:{},请求方式:{},上载数据:{}",reqUrl,requestMethod,dataStr);
    String resultData = "";
    try {
      URL url = new URL(reqUrl);
      HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
      TrustManager[] tm = {xtm};

      SSLContext ctx = SSLContext.getInstance("TLS");
      ctx.init(null, tm, null);

      con.setSSLSocketFactory(ctx.getSocketFactory());
      con.setHostnameVerifier(new HostnameVerifier() {
        @Override
        public boolean verify(String arg0, SSLSession arg1) {
          return true;
        }
      });

      con.setDoOutput(true); // 允许输出流，即允许上传
      con.setDoInput(true); // 允许输入流，即允许下载
      con.setUseCaches(false); // 不使用缓冲
      if (StringUtils.isNotBlank(requestMethod)) {
        con.setRequestMethod(requestMethod); // 使用指定的方式
      } else {
        con.setRequestMethod("GET"); // 使用get请求
      }

      // 请求参数
      if (StringUtils.isNotBlank(dataStr)) {
        con.setRequestProperty("Content-Length", String.valueOf(dataStr.getBytes().length));
        con.getOutputStream().write(dataStr.getBytes("UTF-8"));
      }

      InputStream is = con.getInputStream(); // 获取输入流，此时才真正建立链接
      InputStreamReader isr = new InputStreamReader(is, Charset.forName("UTF-8"));
      BufferedReader bufferReader = new BufferedReader(isr);
      String inputLine;
      while ((inputLine = bufferReader.readLine()) != null) {
        resultData += inputLine;
      }
      //
      if (null != bufferReader) {
        bufferReader.close();
      }
      if (null != isr) {
        isr.close();
      }
      if (null != is) {
        is.close();
      }
    } catch (Exception e) {
      log.error("https请求异常,对应请求地址:{},请求方式:{},上载数据:{},异常信息:{}...", reqUrl, requestMethod, dataStr, e.getMessage());
      e.printStackTrace();
    }
    return resultData;
  }

  static X509TrustManager xtm = new X509TrustManager() {
    @Override
    public X509Certificate[] getAcceptedIssuers() {
      return null;
    }

    @Override
    public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}

    @Override
    public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}
  };
}
