package com.zgxcw.service.infrastructure.dto.request.wxautores;

import java.io.Serializable;

import com.zgxcw.service.infrastructure.dto.base.WXAutoResBase;

public class CreateWXAutoResRequest extends WXAutoResBase implements Serializable {

  private static final long serialVersionUID = 5823062449906911416L;

  public CreateWXAutoResRequest() {}

  private String createUser;

  public String getCreateUser() {
    return createUser;
  }

  public void setCreateUser(String createUser) {
    this.createUser = createUser == null ? null : createUser.trim();
  }

}
