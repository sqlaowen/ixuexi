package com.laowen.bean.domain;

import java.io.Serializable;

/**
 * Created by iyou on 2017/4/22.
 */
public class CMOIT2029Dto implements Serializable {

    private static final long serialVersionUID = 5823062449906911416L;

    private String ip;//终端IP
    private String apn;//接入点
    private String rat;//接入方式[1:3G, 2:2G, 6:4G及其他]
    private String gprsStatus;//在线状态[00:离线, 01:在线]
    private Long timestamp;//状态变更时的时间(毫秒)

    /**
     * 终端IP
     *
     * @return
     */
    public String getIp() {
        return ip;
    }

    /**
     * 终端IP
     *
     * @param ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 接入点
     *
     * @return
     */
    public String getApn() {
        return apn;
    }

    /**
     * 接入点
     *
     * @param apn
     */
    public void setApn(String apn) {
        this.apn = apn;
    }

    /**
     * 接入方式 [1:3G, 2:2G, 6:4G及其他]
     *
     * @return
     */
    public String getRat() {
        return rat;
    }

    /**
     * 接入方式 [1:3G, 2:2G, 6:4G及其他]
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
        }
    }

    /**
     * 在线状态[00:离线, 01:在线]
     *
     * @return
     */
    public String getGprsStatus() {
        return gprsStatus;
    }

    /**
     * 在线状态[00:离线, 01:在线]
     *
     * @param gprsStatus
     */
    public void setGprsStatus(String gprsStatus) {
        if ("00".equals(gprsStatus)) {
            this.gprsStatus = "离线";
        } else if ("01".equals(gprsStatus)) {
            this.gprsStatus = "在线";
        }
    }

    /**
     * 状态变更时的时间(毫秒)
     *
     * @return
     */
    public Long getTimestamp() {
        return timestamp;
    }

    /**
     * 状态变更时的时间(毫秒)
     *
     * @param timestamp
     */
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "CMOIT2029Dto{" +
                "ip='" + ip + '\'' +
                ", apn='" + apn + '\'' +
                ", rat='" + rat + '\'' +
                ", gprsStatus='" + gprsStatus + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
