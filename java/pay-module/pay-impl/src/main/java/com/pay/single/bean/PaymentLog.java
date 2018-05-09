package com.pay.single.bean;

import java.io.Serializable;
import java.util.Date;

public class PaymentLog implements Serializable {

  private static final long serialVersionUID = -5510900430628616913L;

  private Integer paymentLogId;

  private String paymentId;

  private Integer paymentStateId;

  private Date createTime;

  public Integer getPaymentLogId() {
      return paymentLogId;
  }

  public void setPaymentLogId(Integer paymentLogId) {
      this.paymentLogId = paymentLogId;
  }

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

  public Date getCreateTime() {
      return createTime;
  }

  public void setCreateTime(Date createTime) {
      this.createTime = createTime;
  }
}
