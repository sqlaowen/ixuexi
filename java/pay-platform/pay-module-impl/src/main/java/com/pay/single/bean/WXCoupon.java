package com.pay.single.bean;

import java.io.Serializable;

public class WXCoupon implements Serializable {

  private static final long serialVersionUID = 3075082760008431452L;

  public WXCoupon() {}

  // 微信支付优惠id
  private String yhId;

  // 异步通知id
  private String wxNotifyId;

  // 代金券或立减优惠ID
  private String couponId;

  // 单个代金券或立减优惠支付金额(分)
  private Integer couponFee;

  /**
   * 微信支付优惠id
   *
   * @return
   */
  public String getYhId() {
    return yhId;
  }

  /**
   * 微信支付优惠id
   *
   * @param yhId
   */
  public void setYhId(String yhId) {
    this.yhId = yhId == null ? null : yhId.trim();
  }

  /**
   * 异步通知id
   *
   * @return
   */
  public String getWxNotifyId() {
    return wxNotifyId;
  }

  /**
   * 异步通知id
   *
   * @param wxNotifyId
   */
  public void setWxNotifyId(String wxNotifyId) {
    this.wxNotifyId = wxNotifyId == null ? null : wxNotifyId.trim();
  }

  /**
   * 代金券或立减优惠ID
   *
   * @return
   */
  public String getCouponId() {
    return couponId;
  }

  /**
   * 代金券或立减优惠ID
   *
   * @param couponId
   */
  public void setCouponId(String couponId) {
    this.couponId = couponId == null ? null : couponId.trim();
  }

  /**
   * 单个代金券或立减优惠支付金额(分)
   *
   * @return
   */
  public Integer getCouponFee() {
    return couponFee;
  }

  /**
   * 单个代金券或立减优惠支付金额(分)
   *
   * @param couponFee
   */
  public void setCouponFee(Integer couponFee) {
    this.couponFee = couponFee;
  }
}
