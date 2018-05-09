package com.zgxcw.service.infrastructure.dto.request.wxselfmenu;

import java.io.Serializable;

import com.zgxcw.service.infrastructure.dto.base.WXSelfMenuBase;

public class UpdateWXSelfMenuRequest extends WXSelfMenuBase implements Serializable {

  private static final long serialVersionUID = 3843499052166088957L;

  public UpdateWXSelfMenuRequest() {}

  private String menuId;

  private String updateUser;

  public String getMenuId() {
    return menuId;
  }

  public void setMenuId(String menuId) {
    this.menuId = menuId == null ? null : menuId.trim();
  }

  public String getUpdateUser() {
    return updateUser;
  }

  public void setUpdateUser(String updateUser) {
    this.updateUser = updateUser == null ? null : updateUser.trim();
  }

}
