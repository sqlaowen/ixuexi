package com.x2016.poco;

import java.io.Serializable;
import java.util.Date;

public class SysRole implements Serializable {
  
  private static final long serialVersionUID = -4511805845135950682L;

    private String roleId;

    private String roleName;

    private String roleCode;

    private String roleNote;

    private Integer roleStatus;

    private Date createTime;
    
    private Date updateTime;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode == null ? null : roleCode.trim();
    }

    public String getRoleNote() {
        return roleNote;
    }

    public void setRoleNote(String roleNote) {
        this.roleNote = roleNote == null ? null : roleNote.trim();
    }

    public Integer getRoleStatus() {
        return roleStatus;
    }

    public void setRoleStatus(Integer roleStatus) {
        this.roleStatus = roleStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
      return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
      this.updateTime = updateTime;
    }
}