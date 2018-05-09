package com.zgxcw.service.infrastructure.bean;

import java.io.Serializable;

public class WXSelfMenu implements Serializable {

  private static final long serialVersionUID = -1376411925259677457L;

  public WXSelfMenu() {}

  private String menuId;

  private String wxId;

  private String menuName;

  private String menuType;

  private String menuKey;

  private String menuUrl;

  private Integer menuSeq;

  private String menuPid;

  private Long createTime;

  private String createUser;

  private Long updateTime;

  private String updateUser;

  public String getMenuId() {
    return menuId;
  }

  public void setMenuId(String menuId) {
    this.menuId = menuId == null ? null : menuId.trim();
  }

  public String getWxId() {
    return wxId;
  }

  public void setWxId(String wxId) {
    this.wxId = wxId == null ? null : wxId.trim();
  }

  public String getMenuName() {
    return menuName;
  }

  public void setMenuName(String menuName) {
    this.menuName = menuName == null ? null : menuName.trim();
  }

  public String getMenuType() {
    return menuType;
  }

  public void setMenuType(String menuType) {
    this.menuType = menuType == null ? null : menuType.trim();
  }

  public String getMenuKey() {
    return menuKey;
  }

  public void setMenuKey(String menuKey) {
    this.menuKey = menuKey == null ? null : menuKey.trim();
  }

  public String getMenuUrl() {
    return menuUrl;
  }

  public void setMenuUrl(String menuUrl) {
    this.menuUrl = menuUrl == null ? null : menuUrl.trim();
  }

  public Integer getMenuSeq() {
    return menuSeq;
  }

  public void setMenuSeq(Integer menuSeq) {
    this.menuSeq = menuSeq;
  }

  public String getMenuPid() {
    return menuPid;
  }

  public void setMenuPid(String menuPid) {
    this.menuPid = menuPid == null ? null : menuPid.trim();
  }

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

  public Long getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Long updateTime) {
    this.updateTime = updateTime;
  }

  public String getUpdateUser() {
    return updateUser;
  }

  public void setUpdateUser(String updateUser) {
    this.updateUser = updateUser == null ? null : updateUser.trim();
  }
}
