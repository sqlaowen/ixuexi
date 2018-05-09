package com.laowen.bean.domain;

import java.io.Serializable;

/**
 * Created by iyou on 2017/4/20.
 */
public class CMOIT2009Dto extends ChinaMobileDto implements Serializable {

    private static final long serialVersionUID = 5823062449906911416L;

    private String sms; //短信条数(单位:条)

    /**
     * 短信条数(单位:条)
     *
     * @return
     */
    public String getSms() {
        return sms;
    }

    /**
     * 短信条数(单位:条)
     *
     * @param sms
     */
    public void setSms(String sms) {
        this.sms = sms;
    }

    @Override
    public String toString() {
        return "CMOIT2009Dto{" +
                "sms='" + sms + '\'' +
                "} " + super.toString();
    }
}
