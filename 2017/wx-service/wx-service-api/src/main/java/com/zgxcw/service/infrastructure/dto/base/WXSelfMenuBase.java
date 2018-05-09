package com.zgxcw.service.infrastructure.dto.base;

import java.io.Serializable;

public abstract class WXSelfMenuBase implements Serializable {

  private static final long serialVersionUID = 3843499052166088957L;

  private String wxId;

  private String menuName;

  private String menuType;

  private String menuKey;

  private String menuUrl;

  private Integer menuSeq;

  private String menuPid;

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
}
