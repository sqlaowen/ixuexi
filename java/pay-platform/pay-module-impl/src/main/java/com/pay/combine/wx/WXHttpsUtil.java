package com.pay.combine.wx;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WXHttpsUtil {

  private static Logger log = LoggerFactory.getLogger(WXHttpsUtil.class);

  // 连接超时时间，默认20秒
  private static int socketTimeout = 20000;

  // 传输超时时间，默认40秒
  private static int connectTimeout = 40000;
  
  // 请求器的配置
  private static RequestConfig requestConfig;

  // HTTP请求器
  private static CloseableHttpClient httpClient;

  private static void init(boolean requireCert,String certPath, String certPassword) throws KeyStoreException, IOException, KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException{
    // Trust own CA and all self-signed certs
    SSLContext sslcontext =null;
    if(requireCert){
      KeyStore keyStore = KeyStore.getInstance("PKCS12");
      FileInputStream instream = new FileInputStream(new File(certPath));// 加载本地的证书进行https加密传输
      try {
        keyStore.load(instream, certPassword.toCharArray());// 设置证书密码
      } catch (CertificateException e) {
        e.printStackTrace();
      } catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
      } finally {
        instream.close();
      }
      sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, certPassword.toCharArray()).build();
    } else{
      sslcontext = SSLContexts.custom().loadKeyMaterial(null, null).build();
    }

    // Allow TLSv1 protocol only
    SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] {"TLSv1"}, null,SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);

    httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();

    // 根据默认超时限制初始化requestConfig
    requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
  }

  /**
   * 通过Https往API post xml数据
   *
   * @param url API地址
   * @param xmlStr 要提交的XML数据对象
   * @param requireCert 是否需要证书
   * @param certPath 证书路径
   * @param certPassword 证书密钥(当需要证书时此字段必传)
   * @return API回包的实际数据
   * @throws IOException
   * @throws KeyStoreException
   * @throws UnrecoverableKeyException
   * @throws NoSuchAlgorithmException
   * @throws KeyManagementException
   */
  public static String sendPost(String url, String xmlStr, boolean requireCert,String certPath, String certPassword)
      throws IOException, KeyStoreException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyManagementException {

    init(requireCert, certPath, certPassword);
    
    String result = null;

    HttpPost httpPost = new HttpPost(url);

    log.debug("微信请求url:{},POST数据:{}",url,xmlStr);
    
    // 得指明使用UTF-8编码，否则到API服务器XML的中文不能被成功识别
    StringEntity postEntity = new StringEntity(xmlStr, "UTF-8");
    httpPost.addHeader("Content-Type", "text/xml");
    httpPost.setEntity(postEntity);

    // 设置请求器的配置
    httpPost.setConfig(requestConfig);
    log.debug("wx pay executing request" + httpPost.getRequestLine());
    try {
      HttpResponse response = httpClient.execute(httpPost);

      org.apache.http.HttpEntity entity = response.getEntity();

      result = EntityUtils.toString(entity, "UTF-8");

    } catch (ConnectionPoolTimeoutException e) {
      log.error("http get throw ConnectionPoolTimeoutException(wait time out)");

    } catch (ConnectTimeoutException e) {
      log.error("http get throw ConnectTimeoutException");

    } catch (SocketTimeoutException e) {
      log.error("http get throw SocketTimeoutException");

    } catch (Exception e) {
      log.error("http get throw Exception");

    } finally {
      httpPost.abort();
    }

    return result;
  }

  /**
   * 设置连接超时时间
   *
   * @param socketTimeout 连接时长，默认10秒
   */
  public void setSocketTimeout(int socketTimeout) {
    WXHttpsUtil.socketTimeout = socketTimeout;
    resetRequestConfig();
  }

  /**
   * 设置传输超时时间
   *
   * @param connectTimeout 传输时长，默认30秒
   */
  public void setConnectTimeout(int connectTimeout) {
    WXHttpsUtil.connectTimeout = connectTimeout;
    resetRequestConfig();
  }

  private void resetRequestConfig() {
    requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
  }
}
