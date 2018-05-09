package com.x2016.model;

import java.io.Serializable;

/**
 * 微信中转来的消息
 *
 */
public class ReceiveXmlEntity implements Serializable {

  private static final long serialVersionUID = -6835486921422549269L;
  private String toUserName;//开发者微信号
  private String fromUserName;//发送方帐号（一个OpenID）
  private String createTime;//消息创建时间 （毫秒数）
  private String msgType;//文本:text, 图片:image, 语音:voice, 视频:video, 小视频:shortvideo, 地理位置:location, 链接消息:link, 事件:event 
  private String msgId;//消息id，64位整型
  private String event;//事件类型，订阅:subscribe, 取消订阅:unsubscribe, 扫描二维码:subscribe, 已关注:SCAN, 上报地理位置:LOCATION, 自定义菜单:CLICK, 点击链接:VIEW,  
  private String eventKey;//事件KEY值，qrscene_为前缀，后面为二维码的参数值
  private String ticket;//二维码的ticket，可用来换取二维码图片
  private String latitude;//地理位置纬度
  private String longitude;//地理位置经度
  private String precision;//地理位置精度
  private String picUrl;//图片链接
  private String mediaId;//消息媒体id，可以调用多媒体文件下载接口拉取数据。
  private String thumbMediaId;//消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
  private String title;//链接消息标题
  private String description;//链接消息描述
  private String url;//链接消息链接
  private String location_X;//地理位置维度
  private String location_Y;//地理位置经度
  private String scale;//地图缩放大小
  private String label;//地理位置信息
  private String content;//消息内容
  private String format;//语音格式，如amr，speex等
  private String recognition;//开启语音识别后增加的字段,为语音识别结果，使用UTF8编码

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

  public String getMsgId() {
    return msgId;
  }

  public void setMsgId(String msgId) {
    this.msgId = msgId;
  }

  public String getEvent() {
    return event;
  }

  public void setEvent(String event) {
    this.event = event;
  }

  public String getEventKey() {
    return eventKey;
  }

  public void setEventKey(String eventKey) {
    this.eventKey = eventKey;
  }

  public String getTicket() {
    return ticket;
  }

  public void setTicket(String ticket) {
    this.ticket = ticket;
  }

  public String getLatitude() {
    return latitude;
  }

  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }

  public String getLongitude() {
    return longitude;
  }

  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }

  public String getPrecision() {
    return precision;
  }

  public void setPrecision(String precision) {
    this.precision = precision;
  }

  public String getPicUrl() {
    return picUrl;
  }

  public void setPicUrl(String picUrl) {
    this.picUrl = picUrl;
  }

  public String getMediaId() {
    return mediaId;
  }

  public void setMediaId(String mediaId) {
    this.mediaId = mediaId;
  }

  public String getThumbMediaId() {
    return thumbMediaId;
  }

  public void setThumbMediaId(String thumbMediaId) {
    this.thumbMediaId = thumbMediaId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getLocation_X() {
    return location_X;
  }

  public void setLocation_X(String location_X) {
    this.location_X = location_X;
  }

  public String getLocation_Y() {
    return location_Y;
  }

  public void setLocation_Y(String location_Y) {
    this.location_Y = location_Y;
  }

  public String getScale() {
    return scale;
  }

  public void setScale(String scale) {
    this.scale = scale;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getFormat() {
    return format;
  }

  public void setFormat(String format) {
    this.format = format;
  }

  public String getRecognition() {
    return recognition;
  }

  public void setRecognition(String recognition) {
    this.recognition = recognition;
  }

}
