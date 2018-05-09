package api.pay.single.dto.req.payment;

import java.io.Serializable;
import java.util.Date;

import api.pay.single.dto.base.PaymentBase;

public class EditPaymentReqDto extends PaymentBase implements Serializable {

  private static final long serialVersionUID = -2789565424056821233L;

  public EditPaymentReqDto() {}

  // 支付单id
  private String paymentId;

  // 支付单状态id
  private Integer paymentStateId;

  // update_user
  private String updateUser;

  // 订单关闭时间
  private Date orderCloseTime;

  // 实际支付时间
  private Date tradeTime;

  // 交易关闭时间
  private Date tradeCloseTime;

  // 第三方交易号(ali/wechat流水号)
  private String thirdTradeNo;

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


  /**
   * update_user
   *
   * @return
   */
  public String getUpdateUser() {
    return updateUser;
  }

  /**
   * update_user
   *
   * @param updateUser
   */
  public void setUpdateUser(String updateUser) {
    this.updateUser = updateUser == null ? null : updateUser.trim();
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
