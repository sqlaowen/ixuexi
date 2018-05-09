package api.pay.single.dto.res;

import java.util.Date;

import api.pay.single.dto.base.PaymentBase;

public class PaymentResDto extends PaymentBase {

  private static final long serialVersionUID = 7925415764227586165L;

  private String paymentId;

  private Integer paymentStateId;// 支付单状态

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

  /**
   * 支付单状态
   * 
   * @see com.pay.consts.PaymentStateConst
   * @return
   */
  public Integer getPaymentStateId() {
    return paymentStateId;
  }

  /**
   * 支付单状态
   * 
   * @see com.pay.consts.PaymentStateConst
   * @param paymentStateId
   */
  public void setPaymentStateId(Integer paymentStateId) {
    this.paymentStateId = paymentStateId;
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
