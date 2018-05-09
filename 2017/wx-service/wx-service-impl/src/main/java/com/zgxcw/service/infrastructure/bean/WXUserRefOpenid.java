package com.zgxcw.service.infrastructure.bean;

import java.io.Serializable;

public class WXUserRefOpenid implements Serializable {

  private static final long serialVersionUID = 1586545051278553351L;

  public WXUserRefOpenid() {}

  // 关联id
  private String refId;

  // 微信网关id
  private String wxId;

  // 企业id
  private String qiyeId;

  // 用户id
  private String yhuId;

  // openid
  private String openid;

  private Long createTime;

  private String createUser;

  /**
   * 关联id
   *
   * @return
   */
  public String getRefId() {
    return refId;
  }

  /**
   * 关联id
   *
   * @param refId
   */
  public void setRefId(String refId) {
    this.refId = refId == null ? null : refId.trim();
  }

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

  public Long getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Long createTime) {
    this.createTime = createTime;
  }

  public String getCreateUser() {
    return createUser;
  }

  public void setCreateUser(String createUser) {
    this.createUser = createUser == null ? null : createUser.trim();
  }
}
