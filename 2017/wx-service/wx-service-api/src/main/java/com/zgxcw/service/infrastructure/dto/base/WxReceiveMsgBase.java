package com.zgxcw.service.infrastructure.dto.base;

import java.io.Serializable;

/**
 * ΢��ת����Ϣ
 */
public abstract class WxReceiveMsgBase implements Serializable {

  private static final long serialVersionUID = -395827639801068490L;
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
   * ������΢�ź�
   *
   * @return ������΢�ź�
   */
  public String getToUserName() {
    return toUserName;
  }

  /**
   * ������΢�ź�
   *
   * @param toUserName ������΢�ź�
   */
  public void setToUserName(String toUserName) {
    this.toUserName = toUserName == null ? null : toUserName.trim();
  }

  /**
   * ���ͷ��ʺţ�һ��OpenID��
   *
   * @return ���ͷ��ʺţ�һ��OpenID��
   */
  public String getFromUserName() {
    return fromUserName;
  }

  /**
   * ���ͷ��ʺţ�һ��OpenID��
   *
   * @param fromUserName ���ͷ��ʺţ�һ��OpenID��
   */
  public void setFromUserName(String fromUserName) {
    this.fromUserName = fromUserName == null ? null : fromUserName.trim();
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
   * ��ϢId
   *
   * @return ��ϢId
   */
  public String getMsgId() {
    return msgId;
  }

  /**
   * ��ϢId
   *
   * @param msgId ��ϢId
   */
  public void setMsgId(String msgId) {
    this.msgId = msgId == null ? null : msgId.trim();
  }

  /**
   * �¼�����
   *
   * @return �¼�����
   */
  public String getEvent() {
    return event;
  }

  /**
   * �¼�����
   *
   * @param event �¼�����
   */
  public void setEvent(String event) {
    this.event = event == null ? null : event.trim();
  }

  /**
   * �¼�KEYֵ
   *
   * @return �¼�KEYֵ
   */
  public String getEventKey() {
    return eventKey;
  }

  /**
   * �¼�KEYֵ
   *
   * @param eventKey �¼�KEYֵ
   */
  public void setEventKey(String eventKey) {
    this.eventKey = eventKey == null ? null : eventKey.trim();
  }

  /**
   * ��ά���ticket
   *
   * @return ��ά���ticket
   */
  public String getTicket() {
    return ticket;
  }

  /**
   * ��ά���ticket
   *
   * @param ticket ��ά���ticket
   */
  public void setTicket(String ticket) {
    this.ticket = ticket == null ? null : ticket.trim();
  }

  /**
   * ����λ��γ��
   *
   * @return ����λ��γ��
   */
  public String getLatitude() {
    return latitude;
  }

  /**
   * ����λ��γ��
   *
   * @param latitude ����λ��γ��
   */
  public void setLatitude(String latitude) {
    this.latitude = latitude == null ? null : latitude.trim();
  }

  /**
   * ����λ�þ���
   *
   * @return ����λ�þ���
   */
  public String getLongitude() {
    return longitude;
  }

  /**
   * ����λ�þ���
   *
   * @param longitude ����λ�þ���
   */
  public void setLongitude(String longitude) {
    this.longitude = longitude == null ? null : longitude.trim();
  }

  /**
   * �����þ���
   *
   * @return �����þ���
   */
  public String getPrecision() {
    return precision;
  }

  /**
   * �����þ���
   *
   * @param precision �����þ���
   */
  public void setPrecision(String precision) {
    this.precision = precision == null ? null : precision.trim();
  }

  /**
   * ͼƬ����
   *
   * @return ͼƬ����
   */
  public String getPicUrl() {
    return picUrl;
  }

  /**
   * ͼƬ����
   *
   * @param picUrl ͼƬ����
   */
  public void setPicUrl(String picUrl) {
    this.picUrl = picUrl == null ? null : picUrl.trim();
  }

  /**
   * ��Ϣý��id
   *
   * @return ��Ϣý��id
   */
  public String getMediaId() {
    return mediaId;
  }

  /**
   * ��Ϣý��id
   *
   * @param mediaId ��Ϣý��id
   */
  public void setMediaId(String mediaId) {
    this.mediaId = mediaId == null ? null : mediaId.trim();
  }

  /**
   * ��Ϣ����ͼ��ý��id
   *
   * @return ��Ϣ����ͼ��ý��id
   */
  public String getThumbMediaId() {
    return thumbMediaId;
  }

  /**
   * ��Ϣ����ͼ��ý��id
   *
   * @param thumbMediaId ��Ϣ����ͼ��ý��id
   */
  public void setThumbMediaId(String thumbMediaId) {
    this.thumbMediaId = thumbMediaId == null ? null : thumbMediaId.trim();
  }

  /**
   * ������Ϣ����
   *
   * @return ������Ϣ����
   */
  public String getTitle() {
    return title;
  }

  /**
   * ������Ϣ����
   *
   * @param title ������Ϣ����
   */
  public void setTitle(String title) {
    this.title = title == null ? null : title.trim();
  }

  /**
   * ������Ϣ����
   *
   * @return ������Ϣ����
   */
  public String getDescription() {
    return description;
  }

  /**
   * ������Ϣ����
   *
   * @param description ������Ϣ����
   */
  public void setDescription(String description) {
    this.description = description == null ? null : description.trim();
  }

  /**
   * ������Ϣ����
   *
   * @return ������Ϣ����
   */
  public String getUrl() {
    return url;
  }

  /**
   * ������Ϣ����
   *
   * @param url ������Ϣ����
   */
  public void setUrl(String url) {
    this.url = url == null ? null : url.trim();
  }

  /**
   * ����λ��ά��1
   *
   * @return ����λ��ά��1
   */
  public String getLocationX() {
    return locationX;
  }

  /**
   * ����λ��ά��1
   *
   * @param locationX ����λ��ά��1
   */
  public void setLocationX(String locationX) {
    this.locationX = locationX == null ? null : locationX.trim();
  }

  /**
   * ����λ�þ���1
   *
   * @return ����λ�þ���1
   */
  public String getLocationY() {
    return locationY;
  }

  /**
   * ����λ�þ���1
   *
   * @param locationY ����λ�þ���1
   */
  public void setLocationY(String locationY) {
    this.locationY = locationY == null ? null : locationY.trim();
  }

  /**
   * ��ͼ���Ŵ�С
   *
   * @return ��ͼ���Ŵ�С
   */
  public String getScale() {
    return scale;
  }

  /**
   * ��ͼ���Ŵ�С
   *
   * @param scale ��ͼ���Ŵ�С
   */
  public void setScale(String scale) {
    this.scale = scale == null ? null : scale.trim();
  }

  /**
   * ����λ����Ϣ
   *
   * @return ����λ����Ϣ
   */
  public String getLabel() {
    return label;
  }

  /**
   * ����λ����Ϣ
   *
   * @param label ����λ����Ϣ
   */
  public void setLabel(String label) {
    this.label = label == null ? null : label.trim();
  }

  /**
   * ��Ϣ����
   *
   * @return ��Ϣ����
   */
  public String getContent() {
    return content;
  }

  /**
   * ��Ϣ����
   *
   * @param content ��Ϣ����
   */
  public void setContent(String content) {
    this.content = content == null ? null : content.trim();
  }

  /**
   * ������ʽ
   *
   * @return ������ʽ
   */
  public String getFormat() {
    return format;
  }

  /**
   * ������ʽ
   *
   * @param format ������ʽ
   */
  public void setFormat(String format) {
    this.format = format == null ? null : format.trim();
  }

  /**
   * ����ʶ����
   *
   * @return ����ʶ����
   */
  public String getRecognition() {
    return recognition;
  }

  /**
   * ����ʶ����
   *
   * @param recognition ����ʶ����
   */
  public void setRecognition(String recognition) {
    this.recognition = recognition == null ? null : recognition.trim();
  }

  @Override
  public String toString() {
    return "WxReceiveMsgBase{" + "toUserName='" + toUserName + '\'' + ", fromUserName='"
        + fromUserName + '\'' + ", msgType='" + msgType + '\'' + ", msgId='" + msgId + '\''
        + ", event='" + event + '\'' + ", eventKey='" + eventKey + '\'' + ", ticket='" + ticket
        + '\'' + ", latitude='" + latitude + '\'' + ", longitude='" + longitude + '\''
        + ", precision='" + precision + '\'' + ", picUrl='" + picUrl + '\'' + ", mediaId='"
        + mediaId + '\'' + ", thumbMediaId='" + thumbMediaId + '\'' + ", title='" + title + '\''
        + ", description='" + description + '\'' + ", url='" + url + '\'' + ", locationX='"
        + locationX + '\'' + ", locationY='" + locationY + '\'' + ", scale='" + scale + '\''
        + ", label='" + label + '\'' + ", content='" + content + '\'' + ", format='" + format + '\''
        + ", recognition='" + recognition + '\'' + '}';
  }
}
