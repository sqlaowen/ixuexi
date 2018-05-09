package com.pay.single.bean;

import java.io.Serializable;
import java.util.Date;

public class PaymentClose implements Serializable {

  private static final long serialVersionUID = -8077798628359488421L;

  private Integer closeId;

  private String paymentId;

  private String orderId;

  private String closeCode;

  private String closeNote;

  private Date closeTime;

  public Integer getCloseId() {
    return closeId;
  }

  public void setCloseId(Integer closeId) {
    this.closeId = closeId;
  }

  public String getPaymentId() {
    return paymentId;
  }

  public void setPaymentId(String paymentId) {
    this.paymentId = paymentId == null ? null : paymentId.trim();
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId == null ? null : orderId.trim();
  }

  public String getCloseCode() {
    return closeCode;
  }

  public void setCloseCode(String closeCode) {
    this.closeCode = closeCode == null ? null : closeCode.trim();
  }

  public String getCloseNote() {
    return closeNote;
  }

  public void setCloseNote(String closeNote) {
    this.closeNote = closeNote == null ? null : closeNote.trim();
  }

  public Date getCloseTime() {
    return closeTime;
  }

  public void setCloseTime(Date closeTime) {
    this.closeTime = closeTime;
  }
}
