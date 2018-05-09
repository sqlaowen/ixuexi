package com.zgxcw.service.infrastructure.dto.response;

import com.zgxcw.service.infrastructure.dto.base.WxReceiveMsgBase;

import java.io.Serializable;

/**
 * Created by huolh on 2016/7/25.
 */
public class WxReceiveMsgResponse extends WxReceiveMsgBase implements Serializable {

  private static final long serialVersionUID = -4016869991436259457L;

  private String receiveMsgId;

  private Long createTime;

  private String createUser;

  private Long updateTime;

  private String updateUser;


  public WxReceiveMsgResponse() {}

  public String getReceiveMsgId() {
    return receiveMsgId;
  }

  public void setReceiveMsgId(String receiveMsgId) {
    this.receiveMsgId = receiveMsgId;
  }

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

  /**
   * 更新时间
   *
   * @return 更新时间
   */
  public Long getUpdateTime() {
    return updateTime;
  }

  /**
   * 更新时间
   *
   * @param updateTime 更新时间
   */
  public void setUpdateTime(Long updateTime) {
    this.updateTime = updateTime;
  }

  /**
   * 更新操作人
   *
   * @return 更新操作人
   */
  public String getUpdateUser() {
    return updateUser;
  }

  /**
   * 更新操作人
   *
   * @param updateUser 更新操作人
   */
  public void setUpdateUser(String updateUser) {
    this.updateUser = updateUser == null ? null : updateUser.trim();
  }

  @Override
  public String toString() {
    return "WxReceiveMsgResponse{" + "receiveMsgId='" + receiveMsgId + '\'' + ", createTime="
        + createTime + ", createUser='" + createUser + '\'' + ", updateTime=" + updateTime
        + ", updateUser='" + updateUser + '\'' + "} " + super.toString();
  }
}
