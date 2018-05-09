package api.pay.single.dto.base;

public abstract class PaymentOrderBase {

  // 支付单id
  private String paymentId;

  // 订单id
  private String orderId;


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
   * 订单id
   *
   * @return
   */
  public String getOrderId() {
    return orderId;
  }

  /**
   * 订单id
   *
   * @param orderId
   */
  public void setOrderId(String orderId) {
    this.orderId = orderId == null ? null : orderId.trim();
  }

}
