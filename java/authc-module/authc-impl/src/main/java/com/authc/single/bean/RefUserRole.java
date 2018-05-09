package com.authc.single.bean;

import java.io.Serializable;

public class RefUserRole implements Serializable {
   
  private static final long serialVersionUID = 5084735311000575844L;

  // ref_id
  private String refId;

  // 用户ID
  private String userId;

  // 角色id
  private String roleId;

  /**
   * ref_id
   *
   * @return
   */
  public String getRefId() {
    return refId;
  }

  /**
   * ref_id
   *
   * @param refId
   */
  public void setRefId(String refId) {
    this.refId = refId == null ? null : refId.trim();
  }

  /**
   * 用户ID
   *
   * @return
   */
  public String getUserId() {
    return userId;
  }

  /**
   * 用户ID
   *
   * @param userId
   */
  public void setUserId(String userId) {
    this.userId = userId == null ? null : userId.trim();
  }

  /**
   * 角色id
   *
   * @return
   */
  public String getRoleId() {
    return roleId;
  }

  /**
   * 角色id
   *
   * @param roleId
   */
  public void setRoleId(String roleId) {
    this.roleId = roleId == null ? null : roleId.trim();
  }

}