package com.zgxcw.service.infrastructure.dto.request.wxautorescontent;

import java.io.Serializable;

import com.zgxcw.service.infrastructure.dto.base.WXAutoResContentBase;

public class UpdateWXAutoResContentRequest extends WXAutoResContentBase implements Serializable {

  private static final long serialVersionUID = 3046076067606737034L;

  public UpdateWXAutoResContentRequest() {}

  private String contentId;

  private String updateUser;

  public String getContentId() {
    return contentId;
  }

  public void setContentId(String contentId) {
    this.contentId = contentId == null ? null : contentId.trim();
  }

  public String getUpdateUser() {
    return updateUser;
  }

  public void setUpdateUser(String updateUser) {
    this.updateUser = updateUser == null ? null : updateUser.trim();
  }

}
