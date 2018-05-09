package com.pay.single.bean;

import java.io.Serializable;
import java.util.Date;

public class Payment implements Serializable {

  private static final long serialVersionUID = 3075082760008431452L;

  public Payment() {}

  // 支付单id
  private String paymentId;

  // 平台商户id
  private String merchantId;

  // 支付单状态id
  private Integer paymentStateId;

  // 支付网关Id
  private String gatewayId;

  // 合并支付单id
  private String mergePaymentId;

  // 总金额(分)
  private Integer totalFee;

  // 商品名称
  private String spName;

  // 商品描述
  private String spDetail;

  // 超时时间
  private Date timeOut;

  // 支付返回url
  private String returnUrl;

  // 支付通知url
  private String notifyUrl;

  // 公用回传参数
  private String extraCommonParam;

  // 来源ip
  private String fromIp;

  // 支付单来源
  private String paymentSource;

  // 银行简码
  private String bankCode;

  // 备注
  private String paymentNote;

  // create_time
  private Date createTime;

  // create_user
  private String createUser;

  // update_time
  private Date updateTime;

  // update_user
  private String updateUser;

  // 订单关闭时间
  private Date orderCloseTime;

  // 实际支付时间
  private Date tradeTime;

  // 交易关闭时间
  private Date tradeCloseTime;

  // 第三方交易号(ali/wechat流水号)
  private String thirdTradeNo;

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
   * 合并支付单id
   * 
   * @return
   */
  public String getMergePaymentId() {
    return mergePaymentId;
  }

  /**
   * 合并支付单id
   * 
   * @param mergePaymentId
   */
  public void setMergePaymentId(String mergePaymentId) {
    this.mergePaymentId = mergePaymentId;
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
   * 商品名称
   *
   * @return
   */
  public String getSpName() {
    return spName;
  }

  /**
   * 商品名称
   *
   * @param spName
   */
  public void setSpName(String spName) {
    this.spName = spName == null ? null : spName.trim();
  }

  /**
   * 商品描述
   *
   * @return
   */
  public String getSpDetail() {
    return spDetail;
  }

  /**
   * 商品描述
   *
   * @param spDetail
   */
  public void setSpDetail(String spDetail) {
    this.spDetail = spDetail == null ? null : spDetail.trim();
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
   * 公用回传参数
   *
   * @return
   */
  public String getExtraCommonParam() {
    return extraCommonParam;
  }

  /**
   * 公用回传参数
   *
   * @param extraCommonParam
   */
  public void setExtraCommonParam(String extraCommonParam) {
    this.extraCommonParam = extraCommonParam == null ? null : extraCommonParam.trim();
  }

  /**
   * 来源ip
   *
   * @return
   */
  public String getFromIp() {
    return fromIp;
  }

  /**
   * 来源ip
   *
   * @param fromIp
   */
  public void setFromIp(String fromIp) {
    this.fromIp = fromIp == null ? null : fromIp.trim();
  }

  /**
   * 支付单来源
   *
   * @return
   */
  public String getPaymentSource() {
    return paymentSource;
  }

  /**
   * 支付单来源
   *
   * @param paymentSource
   */
  public void setPaymentSource(String paymentSource) {
    this.paymentSource = paymentSource == null ? null : paymentSource.trim();
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
   * 备注
   *
   * @return
   */
  public String getPaymentNote() {
    return paymentNote;
  }

  /**
   * 备注
   *
   * @param paymentNote
   */
  public void setPaymentNote(String paymentNote) {
    this.paymentNote = paymentNote == null ? null : paymentNote.trim();
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
