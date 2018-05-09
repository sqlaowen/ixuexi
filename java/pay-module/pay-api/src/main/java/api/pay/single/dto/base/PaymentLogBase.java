package api.pay.single.dto.base;

import java.io.Serializable;

public abstract class PaymentLogBase implements Serializable {

  private static final long serialVersionUID = -5510900430628616913L;

  private String paymentId;

  private Integer paymentStateId;

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

}
