package com.laowen.bean;

import java.io.Serializable;
import java.util.Date;

public class WXGetway implements Serializable {

    private static final long serialVersionUID = 5823062449906911416L;

    //   微信网关id
    private String wxId;

    //   企业id
    private String companyId;

    //   公众号名
    private String wxName;

    //   appId
    private String appId;

    //   appSecret
    private String appSecret;

    //   access_token
    private String accessToken;

    //   access_token过期时间
    private Long akTimeOut;

    //   token令牌
    private String wxToken;

    //   公众号类型[1:订阅号, 2:服务号]
    private Integer wxType;

    //   原始id
    private String wxGhid;

    //   登录邮箱
    private String wxMailbox;

    //   微信号
    private String wxNo;

    //   网关状态[0:可用, 1:不可用]
    private Integer wxState;

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
     * 企业id
     *
     * @return
     */
    public String getCompanyId() {
        return companyId;
    }

    /**
     * 企业id
     *
     * @param companyId
     */
    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    /**
     * 公众号名
     *
     * @return
     */
    public String getWxName() {
        return wxName;
    }

    /**
     * 公众号名
     *
     * @param wxName
     */
    public void setWxName(String wxName) {
        this.wxName = wxName == null ? null : wxName.trim();
    }

    /**
     * appId
     *
     * @return
     */
    public String getAppId() {
        return appId;
    }

    /**
     * appId
     *
     * @param appId
     */
    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    /**
     * appSecret
     *
     * @return
     */
    public String getAppSecret() {
        return appSecret;
    }

    /**
     * appSecret
     *
     * @param appSecret
     */
    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret == null ? null : appSecret.trim();
    }

    /**
     * access_token
     *
     * @return
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * access_token
     *
     * @param accessToken
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken == null ? null : accessToken.trim();
    }

    /**
     * access_token过期时间
     *
     * @return
     */
    public Long getAkTimeOut() {
        return akTimeOut;
    }

    /**
     * access_token过期时间
     *
     * @param akTimeOut
     */
    public void setAkTimeOut(Long akTimeOut) {
        this.akTimeOut = akTimeOut;
    }

    /**
     * token令牌
     *
     * @return
     */
    public String getWxToken() {
        return wxToken;
    }

    /**
     * token令牌
     *
     * @param wxToken
     */
    public void setWxToken(String wxToken) {
        this.wxToken = wxToken == null ? null : wxToken.trim();
    }

    /**
     * 公众号类型[1:订阅号, 2:服务号]
     *
     * @return
     */
    public Integer getWxType() {
        return wxType;
    }

    /**
     * 公众号类型[1:订阅号, 2:服务号]
     *
     * @param wxType
     */
    public void setWxType(Integer wxType) {
        this.wxType = wxType;
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
     * 登录邮箱
     *
     * @return
     */
    public String getWxMailbox() {
        return wxMailbox;
    }

    /**
     * 登录邮箱
     *
     * @param wxMailbox
     */
    public void setWxMailbox(String wxMailbox) {
        this.wxMailbox = wxMailbox == null ? null : wxMailbox.trim();
    }

    /**
     * 微信号
     *
     * @return
     */
    public String getWxNo() {
        return wxNo;
    }

    /**
     * 微信号
     *
     * @param wxNo
     */
    public void setWxNo(String wxNo) {
        this.wxNo = wxNo == null ? null : wxNo.trim();
    }

    /**
     * 网关状态[0:可用, 1:不可用]
     *
     * @return
     */
    public Integer getWxState() {
        return wxState;
    }

    /**
     * 网关状态[0:可用, 1:不可用]
     *
     * @param wxState
     */
    public void setWxState(Integer wxState) {
        this.wxState = wxState;
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
        return "WXGetway{" +
                "wxId='" + wxId + '\'' +
                ", companyId='" + companyId + '\'' +
                ", wxName='" + wxName + '\'' +
                ", appId='" + appId + '\'' +
                ", appSecret='" + appSecret + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", akTimeOut=" + akTimeOut +
                ", wxToken='" + wxToken + '\'' +
                ", wxType=" + wxType +
                ", wxGhid='" + wxGhid + '\'' +
                ", wxMailbox='" + wxMailbox + '\'' +
                ", wxNo='" + wxNo + '\'' +
                ", wxState=" + wxState +
                ", createTime=" + createTime +
                ", editTime=" + editTime +
                '}';
    }

}