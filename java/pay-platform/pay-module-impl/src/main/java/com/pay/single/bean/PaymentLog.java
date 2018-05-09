package com.pay.single.bean;

import java.io.Serializable;
import java.util.Date;

public class PaymentLog implements Serializable {

  private static final long serialVersionUID = 3075082760008431452L;

  public PaymentLog() {}

  // 支付单状态日志id
  private String paymentLogId;

  // 支付单id
  private String paymentId;

  // 支付单状态id
  private Integer paymentStateId;

  // create_time
  private Date createTime;

  // create_user
  private String createUser;

  /**
   * 支付单状态日志id
   *
   * @return
   */
  public String getPaymentLogId() {
    return paymentLogId;
  }

  /**
   * 支付单状态日志id
   *
   * @param paymentLogId
   */
  public void setPaymentLogId(String paymentLogId) {
    this.paymentLogId = paymentLogId == null ? null : paymentLogId.trim();
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
   * 支付单状态id
   *
   * @return
   */
  public Integer getPaymentStateId() {
    return paymentStateId;
  }

  /**
   * 支付单状态id
   *
   * @param paymentStateId
   */
  public void setPaymentStateId(Integer paymentStateId) {
    this.paymentStateId = paymentStateId;
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
