package api.pay.single.dto.base;

import java.io.Serializable;

public abstract class PaymentOrderBase implements Serializable {

  private static final long serialVersionUID = -6095146847080510593L;

  private String paymentId;

  private String orderId;

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

}
