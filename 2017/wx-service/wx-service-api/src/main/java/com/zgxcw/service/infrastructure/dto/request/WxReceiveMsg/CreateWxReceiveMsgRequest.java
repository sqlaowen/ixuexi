package com.zgxcw.service.infrastructure.dto.request.WxReceiveMsg;

import com.zgxcw.service.infrastructure.dto.base.WxReceiveMsgBase;

import java.io.Serializable;

/**
 * Created by huolh on 2016/7/25.
 */
public class CreateWxReceiveMsgRequest extends WxReceiveMsgBase implements Serializable {

  private static final long serialVersionUID = -7517969147384197648L;

  private Long createTime;

  private String createUser;

  public CreateWxReceiveMsgRequest() {}


  /**
   * 创建时间
   *
   * @return 创建时间
   */
  public Long getCreateTime() {
    return createTime;
  }

  /**
   * 创建时间
   *
   * @param createTime 创建时间
   */
  public void setCreateTime(Long createTime) {
    this.createTime = createTime;
  }

  /**
   * 创建操作人
   *
   * @return 创建操作人
   */
  public String getCreateUser() {
    return createUser;
  }

  /**
   * 创建操作人
   *
   * @param createUser 创建操作人
   */
  public void setCreateUser(String createUser) {
    this.createUser = createUser == null ? null : createUser.trim();
  }


}
