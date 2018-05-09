package com.zgxcw.service.infrastructure.dto.response;

import java.io.Serializable;

import com.zgxcw.service.infrastructure.dto.base.WXBankRefOpenidBase;

public class WXBankRefOpenidResponse extends WXBankRefOpenidBase implements Serializable {

  private static final long serialVersionUID = 8615746509899471246L;

  public WXBankRefOpenidResponse() {}

  // 关联id
  private String refId;
  // 创建时间
  private Long createTime;
  // 创建操作人
  private String createUser;

  /**
   * 关联id
   *
   * @return
   */
  public String getRefId() {
    return refId;
  }

  /**
   * 关联id
   *
   * @param refId
   */
  public void setRefId(String refId) {
    this.refId = refId == null ? null : refId.trim();
  }

  /**
   * 创建时间
   *
   * @return
   */
  public Long getCreateTime() {
    return createTime;
  }

  /**
   * 创建时间
   *
   * @param createTime
   */
  public void setCreateTime(Long createTime) {
    this.createTime = createTime;
  }

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
