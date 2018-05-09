package com.laowen.service.dto.base;

import java.io.Serializable;

public abstract class SysPermissionBase implements Serializable {

    private static final long serialVersionUID = -3820497441670538279L;

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

}
