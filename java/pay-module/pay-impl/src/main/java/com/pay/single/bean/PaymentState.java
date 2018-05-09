package com.pay.single.bean;

import java.io.Serializable;
import java.util.Date;

public class PaymentState implements Serializable {

  private static final long serialVersionUID = -8751369139397087674L;

  private Integer paymentStateId;

  private String stateName;

  private String stateNoet;

  private Date stateCreate;

  private Date stateUpdate;

  public Integer getPaymentStateId() {
      return paymentStateId;
  }

  public void setPaymentStateId(Integer paymentStateId) {
      this.paymentStateId = paymentStateId;
  }

  public String getStateName() {
      return stateName;
  }

  public void setStateName(String stateName) {
      this.stateName = stateName == null ? null : stateName.trim();
  }

  public String getStateNoet() {
      return stateNoet;
  }

  public void setStateNoet(String stateNoet) {
      this.stateNoet = stateNoet == null ? null : stateNoet.trim();
  }

  public Date getStateCreate() {
      return stateCreate;
  }

  public void setStateCreate(Date stateCreate) {
      this.stateCreate = stateCreate;
  }

  public Date getStateUpdate() {
      return stateUpdate;
  }

  public void setStateUpdate(Date stateUpdate) {
      this.stateUpdate = stateUpdate;
  }

  @Override
  public String toString() {
    return "PayPaymentState [paymentStateId=" + paymentStateId + ", stateName=" + stateName
        + ", stateNoet=" + stateNoet + ", stateCreate=" + stateCreate + ", stateUpdate="
        + stateUpdate + "]";
  }
}
