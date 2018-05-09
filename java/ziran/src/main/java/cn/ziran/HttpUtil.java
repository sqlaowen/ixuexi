package cn.ziran;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;

public class HttpUtil {

  public static void main(String[] args) {
    Map<String, Object> map = new HashMap<>();
    map.put("k1", "v1");
    map.put("k2", new Date());
    map.put("k3", 1);
    String xx=httpGetRequest("http://www.ishadowsocks.net/");
    String p = xx.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll("<[^>]*>", "");//去掉网页中带有html语言的标签  
//    System.out.println(httpPostStrRequest("http://ziran.mscte.com/index.aspx?wid=33", map));
//    System.out.println(httpGetRequest("http://ziran.mscte.com/index.aspx?wid=33"));
    System.out.println("------------------------------------------------");
  }

//  controller中读取请求参数
//  try
//  {
//    BufferedReader br = request.getReader();
//    StringBuilder sb = new StringBuilder();
//    String str = "";
//    if ((str = br.readLine()) != "") {
//      sb.append(str);
//    }
//    System.out.println(str);
//  }catch(
//  IOException e)
//  {
//    e.printStackTrace();
//  }

  /**
   * @param url
   * @param map
   * @return
   */
  public static String httpPostBodyRequest(String url, Map<String, Object> map) {
    HttpClient httpclient = HttpClientBuilder.create().build();
    HttpPost httpPost = buildHttpPostWithBodyParams(url, map);
    ServiceLoader.load(int.class);
   
    return execInvoke(httpclient, httpPost);

  }

  public static String httpPostStrRequest(String url, Map<String, Object> map) {
    HttpClient httpclient = HttpClientBuilder.create().build();
    HttpPost httpPost = buildHttpPostWithStrParams(url, map);
    return execInvoke(httpclient, httpPost);

  }

  public static String httpGetRequest(String url) {
    HttpClient httpclient = HttpClientBuilder.create().build();
    HttpGet httpGet = new HttpGet(url);
    return execInvoke(httpclient, httpGet);

  }

  private static String execInvoke(HttpClient httpclient, HttpUriRequest httpost) {
    try {
      HttpResponse response = httpclient.execute(httpost);
            
      HttpEntity entity = response.getEntity();
      return EntityUtils.toString(entity, "UTF-8");
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  private static HttpPost buildHttpPostWithBodyParams(String url, Map<String, Object> map) {
    HttpPost httpost = new HttpPost(url);
    if (null != map && map.size() > 0) {
      List<NameValuePair> nvps = new ArrayList<NameValuePair>();
      Set<String> keySet = map.keySet();
      for (String key : keySet) {
        nvps.add(new BasicNameValuePair(key, String.valueOf(map.get(key))));
      }
      try {
        httpost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }
    }
    return httpost;
  }

  private static HttpPost buildHttpPostWithStrParams(String url, Map<String, Object> map) {
    HttpPost httpost = new HttpPost(url);
    if (null != map && map.size() > 0) {
      String json = JSON.toJSONString(map);
      httpost.setEntity(new StringEntity(json, "UTF-8"));
    }
    return httpost;
  }
  
}
