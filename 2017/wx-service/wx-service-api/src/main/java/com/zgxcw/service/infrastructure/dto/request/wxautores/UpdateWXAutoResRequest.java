package com.zgxcw.service.infrastructure.dto.request.wxautores;

import java.io.Serializable;

import com.zgxcw.service.infrastructure.dto.base.WXAutoResBase;

public class UpdateWXAutoResRequest extends WXAutoResBase implements Serializable {

  private static final long serialVersionUID = 5823062449906911416L;

  public UpdateWXAutoResRequest() {}
  
  private String resId;
  private String updateUser;

  public String getResId() {
    return resId;
  }

  public void setResId(String resId) {
    this.resId = resId == null ? null : resId.trim();
  }

  public String getUpdateUser() {
    return updateUser;
  }

  public void setUpdateUser(String updateUser) {
    this.updateUser = updateUser == null ? null : updateUser.trim();
  }
}
