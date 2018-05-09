package cn.ziran;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;

public class HttpUtil2 {

  public static String postMap(String url, Map<String, Object> params) throws ParseException,
      IOException {
    HttpClient httpclient = new DefaultHttpClient();
    String body = null;
    try {
      HttpPost post = postFormMap(url, params);
      body = invoke(httpclient, post);
    } finally {
      httpclient.getConnectionManager().shutdown();
    }
    return body;
  }

  public static String postStr(String url, Map<String, Object> params) throws ParseException,
      IOException {
    HttpClient httpclient = new DefaultHttpClient();
    String body = null;
    try {
      HttpPost post = postFormStr(url, params);
      body = invoke(httpclient, post);
    } finally {
      httpclient.getConnectionManager().shutdown();
    }
    return body;
  }

  public static String get(String url) throws ParseException, IOException {
    HttpClient httpclient = new DefaultHttpClient();
    String body = null;
    try {
      HttpGet get = new HttpGet(url);
      body = invoke(httpclient, get);
    } finally {
      httpclient.getConnectionManager().shutdown();
    }
    return body;
  }

  private static String invoke(HttpClient httpclient, HttpUriRequest httpost)
      throws ParseException, IOException {
    HttpResponse response = httpclient.execute(httpost);
    HttpEntity entity = response.getEntity();
    return EntityUtils.toString(entity, "UTF-8");
  }

  private static HttpPost postFormMap(String url, Map<String, Object> params)
      throws UnsupportedEncodingException {
    HttpPost httpost = new HttpPost(url);
    if (null != params) {
      List<NameValuePair> nvps = new ArrayList<NameValuePair>();
      Set<String> keySet = params.keySet();
      for (String key : keySet) {
        nvps.add(new BasicNameValuePair(key, String.valueOf(params.get(key))));
      }
      httpost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
    }
    return httpost;
  }

  private static HttpPost postFormStr(String url, Map<String, Object> params)
      throws UnsupportedEncodingException {
    HttpPost httpost = new HttpPost(url);
    if(null != params){
      String json = JSON.toJSONString(params);
      httpost.setEntity(new StringEntity(json, "UTF-8"));
    }
    return httpost;
  }
}
