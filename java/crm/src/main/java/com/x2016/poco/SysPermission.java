package com.x2016.poco;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SysPermission implements Serializable {
  
  private static final long serialVersionUID = -3820497441670538279L;

    private String permId;

    private String permName;

    private Integer permPid;

    private Integer permType;

    private Integer permSeq;

    private String permUrl;

    private String permCode;

    private String permIcon;

    private Integer permStatus;

    private String permNote;
    
    private Date createTime;
    
    private Date updateTime;
    
    private List<SysPermission> childs;

    public String getPermId() {
        return permId;
    }

    public void setPermId(String permId) {
        this.permId = permId;
    }

    public String getPermName() {
        return permName;
    }

    public void setPermName(String permName) {
        this.permName = permName == null ? null : permName.trim();
    }

    public Integer getPermPid() {
        return permPid;
    }

    public void setPermPid(Integer permPid) {
        this.permPid = permPid;
    }

    public Integer getPermType() {
        return permType;
    }

    public void setPermType(Integer permType) {
        this.permType = permType;
    }

    public Integer getPermSeq() {
        return permSeq;
    }

    public void setPermSeq(Integer permSeq) {
        this.permSeq = permSeq;
    }

    public String getPermUrl() {
        return permUrl;
    }

    public void setPermUrl(String permUrl) {
        this.permUrl = permUrl == null ? null : permUrl.trim();
    }

    public String getPermCode() {
        return permCode;
    }

    public void setPermCode(String permCode) {
        this.permCode = permCode == null ? null : permCode.trim();
    }

    public String getPermIcon() {
        return permIcon;
    }

    public void setPermIcon(String permIcon) {
        this.permIcon = permIcon == null ? null : permIcon.trim();
    }

    public Integer getPermStatus() {
        return permStatus;
    }

    public void setPermStatus(Integer permStatus) {
        this.permStatus = permStatus;
    }

    public String getPermNote() {
        return permNote;
    }

    public void setPermNote(String permNote) {
        this.permNote = permNote == null ? null : permNote.trim();
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

    public List<SysPermission> getChilds() {
      return childs;
    }

    public void setChilds(List<SysPermission> childs) {
      this.childs = childs;
    }
}