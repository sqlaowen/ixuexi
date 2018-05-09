package com.zgxcw.service.infrastructure.dto.base;

import java.io.Serializable;

public abstract class WXUserRefOpenidBase implements Serializable {

  private static final long serialVersionUID = 1586545051278553351L;

  public WXUserRefOpenidBase() {}

  // 微信网关id
  private String wxId;

  // 企业id
  private String qiyeId;

  // 用户id
  private String yhuId;

  // openid
  private String openid;

  private String createUser;

  /**
   * 微信网关id
   *
   * @return
   */
  public String getWxId() {
    return wxId;
  }

  /**
   * 微信网关id
   *
   * @param wxId
   */
  public void setWxId(String wxId) {
    this.wxId = wxId == null ? null : wxId.trim();
  }

  /**
   * 企业id
   *
   * @return
   */
  public String getQiyeId() {
    return qiyeId;
  }

  /**
   * 企业id
   *
   * @param qiyeId
   */
  public void setQiyeId(String qiyeId) {
    this.qiyeId = qiyeId == null ? null : qiyeId.trim();
  }

  /**
   * 用户id
   *
   * @return
   */
  public String getYhuId() {
    return yhuId;
  }

  /**
   * 用户id
   *
   * @param yhuId
   */
  public void setYhuId(String yhuId) {
    this.yhuId = yhuId == null ? null : yhuId.trim();
  }

  /**
   * openid
   *
   * @return
   */
  public String getOpenid() {
    return openid;
  }

  /**
   * openid
   *
   * @param openid
   */
  public void setOpenid(String openid) {
    this.openid = openid == null ? null : openid.trim();
  }

  public String getCreateUser() {
    return createUser;
  }

  public void setCreateUser(String createUser) {
    this.createUser = createUser == null ? null : createUser.trim();
  }
}
