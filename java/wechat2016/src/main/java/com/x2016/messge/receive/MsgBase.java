package com.x2016.messge.receive;

import java.io.Serializable;

public abstract class MsgBase implements Serializable {

  private static final long serialVersionUID = -4288183094164065436L;
  private String toUserName;//开发者微信号
  private String fromUserName;//发送方帐号（一个OpenID）
  private String createTime;//消息创建时间 （毫秒数）
  private String msgType;//文本:text, 图片:image, 语音:voice, 视频:video, 小视频:shortvideo, 地理位置:location, 链接消息:link, 事件:event 
  public String getToUserName() {
    return toUserName;
  }
  public void setToUserName(String toUserName) {
    this.toUserName = toUserName;
  }
  public String getFromUserName() {
    return fromUserName;
  }
  public void setFromUserName(String fromUserName) {
    this.fromUserName = fromUserName;
  }
  public String getCreateTime() {
    return createTime;
  }
  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }
  public String getMsgType() {
    return msgType;
  }
  public void setMsgType(String msgType) {
    this.msgType = msgType;
  }
  
}
