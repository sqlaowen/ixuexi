package com.zgxcw.service.infrastructure.dto.request.WxSendMsgLog;

import com.zgxcw.service.infrastructure.dto.base.WxSendMsgLogBase;

import java.io.Serializable;

/**
 * Created by huolh on 2016/7/26.
 */
public class CreateWxSendMsgLogRequest extends WxSendMsgLogBase implements Serializable {

  private static final long serialVersionUID = -4341505669762911778L;

  private Long createTime;

  private String createUser;

  public CreateWxSendMsgLogRequest() {}



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
