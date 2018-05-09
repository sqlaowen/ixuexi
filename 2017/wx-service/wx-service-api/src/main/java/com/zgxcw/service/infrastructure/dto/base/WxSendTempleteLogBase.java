package com.zgxcw.service.infrastructure.dto.base;

import java.io.Serializable;

/**
 * Created by huolh on 2016/7/26.
 */
public abstract class WxSendTempleteLogBase implements Serializable {

  private static final long serialVersionUID = -4036194529013641578L;

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


  /**
   * 模板id
   *
   * @return 模板id
   */
  public String getTempId() {
    return tempId;
  }

  /**
   * 模板id
   *
   * @param tempId 模板id
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
    this.createUser = createUser;
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


