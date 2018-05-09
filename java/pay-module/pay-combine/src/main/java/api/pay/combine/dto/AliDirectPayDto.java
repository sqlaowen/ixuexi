package api.pay.combine.dto;

import java.io.Serializable;
import java.util.Date;

public class AliDirectPayDto implements Serializable {

  private static final long serialVersionUID = 2288151095582071164L;

  private String orderId;

  private Long totalFee;

  private String spName;

  private String spDetail;

  private Date timeOut;

  private String returnUrl;

  private String notifyUrl;

  private String extraCommonParam;

  private String fromIp;

  private String paymentSource;//支付单来源

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId == null ? null : orderId.trim();
  }

  public Long getTotalFee() {
    return totalFee;
  }

  public void setTotalFee(Long totalFee) {
    this.totalFee = totalFee;
  }

  public String getSpName() {
    return spName;
  }

  public void setSpName(String spName) {
    this.spName = spName == null ? null : spName.trim();
  }

  public String getSpDetail() {
    return spDetail;
  }

  public void setSpDetail(String spDetail) {
    this.spDetail = spDetail == null ? null : spDetail.trim();
  }

  public Date getTimeOut() {
    return timeOut;
  }

  public void setTimeOut(Date timeOut) {
    this.timeOut = timeOut;
  }

  public String getReturnUrl() {
    return returnUrl;
  }

  public void setReturnUrl(String returnUrl) {
    this.returnUrl = returnUrl == null ? null : returnUrl.trim();
  }

  public String getNotifyUrl() {
    return notifyUrl;
  }

  public void setNotifyUrl(String notifyUrl) {
    this.notifyUrl = notifyUrl == null ? null : notifyUrl.trim();
  }

  public String getExtraCommonParam() {
    return extraCommonParam;
  }

  public void setExtraCommonParam(String extraCommonParam) {
    this.extraCommonParam = extraCommonParam == null ? null : extraCommonParam.trim();
  }

  public String getFromIp() {
    return fromIp;
  }

  public void setFromIp(String fromIp) {
    this.fromIp = fromIp == null ? null : fromIp.trim();
  }

  /**
   * 支付单来源
   * 
   * @see com.pay.consts.PaymentSourceConst
   * @return
   */
  public String getPaymentSource() {
    return paymentSource;
  }

  /**
   * 支付单来源
   * 
   * @see com.pay.consts.PaymentSourceConst
   * @param paymentSource
   */
  public void setPaymentSource(String paymentSource) {
    this.paymentSource = paymentSource == null ? null : paymentSource.trim();
  }

}
