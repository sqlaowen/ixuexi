package com.laowen.bean.domain;

import java.io.Serializable;

/**
 * Created by iyou on 2017/4/20.
 * 查询时msisdn/iccid/imsi三个提供一个既可
 */
public class ChinaMobileDto implements Serializable {

    private static final long serialVersionUID = 5823062449906911416L;

    private String msisdn; //物联网专网卡号
    private String iccid; //集成电路卡识别码,IC卡的唯一识别码
    private String imsi; //国际移动用户识别码
    private String province; //省份

    /**
     * 物联网专网卡号
     *
     * @return
     */
    public String getMsisdn() {
        return msisdn;
    }

    /**
     * 物联网专网卡号
     *
     * @param msisdn
     */
    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    /**
     * 集成电路卡识别码,IC卡的唯一识别码
     *
     * @return
     */
    public String getIccid() {
        return iccid;
    }

    /**
     * 集成电路卡识别码,IC卡的唯一识别码
     *
     * @param iccid
     */
    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    /**
     * 国际移动用户识别码
     *
     * @return
     */
    public String getImsi() {
        return imsi;
    }

    /**
     * 国际移动用户识别码
     *
     * @param imsi
     */
    public void setImsi(String imsi) {
        this.imsi = imsi;
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
     * 省份
     *
     * @return
     */
    public String getProvince() {
        return province;
    }

    @Override
    public String toString() {
        return "ChinaMobileDto{" +
                "msisdn='" + msisdn + '\'' +
                ", iccid='" + iccid + '\'' +
                ", imsi='" + imsi + '\'' +
                ", province='" + province + '\'' +
                '}';
    }
}
