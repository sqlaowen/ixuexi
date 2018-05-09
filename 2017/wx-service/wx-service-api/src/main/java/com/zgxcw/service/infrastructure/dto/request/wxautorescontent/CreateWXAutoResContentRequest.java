package com.zgxcw.service.infrastructure.dto.request.wxautorescontent;

import java.io.Serializable;

import com.zgxcw.service.infrastructure.dto.base.WXAutoResContentBase;

public class CreateWXAutoResContentRequest extends WXAutoResContentBase implements Serializable {

  private static final long serialVersionUID = 5823062449906911416L;

  public CreateWXAutoResContentRequest() {}

  private String createUser;

  public String getCreateUser() {
    return createUser;
  }

  public void setCreateUser(String createUser) {
    this.createUser = createUser == null ? null : createUser.trim();
  }
}
