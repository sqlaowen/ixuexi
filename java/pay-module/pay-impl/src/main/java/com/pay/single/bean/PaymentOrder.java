package com.pay.single.bean;

import java.io.Serializable;
import java.util.Date;

public class PaymentOrder implements Serializable {

  private static final long serialVersionUID = -6095146847080510593L;

  private Integer paymentOrderId;

  private String paymentId;

  private String orderId;

  private Date refCreate;

  public Integer getPaymentOrderId() {
    return paymentOrderId;
  }

  public void setPaymentOrderId(Integer paymentOrderId) {
    this.paymentOrderId = paymentOrderId;
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

  public Date getRefCreate() {
    return refCreate;
  }

  public void setRefCreate(Date refCreate) {
    this.refCreate = refCreate;
  }
}
