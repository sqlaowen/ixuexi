package com.zgxcw.service.infrastructure.dto.base;

import java.io.Serializable;

/**
 * Created by huolh on 2016/3/1.
 */
public abstract class WXGetwayBase implements Serializable {
 
  private static final long serialVersionUID = 4103904776515089940L;

  public WXGetwayBase() {
  }

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

  /**
   * 公众号名
   *
   * @return 公众号名
   */
  public String getWxName() {
    return wxName;
  }

  /**
   * 公众号名
   *
   * @param wxName 公众号名
   */
  public void setWxName(String wxName) {
    this.wxName = wxName == null ? null : wxName.trim();
  }

  /**
   * appId
   *
   * @return appId
   */
  public String getAppId() {
    return appId;
  }

  /**
   * appId
   *
   * @param appId appId
   */
  public void setAppId(String appId) {
    this.appId = appId == null ? null : appId.trim();
  }

  /**
   * appSecret
   *
   * @return appSecret
   */
  public String getAppSecret() {
    return appSecret;
  }

  /**
   * appSecret
   *
   * @param appSecret appSecret
   */
  public void setAppSecret(String appSecret) {
    this.appSecret = appSecret == null ? null : appSecret.trim();
  }

  /**
   * accessToken
   *
   * @return accessToken
   */
  public String getAccessToken() {
    return accessToken;
  }

  /**
   * accessToken
   *
   * @param accessToken accessToken
   */
  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken == null ? null : accessToken.trim();
  }

  /**
   * accessToken过期时间
   *
   * @return accessToken过期时间
   */
  public Long getAkTimeOut() {
    return akTimeOut;
  }

  /**
   * accessToken过期时间
   *
   * @param akTimeOut accessToken过期时间
   */
  public void setAkTimeOut(Long akTimeOut) {
    this.akTimeOut = akTimeOut;
  }

  /**
   * token令牌
   *
   * @return token令牌
   */
  public String getWxToken() {
    return wxToken;
  }

  /**
   * token令牌
   *
   * @param wxToken token令牌
   */
  public void setWxToken(String wxToken) {
    this.wxToken = wxToken == null ? null : wxToken.trim();
  }

  /**
   * 公众号类型[1:订阅号, 2:服务号]
   *
   * @return 公众号类型[1:订阅号, 2:服务号]
   */
  public Integer getWxType() {
    return wxType;
  }

  /**
   * 公众号类型[1:订阅号, 2:服务号]
   *
   * @param wxType 公众号类型[1:订阅号, 2:服务号]
   */
  public void setWxType(Integer wxType) {
    this.wxType = wxType;
  }

  /**
   * 原始id
   *
   * @return 原始id
   */
  public String getWxGhid() {
    return wxGhid;
  }

  /**
   * 原始id
   *
   * @param wxGhid 原始id
   */
  public void setWxGhid(String wxGhid) {
    this.wxGhid = wxGhid == null ? null : wxGhid.trim();
  }

  /**
   * 登录邮箱
   *
   * @return 登录邮箱
   */
  public String getWxMailbox() {
    return wxMailbox;
  }

  /**
   * 登录邮箱
   *
   * @param wxMailbox 登录邮箱
   */
  public void setWxMailbox(String wxMailbox) {
    this.wxMailbox = wxMailbox == null ? null : wxMailbox.trim();
  }

  /**
   * 微信号
   *
   * @return 微信号
   */
  public String getWxNo() {
    return wxNo;
  }

  /**
   * 微信号
   *
   * @param wxNo 微信号
   */
  public void setWxNo(String wxNo) {
    this.wxNo = wxNo == null ? null : wxNo.trim();
  }
}
