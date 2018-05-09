package com.zgxcw.service.infrastructure.dto.request.WxSendTempleteLog;

import com.zgxcw.service.infrastructure.dto.base.WxSendTempleteLogBase;

import java.io.Serializable;

/**
 * Created by huolh on 2016/7/26.
 */
public class CreateWxSendTempleteLogRequest extends WxSendTempleteLogBase implements Serializable {

  private static final long serialVersionUID = 7181145296314991281L;

  private Long createTime;

  private String createUser;

  public CreateWxSendTempleteLogRequest() {}

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
}
