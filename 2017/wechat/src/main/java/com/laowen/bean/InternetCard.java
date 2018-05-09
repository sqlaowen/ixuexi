package com.laowen.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 物联网卡号
 */
public class InternetCard implements Serializable {

    private static final long serialVersionUID = 5823062449906911416L;

    //   物联卡号
    private String msisdn;

    //   iccid
    private String iccid;

    //   imsi
    private String imsi;

    //   激活时间
    private Date jihuoTime;

    //   开卡时间
    private Date kaikaTime;

    //   省份
    private String province;

    //激活标记
    private Integer jihuoMark; //0:待标记, 1:已标记, 2:激活成功, 3:之前激活了

    //   创建时间
    private Date createTime;

    //   修改时间
    private Date editTime;

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
     * 激活时间
     *
     * @return
     */
    public Date getJihuoTime() {
        return jihuoTime;
    }

    /**
     * 激活时间
     *
     * @param jihuoTime
     */
    public void setJihuoTime(Date jihuoTime) {
        this.jihuoTime = jihuoTime;
    }

    /**
     * 开卡时间
     *
     * @return
     */
    public Date getKaikaTime() {
        return kaikaTime;
    }

    /**
     * 开卡时间
     *
     * @param kaikaTime
     */
    public void setKaikaTime(Date kaikaTime) {
        this.kaikaTime = kaikaTime;
    }

    /**
     * 省份
     *
     * @return
     */
    public String getProvince() {
        return province;
    }

    /**
     * 省份
     *
     * @param province
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 激活标记
     *
     * @return
     */
    public Integer getJihuoMark() {
        return jihuoMark;
    }

    /**
     * 激活标记
     *
     * @param jihuoMark
     */
    public void setJihuoMark(Integer jihuoMark) {
        this.jihuoMark = jihuoMark;
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
        return "InternetCard{" +
                "msisdn='" + msisdn + '\'' +
                ", iccid='" + iccid + '\'' +
                ", imsi='" + imsi + '\'' +
                ", jihuoTime=" + jihuoTime +
                ", kaikaTime=" + kaikaTime +
                ", province='" + province + '\'' +
                ", jihuoMark=" + jihuoMark +
                ", createTime=" + createTime +
                ", editTime=" + editTime +
                '}';
    }
}