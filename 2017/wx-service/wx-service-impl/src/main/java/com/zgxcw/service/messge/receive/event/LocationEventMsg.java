package com.zgxcw.service.messge.receive.event;

import java.io.Serializable;

/**
 * 上报地理位置事件
 * event:LOCATION
 * 
 *
 */
public class LocationEventMsg extends EventBase implements Serializable {

  private static final long serialVersionUID = 2313839842636631442L;
  private String latitude;// 地理位置纬度
  private String longitude;// 地理位置经度
  private String precision;// 地理位置精度

  public String getLatitude() {
    return latitude;
  }

  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }

  public String getLongitude() {
    return longitude;
  }

  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }

  public String getPrecision() {
    return precision;
  }

  public void setPrecision(String precision) {
    this.precision = precision;
  }

}
