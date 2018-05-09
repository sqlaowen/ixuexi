package com.laowen.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SysPermission implements Serializable {

    private static final long serialVersionUID = -3820497441670538279L;

    // 资源id
    private String permId;

    // 资源名
    private String permName;

    // pid
    private String permPid;

    // 类型[0:菜单, 1:功能]
    private Integer permType;

    // 排序
    private Integer permSeq;

    // url
    private String permUrl;

    // code
    private String permCode;

    // 图标
    private String permIcon;

    // 状态[0:可用, 1:不可用]
    private Integer permStatus;

    // 备注
    private String permNote;

    // create_time
    private Date createTime;

    // create_user
    private String createUser;

    // update_time
    private Date updateTime;

    // update_user
    private String updateUser;

    private List<SysPermission> childs;

    /**
     * 资源id
     *
     * @return
     */
    public String getPermId() {
        return permId;
    }

    /**
     * 资源id
     *
     * @param permId
     */
    public void setPermId(String permId) {
        this.permId = permId == null ? null : permId.trim();
    }

    /**
     * 资源名
     *
     * @return
     */
    public String getPermName() {
        return permName;
    }

    /**
     * 资源名
     *
     * @param permName
     */
    public void setPermName(String permName) {
        this.permName = permName == null ? null : permName.trim();
    }

    /**
     * pid
     *
     * @return
     */
    public String getPermPid() {
        return permPid;
    }

    /**
     * pid
     *
     * @param permPid
     */
    public void setPermPid(String permPid) {
        this.permPid = permPid == null ? null : permPid.trim();
    }

    /**
     * 类型[0:菜单, 1:功能]
     *
     * @return
     */
    public Integer getPermType() {
        return permType;
    }

    /**
     * 类型[0:菜单, 1:功能]
     *
     * @param permType
     */
    public void setPermType(Integer permType) {
        this.permType = permType;
    }

    /**
     * 排序
     *
     * @return
     */
    public Integer getPermSeq() {
        return permSeq;
    }

    /**
     * 排序
     *
     * @param permSeq
     */
    public void setPermSeq(Integer permSeq) {
        this.permSeq = permSeq;
    }

    /**
     * url
     *
     * @return
     */
    public String getPermUrl() {
        return permUrl;
    }

    /**
     * url
     *
     * @param permUrl
     */
    public void setPermUrl(String permUrl) {
        this.permUrl = permUrl == null ? null : permUrl.trim();
    }

    /**
     * code
     *
     * @return
     */
    public String getPermCode() {
        return permCode;
    }

    /**
     * code
     *
     * @param permCode
     */
    public void setPermCode(String permCode) {
        this.permCode = permCode == null ? null : permCode.trim();
    }

    /**
     * 图标
     *
     * @return
     */
    public String getPermIcon() {
        return permIcon;
    }

    /**
     * 图标
     *
     * @param permIcon
     */
    public void setPermIcon(String permIcon) {
        this.permIcon = permIcon == null ? null : permIcon.trim();
    }

    /**
     * 状态[0:可用, 1:不可用]
     *
     * @return
     */
    public Integer getPermStatus() {
        return permStatus;
    }

    /**
     * 状态[0:可用, 1:不可用]
     *
     * @param permStatus
     */
    public void setPermStatus(Integer permStatus) {
        this.permStatus = permStatus;
    }

    /**
     * 备注
     *
     * @return
     */
    public String getPermNote() {
        return permNote;
    }

    /**
     * 备注
     *
     * @param permNote
     */
    public void setPermNote(String permNote) {
        this.permNote = permNote == null ? null : permNote.trim();
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

    public List<SysPermission> getChilds() {
        return childs;
    }

    public void setChilds(List<SysPermission> childs) {
        this.childs = childs;
    }

}