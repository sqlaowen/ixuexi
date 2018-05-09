package com.zgxcw.service.infrastructure.bean;

import java.io.Serializable;

public class WXAutoResContent implements Serializable {

  private static final long serialVersionUID = 3046076067606737034L;

  private String contentId;

  private String resId;

  private String title;

  private String description;

  private String picUrl;

  private String url;

  private Integer status;

  private Integer contentSeq;

  private Long createTime;

  private String createUser;

  private Long updateTime;

  private String updateUser;

  public String getContentId() {
    return contentId;
  }

  public void setContentId(String contentId) {
    this.contentId = contentId == null ? null : contentId.trim();
  }

  public String getResId() {
    return resId;
  }

  public void setResId(String resId) {
    this.resId = resId == null ? null : resId.trim();
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title == null ? null : title.trim();
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description == null ? null : description.trim();
  }

  public String getPicUrl() {
    return picUrl;
  }

  public void setPicUrl(String picUrl) {
    this.picUrl = picUrl == null ? null : picUrl.trim();
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url == null ? null : url.trim();
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Integer getContentSeq() {
    return contentSeq;
  }

  public void setContentSeq(Integer contentSeq) {
    this.contentSeq = contentSeq;
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

  public Long getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Long updateTime) {
    this.updateTime = updateTime;
  }

  public String getUpdateUser() {
    return updateUser;
  }

  public void setUpdateUser(String updateUser) {
    this.updateUser = updateUser == null ? null : updateUser.trim();
  }
}
