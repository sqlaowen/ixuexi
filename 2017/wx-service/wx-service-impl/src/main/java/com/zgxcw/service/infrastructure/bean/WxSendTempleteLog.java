package com.zgxcw.service.infrastructure.bean;

import java.io.Serializable;

/**
 * ģ����Ϣ���ͼ�¼
 */
public class WxSendTempleteLog implements Serializable {

  private static final long serialVersionUID = 7182836506929544023L;

  private String logId;

  private String tempId;

  private String openid;

  private String wxId;// 网关微信id

  private String qiyeId;// 企业id

  private String preFill;

  private String content;

  private Long createTime;

  private String createUser;

  private String note;// 备注
  private String errcode;// 发送返回errcode
  private String errmsg;// 发送返回 errmsg
  private String msgid;// 发送返回 msgid

  public WxSendTempleteLog() {}

  /**
   * @return
   */
  public String getLogId() {
    return logId;
  }

  /**
   * @param logId
   */
  public void setLogId(String logId) {
    this.logId = logId == null ? null : logId.trim();
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
    this.wxId = wxId;
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
    this.qiyeId = qiyeId;
  }

  /**
   * ģ��id
   *
   * @return ģ��id
   */
  public String getTempId() {
    return tempId;
  }

  /**
   * ģ��id
   *
   * @param tempId ģ��id
   */
  public void setTempId(String tempId) {
    this.tempId = tempId == null ? null : tempId.trim();
  }

  /**
   * @return
   */
  public String getOpenid() {
    return openid;
  }

  /**
   * @param openid
   */
  public void setOpenid(String openid) {
    this.openid = openid == null ? null : openid.trim();
  }

  /**
   * @return
   */
  public String getPreFill() {
    return preFill;
  }

  /**
   * @param preFill
   */
  public void setPreFill(String preFill) {
    this.preFill = preFill == null ? null : preFill.trim();
  }

  /**
   * @return
   */
  public String getContent() {
    return content;
  }

  /**
   * @param content
   */
  public void setContent(String content) {
    this.content = content == null ? null : content.trim();
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

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public String getErrcode() {
    return errcode;
  }

  public void setErrcode(String errcode) {
    this.errcode = errcode;
  }

  public String getErrmsg() {
    return errmsg;
  }

  public void setErrmsg(String errmsg) {
    this.errmsg = errmsg;
  }

  public String getMsgid() {
    return msgid;
  }

  public void setMsgid(String msgid) {
    this.msgid = msgid;
  }

}
