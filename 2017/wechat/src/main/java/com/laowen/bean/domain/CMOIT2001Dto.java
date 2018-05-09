package com.laowen.bean.domain;

import java.io.Serializable;

/**
 * Created by iyou on 2017/4/22.
 */
public class CMOIT2001Dto implements Serializable {

    private static final long serialVersionUID = 5823062449906911416L;

    private String gprsstatus; //GPRS在线状态[00:离线, 01:在线]
    private String ip;//用户IP地址
    private String apn;//用户接入的APN
    private String rat;//接入方式[1:3G, 2:2G, 6:4G及其他]

    /**
     * GPRS在线状态[00:离线, 01:在线]
     *
     * @return
     */
    public String getGprsstatus() {
        return gprsstatus;
    }

    /**
     * GPRS在线状态[00:离线, 01:在线]
     *
     * @param gprsstatus
     */
    public void setGprsstatus(String gprsstatus) {
        if ("00".equals(gprsstatus)) {
            this.gprsstatus = "离线";
        } else if ("01".equals(gprsstatus)) {
            this.gprsstatus = "在线";
        } else {
            this.gprsstatus = "--";
        }
    }

    /**
     * 用户IP地址
     *
     * @return
     */
    public String getIp() {
        return ip;
    }

    /**
     * 用户IP地址
     *
     * @param ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 用户接入的APN
     *
     * @return
     */
    public String getApn() {
        return apn;
    }

    /**
     * 用户接入的APN
     *
     * @param apn
     */
    public void setApn(String apn) {
        this.apn = apn;
    }

    /**
     * 接入方式[1:3G, 2:2G, 6:4G及其他]
     *
     * @return
     */
    public String getRat() {
        return rat;
    }

    /**
     * 接入方式[1:3G, 2:2G, 6:4G及其他]
     *
     * @param rat
     */
    public void setRat(String rat) {
        if ("1".equals(rat)) {
            this.rat = "3G";
        } else if ("2".equals(rat)) {
            this.rat = "2G";
        } else if ("6".equals(rat)) {
            this.rat = "4G/其他";
        } else {
            this.rat = "--";
        }
    }

    @Override
    public String toString() {
        return "CMOIT2001Dto{" +
                "gprsstatus='" + gprsstatus + '\'' +
                ", ip='" + ip + '\'' +
                ", apn='" + apn + '\'' +
                ", rat='" + rat + '\'' +
                '}';
    }

}
