package com.zgxcw.service.messge.passive;

import java.io.Serializable;

/**
 * 被动回复
 * 
 * @author wensw
 *
 */
public abstract class PassiveBase implements Serializable {

  private static final long serialVersionUID = 3115447439084712178L;
  private String toUserName;// 接收方帐号（收到的OpenID）
  private String fromUserName;// 开发者微信号
  //private String createTime;// 消息创建时间 （毫秒数）
  //private String msgType;// 文本:text, 图片:image, 语音:voice, 视频:video, 音乐:music, 图文:news

  /**
   * 接收方帐号（收到的OpenID）
   * 
   * @return
   */
  public String getToUserName() {
    return toUserName;
  }

  /**
   * 接收方帐号（收到的OpenID）
   * 
   * @param toUserName
   */
  public void setToUserName(String toUserName) {
    this.toUserName = toUserName;
  }

  /**
   * 开发者微信号
   * 
   * @return
   */
  public String getFromUserName() {
    return fromUserName;
  }

  /**
   * 开发者微信号
   * 
   * @param fromUserName
   */
  public void setFromUserName(String fromUserName) {
    this.fromUserName = fromUserName;
  }

//  /**
//   * 消息创建时间 （毫秒数）
//   * 
//   * @return
//   */
//  public String getCreateTime() {
//    return createTime;
//  }
//
//  /**
//   * 消息创建时间 （毫秒数）
//   * 
//   * @param createTime
//   */
//  public void setCreateTime(String createTime) {
//    this.createTime = createTime;
//  }
//
//  /**
//   * 文本:text, 图片:image, 语音:voice, 视频:video, 音乐:music, 图文:news
//   * 
//   * @return
//   */
//  public String getMsgType() {
//    return msgType;
//  }
//
//  /**
//   * 文本:text, 图片:image, 语音:voice, 视频:video, 音乐:music, 图文:news
//   * 
//   * @param msgType
//   */
//  public void setMsgType(String msgType) {
//    this.msgType = msgType;
//  }

}
