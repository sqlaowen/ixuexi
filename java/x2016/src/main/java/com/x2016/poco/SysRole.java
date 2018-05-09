package com.x2016.poco;

import java.io.Serializable;
import java.util.Date;

public class SysRole implements Serializable {

  private static final long serialVersionUID = 4669383843449927237L;

  // 角色id
  private String roleId;

  // role_name
  private String roleName;

  // role_code
  private String roleCode;

  // role_note
  private String roleNote;

  // role_status[0:可用, 1:不可用]
  private Integer roleStatus;

  // create_time
  private Date createTime;

  // create_user
  private String createUser;

  // update_time
  private Date updateTime;

  // update_user
  private String updateUser;

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

  /**
   * role_name
   *
   * @return
   */
  public String getRoleName() {
    return roleName;
  }

  /**
   * role_name
   *
   * @param roleName
   */
  public void setRoleName(String roleName) {
    this.roleName = roleName == null ? null : roleName.trim();
  }

  /**
   * role_code
   *
   * @return
   */
  public String getRoleCode() {
    return roleCode;
  }

  /**
   * role_code
   *
   * @param roleCode
   */
  public void setRoleCode(String roleCode) {
    this.roleCode = roleCode == null ? null : roleCode.trim();
  }

  /**
   * role_note
   *
   * @return
   */
  public String getRoleNote() {
    return roleNote;
  }

  /**
   * role_note
   *
   * @param roleNote
   */
  public void setRoleNote(String roleNote) {
    this.roleNote = roleNote == null ? null : roleNote.trim();
  }

  /**
   * role_status[0:可用, 1:不可用]
   *
   * @return
   */
  public Integer getRoleStatus() {
    return roleStatus;
  }

  /**
   * role_status[0:可用, 1:不可用]
   *
   * @param roleStatus
   */
  public void setRoleStatus(Integer roleStatus) {
    this.roleStatus = roleStatus;
  }

  /**
   * create_time
   *
   * @return
   */
  public Date getCreateTime() {
    return createTime;
  }

  /**
   * create_time
   *
   * @param createTime
   */
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  /**
   * create_user
   *
   * @return
   */
  public String getCreateUser() {
    return createUser;
  }

  /**
   * create_user
   *
   * @param createUser
   */
  public void setCreateUser(String createUser) {
    this.createUser = createUser == null ? null : createUser.trim();
  }

  /**
   * update_time
   *
   * @return
   */
  public Date getUpdateTime() {
    return updateTime;
  }

  /**
   * update_time
   *
   * @param updateTime
   */
  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  /**
   * update_user
   *
   * @return
   */
  public String getUpdateUser() {
    return updateUser;
  }

  /**
   * update_user
   *
   * @param updateUser
   */
  public void setUpdateUser(String updateUser) {
    this.updateUser = updateUser == null ? null : updateUser.trim();
  }
}
