package api.pay.single.dto.base;

public abstract class PaymentCloseBase {

  // 支付单id
  private String paymentId;

  // 平台商户id
  private String merchantId;

  // 订单id
  private String orderId;

  // 关闭是否成功[T:成功，F:失败]
  private String closeCode;

  // 备注
  private String closeNote;


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
   * 平台商户id
   *
   * @return
   */
  public String getMerchantId() {
    return merchantId;
  }

  /**
   * 平台商户id
   *
   * @param merchantId
   */
  public void setMerchantId(String merchantId) {
    this.merchantId = merchantId == null ? null : merchantId.trim();
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

  /**
   * 关闭是否成功[T:成功，F:失败]
   *
   * @return
   */
  public String getCloseCode() {
    return closeCode;
  }

  /**
   * 关闭是否成功[T:成功，F:失败]
   *
   * @param closeCode
   */
  public void setCloseCode(String closeCode) {
    this.closeCode = closeCode == null ? null : closeCode.trim();
  }

  /**
   * 备注
   *
   * @return
   */
  public String getCloseNote() {
    return closeNote;
  }

  /**
   * 备注
   *
   * @param closeNote
   */
  public void setCloseNote(String closeNote) {
    this.closeNote = closeNote == null ? null : closeNote.trim();
  }

}
