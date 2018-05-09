package com.zgxcw.service.infrastructure.dto.response;

import java.io.Serializable;

import com.zgxcw.service.infrastructure.dto.base.WXAutoResBase;

public class WXAutoResResponse extends WXAutoResBase implements Serializable {

  private static final long serialVersionUID = 5823062449906911416L;

  public WXAutoResResponse() {}

  private String resId;

  private Long createTime;

  private String createUser;

  private Long updateTime;

  private String updateUser;

  public String getResId() {
    return resId;
  }

  public void setResId(String resId) {
    this.resId = resId == null ? null : resId.trim();
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
