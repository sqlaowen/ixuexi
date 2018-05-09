package com.x2016.model;

import java.io.Serializable;

/**
 * AccessToken
 *
 */
public class AccessToken implements Serializable {

  private static final long serialVersionUID = 7956676534450184698L;
  private String accessToken;// 获取到的凭证
  private int expiresin;// 凭证有效时间，单位：秒

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public int getExpiresin() {
    return expiresin;
  }

  public void setExpiresin(int expiresin) {
    this.expiresin = expiresin;
  }

  @Override
  public String toString() {
    return "AccessToken [accessToken=" + accessToken + ", expiresin=" + expiresin + "]";
  }

}
