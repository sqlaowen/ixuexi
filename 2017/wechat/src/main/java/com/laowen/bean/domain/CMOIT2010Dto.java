package com.laowen.bean.domain;

import java.io.Serializable;

/**
 * Created by iyou on 2017/4/20.
 */
public class CMOIT2010Dto extends ChinaMobileDto implements Serializable {

    private static final long serialVersionUID = 5823062449906911416L;

    private String gprs; //当日使用的GPRS流量总量(单位:KB)

    /**
     * 当日使用的GPRS流量总量(单位:KB)
     *
     * @return
     */
    public String getGprs() {
        return gprs;
    }

    /**
     * 当日使用的GPRS流量总量(单位:KB)
     *
     * @param gprs
     */
    public void setGprs(String gprs) {
        this.gprs = gprs;
    }

    @Override
    public String toString() {
        return "CMOIT2010Dto{" +
                "gprs='" + gprs + '\'' +
                "} " + super.toString();
    }
}
