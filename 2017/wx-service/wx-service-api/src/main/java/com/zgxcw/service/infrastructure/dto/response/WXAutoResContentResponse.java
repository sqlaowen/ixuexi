package com.zgxcw.service.infrastructure.dto.response;

import java.io.Serializable;

import com.zgxcw.service.infrastructure.dto.base.WXAutoResContentBase;

public class WXAutoResContentResponse extends WXAutoResContentBase implements Serializable {

  private static final long serialVersionUID = 3046076067606737034L;

  public WXAutoResContentResponse() {}

  private String contentId;

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
