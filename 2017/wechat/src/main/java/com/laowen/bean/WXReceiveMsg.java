package com.laowen.bean;

import java.io.Serializable;
import java.util.Date;

public class WXReceiveMsg implements Serializable {

    private static final long serialVersionUID = 5823062449906911416L;

    //   msg_id
    private String msgId;

    //   fans_id
    private String fansId;

    //   原始id
    private String wxGhid;

    //   open_id
    private String openId;

    //   消息类型[文本:text, 图片:image, 语音:voice, 视频:video, 小视频:shortvideo, 地理位置:location, 链接消息:link, 事件:event]
    private String msgType;

    //   微信msgId
    private String wxMsgId;

    //   事件类型[订阅:subscribe, 取消订阅:unsubscribe, 扫描二维码:subscribe, 已关注:SCAN, 上报地理位置:LOCATION, 自定义菜单:CLICK, 点击链接:VIEW]
    private String event;

    //   事件key
    private String eventKey;

    //   二维码的ticket
    private String ticket;

    //   地理位置纬度
    private String latitude;

    //   地理位置经度
    private String longitude;

    //   地理位置精度
    private String precision;

    //   图片链接
    private String picUrl;

    //   消息媒体id
    private String mediaId;

    //   消息缩略图的媒体id
    private String thumbMediaId;

    //   链接消息标题
    private String title;

    //   链接消息描述
    private String description;

    //   链接消息链接
    private String url;

    //   地理维度
    private String locationX;

    //   地理经度
    private String locationY;

    //   地图缩放大小
    private String scale;

    //   地理位置信息
    private String label;

    //   消息内容
    private String content;

    //   语音格式
    private String format;

    //   开启语音识别后增加的字段,为语音识别结果，使用UTF8编码
    private String recognition;

    //   原始xml串
    private String originalXml;

    //   创建时间
    private Date createTime;

    //   修改时间
    private Date editTime;

    /**
     * msg_id
     *
     * @return
     */
    public String getMsgId() {
        return msgId;
    }

    /**
     * msg_id
     *
     * @param msgId
     */
    public void setMsgId(String msgId) {
        this.msgId = msgId == null ? null : msgId.trim();
    }

    /**
     * fans_id
     *
     * @return
     */
    public String getFansId() {
        return fansId;
    }

    /**
     * fans_id
     *
     * @param fansId
     */
    public void setFansId(String fansId) {
        this.fansId = fansId == null ? null : fansId.trim();
    }

    /**
     * 原始id
     *
     * @return
     */
    public String getWxGhid() {
        return wxGhid;
    }

    /**
     * 原始id
     *
     * @param wxGhid
     */
    public void setWxGhid(String wxGhid) {
        this.wxGhid = wxGhid == null ? null : wxGhid.trim();
    }

    /**
     * open_id
     *
     * @return
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * open_id
     *
     * @param openId
     */
    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    /**
     * 消息类型[文本:text, 图片:image, 语音:voice, 视频:video, 小视频:shortvideo, 地理位置:location, 链接消息:link, 事件:event]
     *
     * @return
     */
    public String getMsgType() {
        return msgType;
    }

    /**
     * 消息类型[文本:text, 图片:image, 语音:voice, 视频:video, 小视频:shortvideo, 地理位置:location, 链接消息:link, 事件:event]
     *
     * @param msgType
     */
    public void setMsgType(String msgType) {
        this.msgType = msgType == null ? null : msgType.trim();
    }

    /**
     * 微信msgId
     *
     * @return
     */
    public String getWxMsgId() {
        return wxMsgId;
    }

    /**
     * 微信msgId
     *
     * @param wxMsgId
     */
    public void setWxMsgId(String wxMsgId) {
        this.wxMsgId = wxMsgId == null ? null : wxMsgId.trim();
    }

    /**
     * 事件类型[订阅:subscribe, 取消订阅:unsubscribe, 扫描二维码:subscribe, 已关注:SCAN, 上报地理位置:LOCATION, 自定义菜单:CLICK, 点击链接:VIEW]
     *
     * @return
     */
    public String getEvent() {
        return event;
    }

    /**
     * 事件类型[订阅:subscribe, 取消订阅:unsubscribe, 扫描二维码:subscribe, 已关注:SCAN, 上报地理位置:LOCATION, 自定义菜单:CLICK, 点击链接:VIEW]
     *
     * @param event
     */
    public void setEvent(String event) {
        this.event = event == null ? null : event.trim();
    }

    /**
     * 事件key
     *
     * @return
     */
    public String getEventKey() {
        return eventKey;
    }

    /**
     * 事件key
     *
     * @param eventKey
     */
    public void setEventKey(String eventKey) {
        this.eventKey = eventKey == null ? null : eventKey.trim();
    }

    /**
     * 二维码的ticket
     *
     * @return
     */
    public String getTicket() {
        return ticket;
    }

    /**
     * 二维码的ticket
     *
     * @param ticket
     */
    public void setTicket(String ticket) {
        this.ticket = ticket == null ? null : ticket.trim();
    }

    /**
     * 地理位置纬度
     *
     * @return
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * 地理位置纬度
     *
     * @param latitude
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    /**
     * 地理位置经度
     *
     * @return
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * 地理位置经度
     *
     * @param longitude
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    /**
     * 地理位置精度
     *
     * @return
     */
    public String getPrecision() {
        return precision;
    }

    /**
     * 地理位置精度
     *
     * @param precision
     */
    public void setPrecision(String precision) {
        this.precision = precision == null ? null : precision.trim();
    }

    /**
     * 图片链接
     *
     * @return
     */
    public String getPicUrl() {
        return picUrl;
    }

    /**
     * 图片链接
     *
     * @param picUrl
     */
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    /**
     * 消息媒体id
     *
     * @return
     */
    public String getMediaId() {
        return mediaId;
    }

    /**
     * 消息媒体id
     *
     * @param mediaId
     */
    public void setMediaId(String mediaId) {
        this.mediaId = mediaId == null ? null : mediaId.trim();
    }

    /**
     * 消息缩略图的媒体id
     *
     * @return
     */
    public String getThumbMediaId() {
        return thumbMediaId;
    }

    /**
     * 消息缩略图的媒体id
     *
     * @param thumbMediaId
     */
    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId == null ? null : thumbMediaId.trim();
    }

    /**
     * 链接消息标题
     *
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * 链接消息标题
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 链接消息描述
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * 链接消息描述
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 链接消息链接
     *
     * @return
     */
    public String getUrl() {
        return url;
    }

    /**
     * 链接消息链接
     *
     * @param url
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * 地理维度
     *
     * @return
     */
    public String getLocationX() {
        return locationX;
    }

    /**
     * 地理维度
     *
     * @param locationX
     */
    public void setLocationX(String locationX) {
        this.locationX = locationX == null ? null : locationX.trim();
    }

    /**
     * 地理经度
     *
     * @return
     */
    public String getLocationY() {
        return locationY;
    }

    /**
     * 地理经度
     *
     * @param locationY
     */
    public void setLocationY(String locationY) {
        this.locationY = locationY == null ? null : locationY.trim();
    }

    /**
     * 地图缩放大小
     *
     * @return
     */
    public String getScale() {
        return scale;
    }

    /**
     * 地图缩放大小
     *
     * @param scale
     */
    public void setScale(String scale) {
        this.scale = scale == null ? null : scale.trim();
    }

    /**
     * 地理位置信息
     *
     * @return
     */
    public String getLabel() {
        return label;
    }

    /**
     * 地理位置信息
     *
     * @param label
     */
    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }

    /**
     * 消息内容
     *
     * @return
     */
    public String getContent() {
        return content;
    }

    /**
     * 消息内容
     *
     * @param content
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 语音格式
     *
     * @return
     */
    public String getFormat() {
        return format;
    }

    /**
     * 语音格式
     *
     * @param format
     */
    public void setFormat(String format) {
        this.format = format == null ? null : format.trim();
    }

    /**
     * 开启语音识别后增加的字段,为语音识别结果，使用UTF8编码
     *
     * @return
     */
    public String getRecognition() {
        return recognition;
    }

    /**
     * 开启语音识别后增加的字段,为语音识别结果，使用UTF8编码
     *
     * @param recognition
     */
    public void setRecognition(String recognition) {
        this.recognition = recognition == null ? null : recognition.trim();
    }

    /**
     * 原始xml串
     *
     * @return
     */
    public String getOriginalXml() {
        return originalXml;
    }

    /**
     * 原始xml串
     *
     * @param originalXml
     */
    public void setOriginalXml(String originalXml) {
        this.originalXml = originalXml == null ? null : originalXml.trim();
    }

    /**
     * 创建时间
     *
     * @return
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     *
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改时间
     *
     * @return
     */
    public Date getEditTime() {
        return editTime;
    }

    /**
     * 修改时间
     *
     * @param editTime
     */
    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    @Override
    public String toString() {
        return "WXReceiveMsg{" +
                "msgId='" + msgId + '\'' +
                ", fansId='" + fansId + '\'' +
                ", wxGhid='" + wxGhid + '\'' +
                ", openId='" + openId + '\'' +
                ", msgType='" + msgType + '\'' +
                ", wxMsgId='" + wxMsgId + '\'' +
                ", event='" + event + '\'' +
                ", eventKey='" + eventKey + '\'' +
                ", ticket='" + ticket + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", precision='" + precision + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", mediaId='" + mediaId + '\'' +
                ", thumbMediaId='" + thumbMediaId + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", locationX='" + locationX + '\'' +
                ", locationY='" + locationY + '\'' +
                ", scale='" + scale + '\'' +
                ", label='" + label + '\'' +
                ", content='" + content + '\'' +
                ", format='" + format + '\'' +
                ", recognition='" + recognition + '\'' +
                ", originalXml='" + originalXml + '\'' +
                ", createTime=" + createTime +
                ", editTime=" + editTime +
                '}';
    }
}