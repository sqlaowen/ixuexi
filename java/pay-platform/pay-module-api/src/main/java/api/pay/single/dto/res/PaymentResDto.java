package api.pay.single.dto.res;

import java.io.Serializable;
import java.util.Date;

import api.pay.single.dto.base.PaymentBase;

public class PaymentResDto extends PaymentBase implements Serializable {

  private static final long serialVersionUID = 7925415764227586165L;

  public PaymentResDto() {}

  // 支付单id
  private String paymentId;

  // 支付单状态id
  private Integer paymentStateId;

  // create_time
  private Date createTime;

  // create_user
  private String createUser;

  // update_time
  private Date updateTime;

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


  /**
   * create_time
   *
   * @return
   */
  public Date getCreateTime() {
    return createTime;
  }

  /**
   * create_time
   *
   * @param createTime
   */
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  /**
   * create_user
   *
   * @return
   */
  public String getCreateUser() {
    return createUser;
  }

  /**
   * create_user
   *
   * @param createUser
   */
  public void setCreateUser(String createUser) {
    this.createUser = createUser == null ? null : createUser.trim();
  }

  /**
   * update_time
   *
   * @return
   */
  public Date getUpdateTime() {
    return updateTime;
  }

  /**
   * update_time
   *
   * @param updateTime
   */
  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
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
