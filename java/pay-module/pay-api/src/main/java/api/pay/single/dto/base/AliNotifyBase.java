package api.pay.single.dto.base;

import java.io.Serializable;
import java.util.Date;

public abstract class AliNotifyBase implements Serializable {

  private static final long serialVersionUID = 3035945767175361125L;

  private String paymentId;

  private Date notifyTime;

  private String notifyType;

  private String notifyId;

  private String signType;

  private String sign;

  private String subject;

  private String paymentType;

  private String tradeNo;

  private String tradeStatus;

  private Date gmtCreate;

  private Date gmtPayment;

  private Date gmtClose;

  private String refundStatus;

  private Date gmtRefund;

  private String sellerEmail;

  private String buyerEmail;

  private String sellerId;

  private String buyerId;

  private Long price;

  private Long totalFee;

  private Integer quantity;

  private String body;

  private Integer discount;

  private String isTotalFeeAdjust;

  private String useCoupon;

  private String errorCode;

  private String bankSeqNo;

  private String extraCommonParam;

  private String outChannelType;

  private String outChannelAmount;

  private String outChannelInst;

  private String notifyValue;

  public String getPaymentId() {
    return paymentId;
  }

  public void setPaymentId(String paymentId) {
    this.paymentId = paymentId == null ? null : paymentId.trim();
  }

  public Date getNotifyTime() {
    return notifyTime;
  }

  public void setNotifyTime(Date notifyTime) {
    this.notifyTime = notifyTime;
  }

  public String getNotifyType() {
    return notifyType;
  }

  public void setNotifyType(String notifyType) {
    this.notifyType = notifyType == null ? null : notifyType.trim();
  }

  public String getNotifyId() {
    return notifyId;
  }

  public void setNotifyId(String notifyId) {
    this.notifyId = notifyId == null ? null : notifyId.trim();
  }

  public String getSignType() {
    return signType;
  }

  public void setSignType(String signType) {
    this.signType = signType == null ? null : signType.trim();
  }

  public String getSign() {
    return sign;
  }

  public void setSign(String sign) {
    this.sign = sign == null ? null : sign.trim();
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject == null ? null : subject.trim();
  }

  public String getPaymentType() {
    return paymentType;
  }

  public void setPaymentType(String paymentType) {
    this.paymentType = paymentType == null ? null : paymentType.trim();
  }

  public String getTradeNo() {
    return tradeNo;
  }

  public void setTradeNo(String tradeNo) {
    this.tradeNo = tradeNo == null ? null : tradeNo.trim();
  }

  public String getTradeStatus() {
    return tradeStatus;
  }

  public void setTradeStatus(String tradeStatus) {
    this.tradeStatus = tradeStatus == null ? null : tradeStatus.trim();
  }

  public Date getGmtCreate() {
    return gmtCreate;
  }

  public void setGmtCreate(Date gmtCreate) {
    this.gmtCreate = gmtCreate;
  }

  public Date getGmtPayment() {
    return gmtPayment;
  }

  public void setGmtPayment(Date gmtPayment) {
    this.gmtPayment = gmtPayment;
  }

  public Date getGmtClose() {
    return gmtClose;
  }

  public void setGmtClose(Date gmtClose) {
    this.gmtClose = gmtClose;
  }

  public String getRefundStatus() {
    return refundStatus;
  }

  public void setRefundStatus(String refundStatus) {
    this.refundStatus = refundStatus == null ? null : refundStatus.trim();
  }

  public Date getGmtRefund() {
    return gmtRefund;
  }

  public void setGmtRefund(Date gmtRefund) {
    this.gmtRefund = gmtRefund;
  }

  public String getSellerEmail() {
    return sellerEmail;
  }

  public void setSellerEmail(String sellerEmail) {
    this.sellerEmail = sellerEmail == null ? null : sellerEmail.trim();
  }

  public String getBuyerEmail() {
    return buyerEmail;
  }

  public void setBuyerEmail(String buyerEmail) {
    this.buyerEmail = buyerEmail == null ? null : buyerEmail.trim();
  }

  public String getSellerId() {
    return sellerId;
  }

  public void setSellerId(String sellerId) {
    this.sellerId = sellerId == null ? null : sellerId.trim();
  }

  public String getBuyerId() {
    return buyerId;
  }

  public void setBuyerId(String buyerId) {
    this.buyerId = buyerId == null ? null : buyerId.trim();
  }

  public Long getPrice() {
    return price;
  }

  public void setPrice(Long price) {
    this.price = price;
  }

  public Long getTotalFee() {
    return totalFee;
  }

  public void setTotalFee(Long totalFee) {
    this.totalFee = totalFee;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body == null ? null : body.trim();
  }

  public Integer getDiscount() {
    return discount;
  }

  public void setDiscount(Integer discount) {
    this.discount = discount;
  }

  public String getIsTotalFeeAdjust() {
    return isTotalFeeAdjust;
  }

  public void setIsTotalFeeAdjust(String isTotalFeeAdjust) {
    this.isTotalFeeAdjust = isTotalFeeAdjust == null ? null : isTotalFeeAdjust.trim();
  }

  public String getUseCoupon() {
    return useCoupon;
  }

  public void setUseCoupon(String useCoupon) {
    this.useCoupon = useCoupon == null ? null : useCoupon.trim();
  }

  public String getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode == null ? null : errorCode.trim();
  }

  public String getBankSeqNo() {
    return bankSeqNo;
  }

  public void setBankSeqNo(String bankSeqNo) {
    this.bankSeqNo = bankSeqNo == null ? null : bankSeqNo.trim();
  }

  public String getExtraCommonParam() {
    return extraCommonParam;
  }

  public void setExtraCommonParam(String extraCommonParam) {
    this.extraCommonParam = extraCommonParam == null ? null : extraCommonParam.trim();
  }

  public String getOutChannelType() {
    return outChannelType;
  }

  public void setOutChannelType(String outChannelType) {
    this.outChannelType = outChannelType == null ? null : outChannelType.trim();
  }

  public String getOutChannelAmount() {
    return outChannelAmount;
  }

  public void setOutChannelAmount(String outChannelAmount) {
    this.outChannelAmount = outChannelAmount == null ? null : outChannelAmount.trim();
  }

  public String getOutChannelInst() {
    return outChannelInst;
  }

  public void setOutChannelInst(String outChannelInst) {
    this.outChannelInst = outChannelInst == null ? null : outChannelInst.trim();
  }

  public String getNotifyValue() {
    return notifyValue;
  }

  public void setNotifyValue(String notifyValue) {
    this.notifyValue = notifyValue == null ? null : notifyValue.trim();
  }
}
