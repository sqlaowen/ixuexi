package com.x2016.util;

import java.io.Serializable;

public class AccessToken implements Serializable {
  
  private static final long serialVersionUID = -8901355102119205914L;
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
}
