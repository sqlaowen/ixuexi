package com.x2016.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.x2016.model.AccessToken;

/**
 * 获取AccessToken
 * 单独开启一个线程,每7000秒获取一次,如果失败则3秒获取一次
 */
public class AccessTokenThread implements Runnable {

  private Logger log = LoggerFactory.getLogger(getClass());
  String appId = "wx55321f628b4acc0a";
  String appSecret = "c8dd71dc1eca577615cf8d0952314e1a";
  public static AccessToken accessToken = null;
  
  @Override
  public void run() {
    while (true) {
      try {
        accessToken = this.getAccessToken();
        if (null != accessToken) {
          log.debug("当前AccessToken:{},准备休眠7000秒...",accessToken);
          Thread.sleep(7000 * 1000); // 获取到access_token 休眠7000秒
        } else {
          log.debug("获取AccessToken失败,准备休眠3秒...");
          Thread.sleep(3 * 1000); // 获取的access_token为空 休眠3秒
        }
      } catch (Exception e) {
        log.error("获取AccessToken异常:{},准备休眠3秒...",e.getMessage());
        e.printStackTrace();
        try {
          Thread.sleep(3 * 1000); // 发生异常休眠3秒
        } catch (Exception e1) {
          
        }
      }
    }
  }

  /**
   * 获取access_token
   * 
   * @return
   */
  private AccessToken getAccessToken() {
    NetWorkHelper netHelper = new NetWorkHelper();
    String url = String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s",appId, appSecret);
    log.debug("请求微信AccessToken地址:{}...", url);
    String result = netHelper.getHttpsResponse(url, "",null);
    log.debug("请求微信AccessToken返回结果:{},对应[appId:{},appSecret:{}]...", result,appId,appSecret);
    AccessToken token =null;
    if(StringUtils.isNotBlank(result)){
      JSONObject json = JSON.parseObject(result);
      token = new AccessToken();
      token.setAccessToken(json.getString("access_token"));
      token.setExpiresin(json.getInteger("expires_in"));
    }
    return token;
  }

}

