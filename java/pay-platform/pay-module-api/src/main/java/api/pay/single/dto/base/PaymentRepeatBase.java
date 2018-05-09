package api.pay.single.dto.base;

import java.util.Date;

public class PaymentRepeatBase {

  public PaymentRepeatBase() {}

  // 平台商户id
  private String merchantId;

  // 支付单id
  private String paymentId;

  // 支付网关Id
  private String gatewayId;

  // 合并支付id
  private String mergePaymentId;

  // 总金额(分)
  private Integer totalFee;

  // 第三方交易号(ali/wechat流水号)
  private String thirdTradeNo;

  // 实际支付时间
  private Date tradeTime;

  // 支付方式[1:ali支付, 2:微信支付]
  private Integer paymentType;

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
   * 支付网关Id
   *
   * @return
   */
  public String getGatewayId() {
    return gatewayId;
  }

  /**
   * 支付网关Id
   *
   * @param gatewayId
   */
  public void setGatewayId(String gatewayId) {
    this.gatewayId = gatewayId == null ? null : gatewayId.trim();
  }

  /**
   * 合并支付id
   *
   * @return
   */
  public String getMergePaymentId() {
    return mergePaymentId;
  }

  /**
   * 合并支付id
   *
   * @param mergePaymentId
   */
  public void setMergePaymentId(String mergePaymentId) {
    this.mergePaymentId = mergePaymentId == null ? null : mergePaymentId.trim();
  }

  /**
   * 总金额(分)
   *
   * @return
   */
  public Integer getTotalFee() {
    return totalFee;
  }

  /**
   * 总金额(分)
   *
   * @param totalFee
   */
  public void setTotalFee(Integer totalFee) {
    this.totalFee = totalFee;
  }

  /**
   * 第三方交易号(ali/wechat流水号)
   *
   * @return
   */
  public String getThirdTradeNo() {
    return thirdTradeNo;
  }

  /**
   * 第三方交易号(ali/wechat流水号)
   *
   * @param thirdTradeNo
   */
  public void setThirdTradeNo(String thirdTradeNo) {
    this.thirdTradeNo = thirdTradeNo == null ? null : thirdTradeNo.trim();
  }

  /**
   * 实际支付时间
   *
   * @return
   */
  public Date getTradeTime() {
    return tradeTime;
  }

  /**
   * 实际支付时间
   *
   * @param tradeTime
   */
  public void setTradeTime(Date tradeTime) {
    this.tradeTime = tradeTime;
  }

  /**
   * 支付方式[1:ali支付, 2:微信支付]
   *
   * @return
   */
  public Integer getPaymentType() {
    return paymentType;
  }

  /**
   * 支付方式[1:ali支付, 2:微信支付]
   *
   * @param paymentType
   */
  public void setPaymentType(Integer paymentType) {
    this.paymentType = paymentType;
  }

}
