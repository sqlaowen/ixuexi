package com.laowen.bean;

import java.io.Serializable;
import java.util.Date;

//微信粉丝REF物联网卡号
public class FansRefCard implements Serializable {

    private static final long serialVersionUID = 5823062449906911416L;

    //   卡号id
    private String cardId;

    //   open_id
    private String openId;

    //   微信网关id
    private String wxId;

    //   物联卡号
    private String msisdn;

    //   iccid
    private String iccid;

    //   imsi
    private String imsi;

    //    查询次数
    private Integer searchNo;

    //   手机号
    private String phoneNo;

    //   创建时间
    private Date createTime;

    //   修改时间
    private Date editTime;

    /**
     * 卡号id
     *
     * @return
     */
    public String getCardId() {
        return cardId;
    }

    /**
     * 卡号id
     *
     * @param cardId
     */
    public void setCardId(String cardId) {
        this.cardId = cardId == null ? null : cardId.trim();
    }

    /**
     * open_id
     *
     * @return
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * open_id
     *
     * @param openId
     */
    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

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
     * 物联卡号
     *
     * @return
     */
    public String getMsisdn() {
        return msisdn;
    }

    /**
     * 物联卡号
     *
     * @param msisdn
     */
    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn == null ? null : msisdn.trim();
    }

    /**
     * iccid
     *
     * @return
     */
    public String getIccid() {
        return iccid;
    }

    /**
     * iccid
     *
     * @param iccid
     */
    public void setIccid(String iccid) {
        this.iccid = iccid == null ? null : iccid.trim();
    }

    /**
     * imsi
     *
     * @return
     */
    public String getImsi() {
        return imsi;
    }

    /**
     * imsi
     *
     * @param imsi
     */
    public void setImsi(String imsi) {
        this.imsi = imsi == null ? null : imsi.trim();
    }

    /**
     * 查询次数
     *
     * @return
     */
    public Integer getSearchNo() {
        return searchNo;
    }

    /**
     * 查询次数
     *
     * @param searchNo
     */
    public void setSearchNo(Integer searchNo) {
        this.searchNo = searchNo;
    }

    /**
     * 手机号
     *
     * @return
     */
    public String getPhoneNo() {
        return phoneNo;
    }

    /**
     * 手机号
     *
     * @param phoneNo
     */
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo == null ? null : phoneNo.trim();
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
        return "FansRefCard{" +
                "cardId='" + cardId + '\'' +
                ", openId='" + openId + '\'' +
                ", wxId='" + wxId + '\'' +
                ", msisdn='" + msisdn + '\'' +
                ", iccid='" + iccid + '\'' +
                ", imsi='" + imsi + '\'' +
                ", searchNo=" + searchNo +
                ", phoneNo='" + phoneNo + '\'' +
                ", createTime=" + createTime +
                ", editTime=" + editTime +
                '}';
    }

}