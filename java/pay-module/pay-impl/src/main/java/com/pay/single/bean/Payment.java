package com.pay.single.bean;

import java.io.Serializable;
import java.util.Date;

public class Payment implements Serializable {

  private static final long serialVersionUID = 2288151095582071164L;

  private String paymentId;

  private Integer paymentStateId;

  private Long totalFee;

  private String spName;

  private String spDetail;

  private Date timeOut;

  private String returnUrl;

  private String notifyUrl;

  private String extraCommonParam;

  private String fromIp;

  private String paymentSource;

  private String paymentNote;

  private Date paymentCreate;

  private Date paymentUpdate;

  private Date tradeTime;

  private String thirdTradeNo;

  private Date orderCloseTime;

  private Date tradeCloseTime;

  public String getPaymentId() {
    return paymentId;
  }

  public void setPaymentId(String paymentId) {
    this.paymentId = paymentId == null ? null : paymentId.trim();
  }

  public Integer getPaymentStateId() {
    return paymentStateId;
  }

  public void setPaymentStateId(Integer paymentStateId) {
    this.paymentStateId = paymentStateId;
  }

  public Long getTotalFee() {
    return totalFee;
  }

  public void setTotalFee(Long totalFee) {
    this.totalFee = totalFee;
  }

  public String getSpName() {
    return spName;
  }

  public void setSpName(String spName) {
    this.spName = spName == null ? null : spName.trim();
  }

  public String getSpDetail() {
    return spDetail;
  }

  public void setSpDetail(String spDetail) {
    this.spDetail = spDetail == null ? null : spDetail.trim();
  }

  public Date getTimeOut() {
    return timeOut;
  }

  public void setTimeOut(Date timeOut) {
    this.timeOut = timeOut;
  }

  public String getReturnUrl() {
    return returnUrl;
  }

  public void setReturnUrl(String returnUrl) {
    this.returnUrl = returnUrl == null ? null : returnUrl.trim();
  }

  public String getNotifyUrl() {
    return notifyUrl;
  }

  public void setNotifyUrl(String notifyUrl) {
    this.notifyUrl = notifyUrl == null ? null : notifyUrl.trim();
  }

  public String getExtraCommonParam() {
    return extraCommonParam;
  }

  public void setExtraCommonParam(String extraCommonParam) {
    this.extraCommonParam = extraCommonParam == null ? null : extraCommonParam.trim();
  }

  public String getFromIp() {
    return fromIp;
  }

  public void setFromIp(String fromIp) {
    this.fromIp = fromIp == null ? null : fromIp.trim();
  }

  public String getPaymentSource() {
    return paymentSource;
  }

  public void setPaymentSource(String paymentSource) {
    this.paymentSource = paymentSource == null ? null : paymentSource.trim();
  }

  public String getPaymentNote() {
    return paymentNote;
  }

  public void setPaymentNote(String paymentNote) {
    this.paymentNote = paymentNote == null ? null : paymentNote.trim();
  }

  public Date getPaymentCreate() {
    return paymentCreate;
  }

  public void setPaymentCreate(Date paymentCreate) {
    this.paymentCreate = paymentCreate;
  }

  public Date getPaymentUpdate() {
    return paymentUpdate;
  }

  public void setPaymentUpdate(Date paymentUpdate) {
    this.paymentUpdate = paymentUpdate;
  }

  public Date getTradeTime() {
    return tradeTime;
  }

  public void setTradeTime(Date tradeTime) {
    this.tradeTime = tradeTime;
  }

  public String getThirdTradeNo() {
    return thirdTradeNo;
  }

  public void setThirdTradeNo(String thirdTradeNo) {
    this.thirdTradeNo = thirdTradeNo == null ? null : thirdTradeNo.trim();
  }

  public Date getOrderCloseTime() {
    return orderCloseTime;
  }

  public void setOrderCloseTime(Date orderCloseTime) {
    this.orderCloseTime = orderCloseTime;
  }

  public Date getTradeCloseTime() {
    return tradeCloseTime;
  }

  public void setTradeCloseTime(Date tradeCloseTime) {
    this.tradeCloseTime = tradeCloseTime;
  }
}
