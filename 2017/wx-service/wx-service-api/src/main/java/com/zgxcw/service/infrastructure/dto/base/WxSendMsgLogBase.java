package com.zgxcw.service.infrastructure.dto.base;

import java.io.Serializable;

/**
 * Created by huolh on 2016/7/26.
 */
public abstract class WxSendMsgLogBase implements Serializable {

  private static final long serialVersionUID = -1184408049581154758L;
  private String wxId;
  private String openid;

  private String ghid;

  private String msgType;

  private String msgContent;

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
   * 消息类型
   *
   * @return 消息类型
   */
  public String getMsgType() {
    return msgType;
  }

  /**
   * 消息类型
   *
   * @param msgType 消息类型
   */
  public void setMsgType(String msgType) {
    this.msgType = msgType == null ? null : msgType.trim();
  }

  /**
   * 消息内容
   *
   * @return 消息内容
   */
  public String getMsgContent() {
    return msgContent;
  }

  /**
   * 消息内容
   *
   * @param msgContent 消息内容
   */
  public void setMsgContent(String msgContent) {
    this.msgContent = msgContent == null ? null : msgContent.trim();
  }


}
