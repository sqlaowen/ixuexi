package com.zgxcw.service.infrastructure.dto.request.wxbankrefopenid;

import java.io.Serializable;

import com.zgxcw.service.infrastructure.dto.base.WXBankRefOpenidBase;

public class CreateWXBankRefOpenidRequest extends WXBankRefOpenidBase implements Serializable {

  private static final long serialVersionUID = 4201222167615980724L;

  public CreateWXBankRefOpenidRequest() {}

  // 创建操作人
  private String createUser;

  /**
   * 创建操作人
   *
   * @return
   */
  public String getCreateUser() {
    return createUser;
  }

  /**
   * 创建操作人
   *
   * @param createUser
   */
  public void setCreateUser(String createUser) {
    this.createUser = createUser == null ? null : createUser.trim();
  }
}
