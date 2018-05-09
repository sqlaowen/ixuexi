package com.x2016.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 获取AccessToken 单独开启一个线程,每7000秒获取一次,如果失败则3秒获取一次
 */
public class AccessTokenThread implements Runnable {

  private Logger log = LoggerFactory.getLogger(getClass());
  
  public static AccessToken accessToken = null;

  @Override
  public void run() {
    while (true) {
      try {
        accessToken = this.getAccessToken();
        if (null != accessToken) {
          log.debug("当前AccessToken:{},准备休眠7000秒...", accessToken);
          Thread.sleep(7000 * 1000); // 获取到access_token 休眠7000秒
        } else {
          log.debug("获取AccessToken失败,准备休眠3秒...");
          Thread.sleep(3 * 1000); // 获取的access_token为空 休眠3秒
        }
      } catch (Exception e) {
        log.error("获取AccessToken异常:{},准备休眠3秒...", e.getMessage());
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
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String d = sdf.format(new Date());
    AccessToken token = new AccessToken();
    token.setAccessToken(d);
    return token;
  }

}

