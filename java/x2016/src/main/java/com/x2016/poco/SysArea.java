package com.x2016.poco;

import java.io.Serializable;
import java.util.Date;

public class SysArea implements Serializable {

  private static final long serialVersionUID = 4669383843449927237L;

  // area_id
  private Integer areaId;

  // area_name
  private String areaName;

  // area_pid
  private Integer areaPid;

  // area_seq
  private Integer areaSeq;

  // area_level
  private Integer areaLevel;

  // area_status[0:可用, 1:不可用]
  private Integer areaStatus;

  // create_time
  private Date createTime;

  // create_user
  private String createUser;

  // update_time
  private Date updateTime;

  // update_user
  private String updateUser;

  /**
   * area_id
   *
   * @return
   */
  public Integer getAreaId() {
    return areaId;
  }

  /**
   * area_id
   *
   * @param areaId
   */
  public void setAreaId(Integer areaId) {
    this.areaId = areaId;
  }

  /**
   * area_name
   *
   * @return
   */
  public String getAreaName() {
    return areaName;
  }

  /**
   * area_name
   *
   * @param areaName
   */
  public void setAreaName(String areaName) {
    this.areaName = areaName == null ? null : areaName.trim();
  }

  /**
   * area_pid
   *
   * @return
   */
  public Integer getAreaPid() {
    return areaPid;
  }

  /**
   * area_pid
   *
   * @param areaPid
   */
  public void setAreaPid(Integer areaPid) {
    this.areaPid = areaPid;
  }

  /**
   * area_seq
   *
   * @return
   */
  public Integer getAreaSeq() {
    return areaSeq;
  }

  /**
   * area_seq
   *
   * @param areaSeq
   */
  public void setAreaSeq(Integer areaSeq) {
    this.areaSeq = areaSeq;
  }

  /**
   * area_level
   *
   * @return
   */
  public Integer getAreaLevel() {
    return areaLevel;
  }

  /**
   * area_level
   *
   * @param areaLevel
   */
  public void setAreaLevel(Integer areaLevel) {
    this.areaLevel = areaLevel;
  }

  /**
   * area_status[0:可用, 1:不可用]
   *
   * @return
   */
  public Integer getAreaStatus() {
    return areaStatus;
  }

  /**
   * area_status[0:可用, 1:不可用]
   *
   * @param areaStatus
   */
  public void setAreaStatus(Integer areaStatus) {
    this.areaStatus = areaStatus;
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
