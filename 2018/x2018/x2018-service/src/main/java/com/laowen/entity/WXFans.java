package com.laowen.entity;

import java.io.Serializable;
import java.util.Date;

public class WXFans implements Serializable {

    private static final long serialVersionUID = 5823062449906911416L;

    //   微信网关id
    private String wxId;

    //  openId
    private String openId;

    //   原始id
    private String wxGhid;

    //   用户状态[0:正常,1:取消关注]
    private Integer fansState;

    //   创建时间
    private Date createTime;

    //   修改时间
    private Date editTime;

    /**
     * 微信网关id
     *
     * @return
     */
    public String getWxId() {
        return wxId;
    }

    /**
     * 微信网关id
     *
     * @param wxId
     */
    public void setWxId(String wxId) {
        this.wxId = wxId == null ? null : wxId.trim();
    }

    /**
     * openId
     *
     * @return
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * openId
     *
     * @param openId
     */
    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    /**
     * 原始id
     *
     * @return
     */
    public String getWxGhid() {
        return wxGhid;
    }

    /**
     * 原始id
     *
     * @param wxGhid
     */
    public void setWxGhid(String wxGhid) {
        this.wxGhid = wxGhid == null ? null : wxGhid.trim();
    }

    /**
     * 用户状态[0:正常,1:取消关注]
     *
     * @return
     */
    public Integer getFansState() {
        return fansState;
    }

    /**
     * 用户状态[0:正常,1:取消关注]
     *
     * @param fansState
     */
    public void setFansState(Integer fansState) {
        this.fansState = fansState;
    }

    /**
     * 创建时间
     *
     * @return
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     *
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改时间
     *
     * @return
     */
    public Date getEditTime() {
        return editTime;
    }

    /**
     * 修改时间
     *
     * @param editTime
     */
    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    @Override
    public String toString() {
        return "WXFans{" +
                "wxId='" + wxId + '\'' +
                ", openId='" + openId + '\'' +
                ", wxGhid='" + wxGhid + '\'' +
                ", fansState=" + fansState +
                ", createTime=" + createTime +
                ", editTime=" + editTime +
                '}';
    }

}