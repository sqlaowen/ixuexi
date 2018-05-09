package com.zgxcw.service.infrastructure.bean;

import java.io.Serializable;

public class WXGetway implements Serializable {

  private static final long serialVersionUID = -1376411925259677457L;

  public WXGetway() {}

  private String wxId;

  private String wxName;

  private String appId;

  private String appSecret;

  private String accessToken;

  private Long akTimeOut;

  private String wxToken;

  private Integer wxType;

  private String wxGhid;

  private String wxMailbox;

  private String wxNo;

  public String getWxId() {
    return wxId;
  }

  public void setWxId(String wxId) {
    this.wxId = wxId == null ? null : wxId.trim();
  }

  public String getWxName() {
    return wxName;
  }

  public void setWxName(String wxName) {
    this.wxName = wxName == null ? null : wxName.trim();
  }

  public String getAppId() {
    return appId;
  }

  public void setAppId(String appId) {
    this.appId = appId == null ? null : appId.trim();
  }

  public String getAppSecret() {
    return appSecret;
  }

  public void setAppSecret(String appSecret) {
    this.appSecret = appSecret == null ? null : appSecret.trim();
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken == null ? null : accessToken.trim();
  }

  public Long getAkTimeOut() {
    return akTimeOut;
  }

  public void setAkTimeOut(Long akTimeOut) {
    this.akTimeOut = akTimeOut;
  }

  public String getWxToken() {
    return wxToken;
  }

  public void setWxToken(String wxToken) {
    this.wxToken = wxToken == null ? null : wxToken.trim();
  }

  public Integer getWxType() {
    return wxType;
  }

  public void setWxType(Integer wxType) {
    this.wxType = wxType;
  }

  public String getWxGhid() {
    return wxGhid;
  }

  public void setWxGhid(String wxGhid) {
    this.wxGhid = wxGhid == null ? null : wxGhid.trim();
  }

  public String getWxMailbox() {
    return wxMailbox;
  }

  public void setWxMailbox(String wxMailbox) {
    this.wxMailbox = wxMailbox == null ? null : wxMailbox.trim();
  }

  public String getWxNo() {
    return wxNo;
  }

  public void setWxNo(String wxNo) {
    this.wxNo = wxNo == null ? null : wxNo.trim();
  }
}
