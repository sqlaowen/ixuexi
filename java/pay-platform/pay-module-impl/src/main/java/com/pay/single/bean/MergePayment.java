package com.pay.single.bean;

import java.io.Serializable;
import java.util.Date;

public class MergePayment implements Serializable {

  private static final long serialVersionUID = 3075082760008431452L;

  public MergePayment() {}

  // 合并支付id
  private String mergePaymentId;

  // 平台商户id
  private String merchantId;

  // 支付网关Id
  private String gatewayId;

  // 支付单状态id [com.pay.consts.PaymentStateConst]
  private Integer paymentStateId;

  // 总金额(分)
  private Integer totalFee;

  // 支付返回url
  private String returnUrl;

  // 支付通知url
  private String notifyUrl;

  // 银行简码
  private String bankCode;

  // 超时时间
  private Date timeOut;

  // create_time
  private Date createTime;

  // create_user
  private String createUser;

  // update_time
  private Date updateTime;

  // update_user
  private String updateUser;

  // 实际支付时间
  private Date tradeTime;

  // 订单关闭时间
  private Date orderCloseTime;

  // 交易关闭时间
  private Date tradeCloseTime;

  // 第三方交易号(ali/wechat流水号)
  private String thirdTradeNo;

  /**
   * 合并支付id
   *
   * @return
   */
  public String getMergePaymentId() {
    return mergePaymentId;
  }

  /**
   * 合并支付id
   *
   * @param mergePaymentId
   */
  public void setMergePaymentId(String mergePaymentId) {
    this.mergePaymentId = mergePaymentId == null ? null : mergePaymentId.trim();
  }

  /**
   * 平台商户id
   *
   * @return
   */
  public String getMerchantId() {
    return merchantId;
  }

  /**
   * 平台商户id
   *
   * @param merchantId
   */
  public void setMerchantId(String merchantId) {
    this.merchantId = merchantId == null ? null : merchantId.trim();
  }

  /**
   * 支付网关Id
   *
   * @return
   */
  public String getGatewayId() {
    return gatewayId;
  }

  /**
   * 支付网关Id
   *
   * @param gatewayId
   */
  public void setGatewayId(String gatewayId) {
    this.gatewayId = gatewayId == null ? null : gatewayId.trim();
  }

  /**
   * 支付单状态id
   * 
   * @see com.pay.consts.PaymentStateConst
   * 
   * @return
   */
  public Integer getPaymentStateId() {
    return paymentStateId;
  }

  /**
   * 支付单状态id
   * 
   * @see com.pay.consts.PaymentStateConst
   *
   * @param paymentStateId
   */
  public void setPaymentStateId(Integer paymentStateId) {
    this.paymentStateId = paymentStateId;
  }

  /**
   * 总金额(分)
   *
   * @return
   */
  public Integer getTotalFee() {
    return totalFee;
  }

  /**
   * 总金额(分)
   *
   * @param totalFee
   */
  public void setTotalFee(Integer totalFee) {
    this.totalFee = totalFee;
  }

  /**
   * 支付返回url
   *
   * @return
   */
  public String getReturnUrl() {
    return returnUrl;
  }

  /**
   * 支付返回url
   *
   * @param returnUrl
   */
  public void setReturnUrl(String returnUrl) {
    this.returnUrl = returnUrl == null ? null : returnUrl.trim();
  }

  /**
   * 支付通知url
   *
   * @return
   */
  public String getNotifyUrl() {
    return notifyUrl;
  }

  /**
   * 支付通知url
   *
   * @param notifyUrl
   */
  public void setNotifyUrl(String notifyUrl) {
    this.notifyUrl = notifyUrl == null ? null : notifyUrl.trim();
  }

  /**
   * 银行简码
   *
   * @return
   */
  public String getBankCode() {
    return bankCode;
  }

  /**
   * 银行简码
   *
   * @param bankCode
   */
  public void setBankCode(String bankCode) {
    this.bankCode = bankCode == null ? null : bankCode.trim();
  }

  /**
   * 超时时间
   *
   * @return
   */
  public Date getTimeOut() {
    return timeOut;
  }

  /**
   * 超时时间
   *
   * @param timeOut
   */
  public void setTimeOut(Date timeOut) {
    this.timeOut = timeOut;
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

  /**
   * update_time
   *
   * @return
   */
  public Date getUpdateTime() {
    return updateTime;
  }

  /**
   * update_time
   *
   * @param updateTime
   */
  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  /**
   * update_user
   *
   * @return
   */
  public String getUpdateUser() {
    return updateUser;
  }

  /**
   * update_user
   *
   * @param updateUser
   */
  public void setUpdateUser(String updateUser) {
    this.updateUser = updateUser == null ? null : updateUser.trim();
  }

  /**
   * 实际支付时间
   *
   * @return
   */
  public Date getTradeTime() {
    return tradeTime;
  }

  /**
   * 实际支付时间
   *
   * @param tradeTime
   */
  public void setTradeTime(Date tradeTime) {
    this.tradeTime = tradeTime;
  }

  /**
   * 订单关闭时间
   *
   * @return
   */
  public Date getOrderCloseTime() {
    return orderCloseTime;
  }

  /**
   * 订单关闭时间
   *
   * @param orderCloseTime
   */
  public void setOrderCloseTime(Date orderCloseTime) {
    this.orderCloseTime = orderCloseTime;
  }

  /**
   * 交易关闭时间
   *
   * @return
   */
  public Date getTradeCloseTime() {
    return tradeCloseTime;
  }

  /**
   * 交易关闭时间
   *
   * @param tradeCloseTime
   */
  public void setTradeCloseTime(Date tradeCloseTime) {
    this.tradeCloseTime = tradeCloseTime;
  }

  /**
   * 第三方交易号(ali/wechat流水号)
   *
   * @return
   */
  public String getThirdTradeNo() {
    return thirdTradeNo;
  }

  /**
   * 第三方交易号(ali/wechat流水号)
   *
   * @param thirdTradeNo
   */
  public void setThirdTradeNo(String thirdTradeNo) {
    this.thirdTradeNo = thirdTradeNo == null ? null : thirdTradeNo.trim();
  }
}
