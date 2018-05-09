package com.pay.single.bean;

import java.io.Serializable;
import java.util.Date;

public class AliReturn implements Serializable {

  private static final long serialVersionUID = -405576673177436209L;
  private Integer aliNotifyId;

  private String paymentId;

  private String isSuccess;

  private String signType;

  private String sign;

  private String subject;

  private String paymentType;

  private String exterface;

  private String tradeNo;

  private String tradeStatus;

  private String notifyId;

  private Date notifyTime;

  private String notifyType;

  private String sellerEmail;

  private String buyerEmail;

  private String sellerId;

  private String buyerId;

  private Long totalFee;

  private String body;

  private String bankSeqNo;

  private String extraCommonParam;

  private Date createTime;

  private String returnValue;

  public Integer getAliNotifyId() {
    return aliNotifyId;
  }

  public void setAliNotifyId(Integer aliNotifyId) {
    this.aliNotifyId = aliNotifyId;
  }

  public String getPaymentId() {
    return paymentId;
  }

  public void setPaymentId(String paymentId) {
    this.paymentId = paymentId == null ? null : paymentId.trim();
  }

  public String getIsSuccess() {
    return isSuccess;
  }

  public void setIsSuccess(String isSuccess) {
    this.isSuccess = isSuccess == null ? null : isSuccess.trim();
  }

  public String getSignType() {
    return signType;
  }

  public void setSignType(String signType) {
    this.signType = signType == null ? null : signType.trim();
  }

  public String getSign() {
    return sign;
  }

  public void setSign(String sign) {
    this.sign = sign == null ? null : sign.trim();
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject == null ? null : subject.trim();
  }

  public String getPaymentType() {
    return paymentType;
  }

  public void setPaymentType(String paymentType) {
    this.paymentType = paymentType == null ? null : paymentType.trim();
  }

  public String getExterface() {
    return exterface;
  }

  public void setExterface(String exterface) {
    this.exterface = exterface == null ? null : exterface.trim();
  }

  public String getTradeNo() {
    return tradeNo;
  }

  public void setTradeNo(String tradeNo) {
    this.tradeNo = tradeNo == null ? null : tradeNo.trim();
  }

  public String getTradeStatus() {
    return tradeStatus;
  }

  public void setTradeStatus(String tradeStatus) {
    this.tradeStatus = tradeStatus == null ? null : tradeStatus.trim();
  }

  public String getNotifyId() {
    return notifyId;
  }

  public void setNotifyId(String notifyId) {
    this.notifyId = notifyId == null ? null : notifyId.trim();
  }

  public Date getNotifyTime() {
    return notifyTime;
  }

  public void setNotifyTime(Date notifyTime) {
    this.notifyTime = notifyTime;
  }

  public String getNotifyType() {
    return notifyType;
  }

  public void setNotifyType(String notifyType) {
    this.notifyType = notifyType == null ? null : notifyType.trim();
  }

  public String getSellerEmail() {
    return sellerEmail;
  }

  public void setSellerEmail(String sellerEmail) {
    this.sellerEmail = sellerEmail == null ? null : sellerEmail.trim();
  }

  public String getBuyerEmail() {
    return buyerEmail;
  }

  public void setBuyerEmail(String buyerEmail) {
    this.buyerEmail = buyerEmail == null ? null : buyerEmail.trim();
  }

  public String getSellerId() {
    return sellerId;
  }

  public void setSellerId(String sellerId) {
    this.sellerId = sellerId == null ? null : sellerId.trim();
  }

  public String getBuyerId() {
    return buyerId;
  }

  public void setBuyerId(String buyerId) {
    this.buyerId = buyerId == null ? null : buyerId.trim();
  }

  public Long getTotalFee() {
    return totalFee;
  }

  public void setTotalFee(Long totalFee) {
    this.totalFee = totalFee;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body == null ? null : body.trim();
  }

  public String getBankSeqNo() {
    return bankSeqNo;
  }

  public void setBankSeqNo(String bankSeqNo) {
    this.bankSeqNo = bankSeqNo == null ? null : bankSeqNo.trim();
  }

  public String getExtraCommonParam() {
    return extraCommonParam;
  }

  public void setExtraCommonParam(String extraCommonParam) {
    this.extraCommonParam = extraCommonParam == null ? null : extraCommonParam.trim();
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public String getReturnValue() {
    return returnValue;
  }

  public void setReturnValue(String returnValue) {
    this.returnValue = returnValue == null ? null : returnValue.trim();
  }
}
