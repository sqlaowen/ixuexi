package com.zgxcw.service.infrastructure.bean;

import java.io.Serializable;

/**
 * �����˿��Ϣ
 */
public class WxSendMsgLog implements Serializable {
 
  private static final long serialVersionUID = -3698932610334218523L;
  private String fansId;
  private String wxId;
  private String openid;

  private String ghid;

  private String msgType;

  private String msgContent;

  private Long createTime;

  private String createUser;

  public WxSendMsgLog() {}

  /**
   * ��˿id
   *
   * @return ��˿id
   */
  public String getFansId() {
    return fansId;
  }

  /**
   * ��˿id
   *
   * @param fansId ��˿id
   */
  public void setFansId(String fansId) {
    this.fansId = fansId == null ? null : fansId.trim();
  }

  /**
   * 网关微信id
   * 
   * @return
   */
  public String getWxId() {
    return wxId;
  }

  /**
   * 网关微信id
   * 
   * @param wxId
   */
  public void setWxId(String wxId) {
    this.wxId = wxId == null ? null : wxId.trim();
  }

  /**
   * openid
   *
   * @return openid
   */
  public String getOpenid() {
    return openid;
  }

  /**
   * openid
   *
   * @param openid openid
   */
  public void setOpenid(String openid) {
    this.openid = openid == null ? null : openid.trim();
  }

  /**
   * ghid
   *
   * @return ghid
   */
  public String getGhid() {
    return ghid;
  }

  /**
   * ghid
   *
   * @param ghid ghid
   */
  public void setGhid(String ghid) {
    this.ghid = ghid == null ? null : ghid.trim();
  }

  /**
   * ��Ϣ����
   *
   * @return ��Ϣ����
   */
  public String getMsgType() {
    return msgType;
  }

  /**
   * ��Ϣ����
   *
   * @param msgType ��Ϣ����
   */
  public void setMsgType(String msgType) {
    this.msgType = msgType == null ? null : msgType.trim();
  }

  /**
   * ��Ϣ����
   *
   * @return ��Ϣ����
   */
  public String getMsgContent() {
    return msgContent;
  }

  /**
   * ��Ϣ����
   *
   * @param msgContent ��Ϣ����
   */
  public void setMsgContent(String msgContent) {
    this.msgContent = msgContent == null ? null : msgContent.trim();
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
