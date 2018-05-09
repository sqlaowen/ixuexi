package com.x2016.poco;

import java.io.Serializable;

public class RefRolePerm implements Serializable {
   
  private static final long serialVersionUID = -661064917907593907L;

    private String refId;

    private String roleId;

    private String permId;

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPermId() {
        return permId;
    }

    public void setPermId(String permId) {
        this.permId = permId;
    }
}