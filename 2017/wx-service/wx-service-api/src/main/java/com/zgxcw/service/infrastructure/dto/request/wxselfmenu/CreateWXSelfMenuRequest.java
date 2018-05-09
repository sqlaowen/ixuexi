package com.zgxcw.service.infrastructure.dto.request.wxselfmenu;

import java.io.Serializable;

import com.zgxcw.service.infrastructure.dto.base.WXSelfMenuBase;

public class CreateWXSelfMenuRequest extends WXSelfMenuBase implements Serializable {

  private static final long serialVersionUID = 3843499052166088957L;

  public CreateWXSelfMenuRequest() {}
  
  private String createUser;

  public String getCreateUser() {
    return createUser;
  }

  public void setCreateUser(String createUser) {
    this.createUser = createUser == null ? null : createUser.trim();
  }

}
