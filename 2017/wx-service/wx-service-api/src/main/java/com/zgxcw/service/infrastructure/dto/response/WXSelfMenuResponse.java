package com.zgxcw.service.infrastructure.dto.response;

import java.io.Serializable;
import java.util.List;

import com.zgxcw.service.infrastructure.dto.base.WXSelfMenuBase;

public class WXSelfMenuResponse extends WXSelfMenuBase implements Serializable {

  private static final long serialVersionUID = 3843499052166088957L;

  public WXSelfMenuResponse() {}

  private String menuId;

  private Long createTime;

  private String createUser;

  private Long updateTime;

  private String updateUser;

  private WXSelfMenuResponse parent;

  private List<WXSelfMenuResponse> children;

  public String getMenuId() {
    return menuId;
  }

  public void setMenuId(String menuId) {
    this.menuId = menuId == null ? null : menuId.trim();
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

  public WXSelfMenuResponse getParent() {
    return parent;
  }

  public void setParent(WXSelfMenuResponse parent) {
    this.parent = parent;
  }

  public List<WXSelfMenuResponse> getChildren() {
    return children;
  }

  public void setChildren(List<WXSelfMenuResponse> children) {
    this.children = children;
  }


}
