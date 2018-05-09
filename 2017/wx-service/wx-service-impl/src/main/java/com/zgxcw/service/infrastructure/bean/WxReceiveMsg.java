package com.zgxcw.service.infrastructure.bean;

import java.io.Serializable;

/**
 * Created by huolh on 2016/7/25.
 */
public class WxReceiveMsg implements Serializable {

  private static final long serialVersionUID = -3958159791941486675L;
  private String receiveMsgId;
  private String wxId;
  private String toUserName;

  private String fromUserName;

  private String msgType;

  private String msgId;

  private String event;

  private String eventKey;

  private String ticket;

  private String latitude;

  private String longitude;

  private String precision;

  private String picUrl;

  private String mediaId;

  private String thumbMediaId;

  private String title;

  private String description;

  private String url;

  private String locationX;

  private String locationY;

  private String scale;

  private String label;

  private String content;

  private String format;

  private String recognition;

  private Long createTime;

  private String createUser;

  private Long updateTime;

  private String updateUser;

  public String getReceiveMsgId() {
    return receiveMsgId;
  }

  public void setReceiveMsgId(String receiveMsgId) {
    this.receiveMsgId = receiveMsgId;
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
   * 开发者微信号
   *
   * @return 开发者微信号
   */
  public String getToUserName() {
    return toUserName;
  }

  /**
   * 开发者微信号
   *
   * @param toUserName 开发者微信号
   */
  public void setToUserName(String toUserName) {
    this.toUserName = toUserName == null ? null : toUserName.trim();
  }

  /**
   * 发送方帐号（一个OpenID）
   *
   * @return 发送方帐号（一个OpenID）
   */
  public String getFromUserName() {
    return fromUserName;
  }

  /**
   * 发送方帐号（一个OpenID）
   *
   * @param fromUserName 发送方帐号（一个OpenID）
   */
  public void setFromUserName(String fromUserName) {
    this.fromUserName = fromUserName == null ? null : fromUserName.trim();
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
   * 消息Id
   *
   * @return 消息Id
   */
  public String getMsgId() {
    return msgId;
  }

  /**
   * 消息Id
   *
   * @param msgId 消息Id
   */
  public void setMsgId(String msgId) {
    this.msgId = msgId == null ? null : msgId.trim();
  }

  /**
   * 事件类型
   *
   * @return 事件类型
   */
  public String getEvent() {
    return event;
  }

  /**
   * 事件类型
   *
   * @param event 事件类型
   */
  public void setEvent(String event) {
    this.event = event == null ? null : event.trim();
  }

  /**
   * 事件KEY值
   *
   * @return 事件KEY值
   */
  public String getEventKey() {
    return eventKey;
  }

  /**
   * 事件KEY值
   *
   * @param eventKey 事件KEY值
   */
  public void setEventKey(String eventKey) {
    this.eventKey = eventKey == null ? null : eventKey.trim();
  }

  /**
   * 二维码的ticket
   *
   * @return 二维码的ticket
   */
  public String getTicket() {
    return ticket;
  }

  /**
   * 二维码的ticket
   *
   * @param ticket 二维码的ticket
   */
  public void setTicket(String ticket) {
    this.ticket = ticket == null ? null : ticket.trim();
  }

  /**
   * 地理位置纬度
   *
   * @return 地理位置纬度
   */
  public String getLatitude() {
    return latitude;
  }

  /**
   * 地理位置纬度
   *
   * @param latitude 地理位置纬度
   */
  public void setLatitude(String latitude) {
    this.latitude = latitude == null ? null : latitude.trim();
  }

  /**
   * 地理位置经度
   *
   * @return 地理位置经度
   */
  public String getLongitude() {
    return longitude;
  }

  /**
   * 地理位置经度
   *
   * @param longitude 地理位置经度
   */
  public void setLongitude(String longitude) {
    this.longitude = longitude == null ? null : longitude.trim();
  }

  /**
   * 地理置精度
   *
   * @return 地理置精度
   */
  public String getPrecision() {
    return precision;
  }

  /**
   * 地理置精度
   *
   * @param precision 地理置精度
   */
  public void setPrecision(String precision) {
    this.precision = precision == null ? null : precision.trim();
  }

  /**
   * 图片链接
   *
   * @return 图片链接
   */
  public String getPicUrl() {
    return picUrl;
  }

  /**
   * 图片链接
   *
   * @param picUrl 图片链接
   */
  public void setPicUrl(String picUrl) {
    this.picUrl = picUrl == null ? null : picUrl.trim();
  }

  /**
   * 消息媒体id
   *
   * @return 消息媒体id
   */
  public String getMediaId() {
    return mediaId;
  }

  /**
   * 消息媒体id
   *
   * @param mediaId 消息媒体id
   */
  public void setMediaId(String mediaId) {
    this.mediaId = mediaId == null ? null : mediaId.trim();
  }

  /**
   * 消息缩略图的媒体id
   *
   * @return 消息缩略图的媒体id
   */
  public String getThumbMediaId() {
    return thumbMediaId;
  }

  /**
   * 消息缩略图的媒体id
   *
   * @param thumbMediaId 消息缩略图的媒体id
   */
  public void setThumbMediaId(String thumbMediaId) {
    this.thumbMediaId = thumbMediaId == null ? null : thumbMediaId.trim();
  }

  /**
   * 链接消息标题
   *
   * @return 链接消息标题
   */
  public String getTitle() {
    return title;
  }

  /**
   * 链接消息标题
   *
   * @param title 链接消息标题
   */
  public void setTitle(String title) {
    this.title = title == null ? null : title.trim();
  }

  /**
   * 链接消息描述
   *
   * @return 链接消息描述
   */
  public String getDescription() {
    return description;
  }

  /**
   * 链接消息描述
   *
   * @param description 链接消息描述
   */
  public void setDescription(String description) {
    this.description = description == null ? null : description.trim();
  }

  /**
   * 链接消息链接
   *
   * @return 链接消息链接
   */
  public String getUrl() {
    return url;
  }

  /**
   * 链接消息链接
   *
   * @param url 链接消息链接
   */
  public void setUrl(String url) {
    this.url = url == null ? null : url.trim();
  }

  /**
   * 地理位置维度1
   *
   * @return 地理位置维度1
   */
  public String getLocationX() {
    return locationX;
  }

  /**
   * 地理位置维度1
   *
   * @param locationX 地理位置维度1
   */
  public void setLocationX(String locationX) {
    this.locationX = locationX == null ? null : locationX.trim();
  }

  /**
   * 地理位置经度1
   *
   * @return 地理位置经度1
   */
  public String getLocationY() {
    return locationY;
  }

  /**
   * 地理位置经度1
   *
   * @param locationY 地理位置经度1
   */
  public void setLocationY(String locationY) {
    this.locationY = locationY == null ? null : locationY.trim();
  }

  /**
   * 地图缩放大小
   *
   * @return 地图缩放大小
   */
  public String getScale() {
    return scale;
  }

  /**
   * 地图缩放大小
   *
   * @param scale 地图缩放大小
   */
  public void setScale(String scale) {
    this.scale = scale == null ? null : scale.trim();
  }

  /**
   * 地理位置信息
   *
   * @return 地理位置信息
   */
  public String getLabel() {
    return label;
  }

  /**
   * 地理位置信息
   *
   * @param label 地理位置信息
   */
  public void setLabel(String label) {
    this.label = label == null ? null : label.trim();
  }

  /**
   * 消息内容
   *
   * @return 消息内容
   */
  public String getContent() {
    return content;
  }

  /**
   * 消息内容
   *
   * @param content 消息内容
   */
  public void setContent(String content) {
    this.content = content == null ? null : content.trim();
  }

  /**
   * 语音格式
   *
   * @return 语音格式
   */
  public String getFormat() {
    return format;
  }

  /**
   * 语音格式
   *
   * @param format 语音格式
   */
  public void setFormat(String format) {
    this.format = format == null ? null : format.trim();
  }

  /**
   * 语音识别结果
   *
   * @return 语音识别结果
   */
  public String getRecognition() {
    return recognition;
  }

  /**
   * 语音识别结果
   *
   * @param recognition 语音识别结果
   */
  public void setRecognition(String recognition) {
    this.recognition = recognition == null ? null : recognition.trim();
  }

  /**
   * 创建时间
   *
   * @return 创建时间
   */
  public Long getCreateTime() {
    return createTime;
  }

  /**
   * 创建时间
   *
   * @param createTime 创建时间
   */
  public void setCreateTime(Long createTime) {
    this.createTime = createTime;
  }

  /**
   * 创建操作人
   *
   * @return 创建操作人
   */
  public String getCreateUser() {
    return createUser;
  }

  /**
   * 创建操作人
   *
   * @param createUser 创建操作人
   */
  public void setCreateUser(String createUser) {
    this.createUser = createUser == null ? null : createUser.trim();
  }

  /**
   * 更新时间
   *
   * @return 更新时间
   */
  public Long getUpdateTime() {
    return updateTime;
  }

  /**
   * 更新时间
   *
   * @param updateTime 更新时间
   */
  public void setUpdateTime(Long updateTime) {
    this.updateTime = updateTime;
  }

  /**
   * 更新操作人
   *
   * @return 更新操作人
   */
  public String getUpdateUser() {
    return updateUser;
  }

  /**
   * 更新操作人
   *
   * @param updateUser 更新操作人
   */
  public void setUpdateUser(String updateUser) {
    this.updateUser = updateUser == null ? null : updateUser.trim();
  }


}
