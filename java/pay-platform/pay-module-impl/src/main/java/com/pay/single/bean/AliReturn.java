package com.pay.single.bean;

import java.io.Serializable;
import java.util.Date;

public class AliReturn implements Serializable {

  private static final long serialVersionUID = 3075082760008431452L;

  public AliReturn() {}

  // 同步通知id
  private String aliNotifyId;

  // 合并支付id
  private String mergePaymentId;

  // 商户订单号
  private String outTradeNo;

  // 成功标识
  private String isSuccess;

  // 签名方式
  private String signType;

  // 签名
  private String sign;

  // 商品名
  private String subject;

  // 支付类型
  private String paymentType;

  // 接口名
  private String exterface;

  // 支付宝流水号
  private String tradeNo;

  // 交易状态
  private String tradeStatus;

  // 通知校验ID
  private String notifyId;

  // 通知时间
  private Date notifyTime;

  // 通知类型
  private String notifyType;

  // 卖家支付宝账号
  private String sellerEmail;

  // 买家支付宝账号
  private String buyerEmail;

  // 卖家partentId
  private String sellerId;

  // 买家partentId
  private String buyerId;

  // 交易金额(分)
  private Integer totalFee;

  // 商品描述
  private String body;

  // 网银流水
  private String bankSeqNo;

  // 公用回传参数
  private String extraCommonParam;

  // 同步返回参数
  private String returnValue;

  // create_time
  private Date createTime;

  // create_user
  private String createUser;

  /**
   * 同步通知id
   *
   * @return
   */
  public String getAliNotifyId() {
    return aliNotifyId;
  }

  /**
   * 同步通知id
   *
   * @param aliNotifyId
   */
  public void setAliNotifyId(String aliNotifyId) {
    this.aliNotifyId = aliNotifyId == null ? null : aliNotifyId.trim();
  }

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
   * 商户订单号
   *
   * @return
   */
  public String getOutTradeNo() {
    return outTradeNo;
  }

  /**
   * 商户订单号
   *
   * @param outTradeNo
   */
  public void setOutTradeNo(String outTradeNo) {
    this.outTradeNo = outTradeNo == null ? null : outTradeNo.trim();
  }

  /**
   * 成功标识
   *
   * @return
   */
  public String getIsSuccess() {
    return isSuccess;
  }

  /**
   * 成功标识
   *
   * @param isSuccess
   */
  public void setIsSuccess(String isSuccess) {
    this.isSuccess = isSuccess == null ? null : isSuccess.trim();
  }

  /**
   * 签名方式
   *
   * @return
   */
  public String getSignType() {
    return signType;
  }

  /**
   * 签名方式
   *
   * @param signType
   */
  public void setSignType(String signType) {
    this.signType = signType == null ? null : signType.trim();
  }

  /**
   * 签名
   *
   * @return
   */
  public String getSign() {
    return sign;
  }

  /**
   * 签名
   *
   * @param sign
   */
  public void setSign(String sign) {
    this.sign = sign == null ? null : sign.trim();
  }

  /**
   * 商品名
   *
   * @return
   */
  public String getSubject() {
    return subject;
  }

  /**
   * 商品名
   *
   * @param subject
   */
  public void setSubject(String subject) {
    this.subject = subject == null ? null : subject.trim();
  }

  /**
   * 支付类型
   *
   * @return
   */
  public String getPaymentType() {
    return paymentType;
  }

  /**
   * 支付类型
   *
   * @param paymentType
   */
  public void setPaymentType(String paymentType) {
    this.paymentType = paymentType == null ? null : paymentType.trim();
  }

  /**
   * 接口名
   *
   * @return
   */
  public String getExterface() {
    return exterface;
  }

  /**
   * 接口名
   *
   * @param exterface
   */
  public void setExterface(String exterface) {
    this.exterface = exterface == null ? null : exterface.trim();
  }

  /**
   * 支付宝流水号
   *
   * @return
   */
  public String getTradeNo() {
    return tradeNo;
  }

  /**
   * 支付宝流水号
   *
   * @param tradeNo
   */
  public void setTradeNo(String tradeNo) {
    this.tradeNo = tradeNo == null ? null : tradeNo.trim();
  }

  /**
   * 交易状态
   *
   * @return
   */
  public String getTradeStatus() {
    return tradeStatus;
  }

  /**
   * 交易状态
   *
   * @param tradeStatus
   */
  public void setTradeStatus(String tradeStatus) {
    this.tradeStatus = tradeStatus == null ? null : tradeStatus.trim();
  }

  /**
   * 通知校验ID
   *
   * @return
   */
  public String getNotifyId() {
    return notifyId;
  }

  /**
   * 通知校验ID
   *
   * @param notifyId
   */
  public void setNotifyId(String notifyId) {
    this.notifyId = notifyId == null ? null : notifyId.trim();
  }

  /**
   * 通知时间
   *
   * @return
   */
  public Date getNotifyTime() {
    return notifyTime;
  }

  /**
   * 通知时间
   *
   * @param notifyTime
   */
  public void setNotifyTime(Date notifyTime) {
    this.notifyTime = notifyTime;
  }

  /**
   * 通知类型
   *
   * @return
   */
  public String getNotifyType() {
    return notifyType;
  }

  /**
   * 通知类型
   *
   * @param notifyType
   */
  public void setNotifyType(String notifyType) {
    this.notifyType = notifyType == null ? null : notifyType.trim();
  }

  /**
   * 卖家支付宝账号
   *
   * @return
   */
  public String getSellerEmail() {
    return sellerEmail;
  }

  /**
   * 卖家支付宝账号
   *
   * @param sellerEmail
   */
  public void setSellerEmail(String sellerEmail) {
    this.sellerEmail = sellerEmail == null ? null : sellerEmail.trim();
  }

  /**
   * 买家支付宝账号
   *
   * @return
   */
  public String getBuyerEmail() {
    return buyerEmail;
  }

  /**
   * 买家支付宝账号
   *
   * @param buyerEmail
   */
  public void setBuyerEmail(String buyerEmail) {
    this.buyerEmail = buyerEmail == null ? null : buyerEmail.trim();
  }

  /**
   * 卖家partentId
   *
   * @return
   */
  public String getSellerId() {
    return sellerId;
  }

  /**
   * 卖家partentId
   *
   * @param sellerId
   */
  public void setSellerId(String sellerId) {
    this.sellerId = sellerId == null ? null : sellerId.trim();
  }

  /**
   * 买家partentId
   *
   * @return
   */
  public String getBuyerId() {
    return buyerId;
  }

  /**
   * 买家partentId
   *
   * @param buyerId
   */
  public void setBuyerId(String buyerId) {
    this.buyerId = buyerId == null ? null : buyerId.trim();
  }

  /**
   * 交易金额(分)
   *
   * @return
   */
  public Integer getTotalFee() {
    return totalFee;
  }

  /**
   * 交易金额(分)
   *
   * @param totalFee
   */
  public void setTotalFee(Integer totalFee) {
    this.totalFee = totalFee;
  }

  /**
   * 商品描述
   *
   * @return
   */
  public String getBody() {
    return body;
  }

  /**
   * 商品描述
   *
   * @param body
   */
  public void setBody(String body) {
    this.body = body == null ? null : body.trim();
  }

  /**
   * 网银流水
   *
   * @return
   */
  public String getBankSeqNo() {
    return bankSeqNo;
  }

  /**
   * 网银流水
   *
   * @param bankSeqNo
   */
  public void setBankSeqNo(String bankSeqNo) {
    this.bankSeqNo = bankSeqNo == null ? null : bankSeqNo.trim();
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
   * 同步返回参数
   *
   * @return
   */
  public String getReturnValue() {
    return returnValue;
  }

  /**
   * 同步返回参数
   *
   * @param returnValue
   */
  public void setReturnValue(String returnValue) {
    this.returnValue = returnValue == null ? null : returnValue.trim();
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
