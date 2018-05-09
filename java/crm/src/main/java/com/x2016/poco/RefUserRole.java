package com.x2016.poco;

import java.io.Serializable;

public class RefUserRole implements Serializable {
   
  private static final long serialVersionUID = 5084735311000575844L;

    private String refId;

    private String userId;

    private String roleId;

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}