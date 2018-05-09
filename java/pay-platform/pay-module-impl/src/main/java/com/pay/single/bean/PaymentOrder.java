package com.pay.single.bean;

import java.io.Serializable;
import java.util.Date;

public class PaymentOrder implements Serializable {

  private static final long serialVersionUID = 3075082760008431452L;

  public PaymentOrder() {}

  // 订单ref支付单id
  private String paymentOrderId;

  // 支付单id
  private String paymentId;

  // 订单id
  private String orderId;

  // create_time
  private Date createTime;

  // create_user
  private String createUser;

  /**
   * 订单ref支付单id
   *
   * @return
   */
  public String getPaymentOrderId() {
    return paymentOrderId;
  }

  /**
   * 订单ref支付单id
   *
   * @param paymentOrderId
   */
  public void setPaymentOrderId(String paymentOrderId) {
    this.paymentOrderId = paymentOrderId == null ? null : paymentOrderId.trim();
  }

  /**
   * 支付单id
   *
   * @return
   */
  public String getPaymentId() {
    return paymentId;
  }

  /**
   * 支付单id
   *
   * @param paymentId
   */
  public void setPaymentId(String paymentId) {
    this.paymentId = paymentId == null ? null : paymentId.trim();
  }

  /**
   * 订单id
   *
   * @return
   */
  public String getOrderId() {
    return orderId;
  }

  /**
   * 订单id
   *
   * @param orderId
   */
  public void setOrderId(String orderId) {
    this.orderId = orderId == null ? null : orderId.trim();
  }

  /**
   * create_time
   *
   * @return
   */
  public Date getCreateTime() {
    return createTime;
  }

  /**
   * create_time
   *
   * @param createTime
   */
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  /**
   * create_user
   *
   * @return
   */
  public String getCreateUser() {
    return createUser;
  }

  /**
   * create_user
   *
   * @param createUser
   */
  public void setCreateUser(String createUser) {
    this.createUser = createUser == null ? null : createUser.trim();
  }
}
