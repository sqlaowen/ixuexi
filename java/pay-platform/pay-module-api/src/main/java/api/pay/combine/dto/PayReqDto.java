package api.pay.combine.dto;

import java.io.Serializable;
import java.util.List;

public class PayReqDto implements Serializable {

  private static final long serialVersionUID = -2643947010608903508L;

  public PayReqDto() {}

  private String merchantId;// 平台商户id
  private String gatewayCode;// 支付网关编码 [com.pay.consts.GatewayCodeConst]
  private String returnUrl;// 支付返回url
  private String notifyUrl;// 支付通知url
  private String fromIp;// 来源ip
  private String paymentSource;// 支付单来源 [com.pay.consts.PaymentSourceConst]
  private String bankCode;// 银行简码
  private String version;// 版本[默认: 1.0]
  private String openid;// 微信openid,JSAPI支付时使用
  private List<PayItemReqDto> payItemReqList;// 支付子项

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
   * 支付网关编码
   * 
   * @see com.pay.consts.GatewayCodeConst
   * @return
   */
  public String getGatewayCode() {
    return gatewayCode;
  }

  /**
   * 支付网关编码
   * 
   * @see com.pay.consts.GatewayCodeConst
   * @param gatewayCode
   */
  public void setGatewayCode(String gatewayCode) {
    this.gatewayCode = gatewayCode == null ? null : gatewayCode.trim();
  }

  /**
   * 支付返回url
   *
   * @return
   */
  public String getReturnUrl() {
    return returnUrl;
  }

  /**
   * 支付返回url
   *
   * @param returnUrl
   */
  public void setReturnUrl(String returnUrl) {
    this.returnUrl = returnUrl == null ? null : returnUrl.trim();
  }

  /**
   * 支付通知url
   *
   * @return
   */
  public String getNotifyUrl() {
    return notifyUrl;
  }

  /**
   * 支付通知url
   *
   * @param notifyUrl
   */
  public void setNotifyUrl(String notifyUrl) {
    this.notifyUrl = notifyUrl == null ? null : notifyUrl.trim();
  }

  /**
   * 来源ip
   *
   * @return
   */
  public String getFromIp() {
    return fromIp;
  }

  /**
   * 来源ip
   *
   * @param fromIp
   */
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

  /**
   * 银行简码
   *
   * @return
   */
  public String getBankCode() {
    return bankCode;
  }

  /**
   * 银行简码
   *
   * @param bankCode
   */
  public void setBankCode(String bankCode) {
    this.bankCode = bankCode == null ? null : bankCode.trim();
  }

  /**
   * 版本[默认: 1.0]
   * 
   * @return
   */
  public String getVersion() {
    return version;
  }

  /**
   * 版本[默认: 1.0]
   * 
   * @param version
   */
  public void setVersion(String version) {
    this.version = version;
  }

  /**
   * 微信openid,JSAPI支付时使用
   * 
   * @return
   */
  public String getOpenid() {
    return openid;
  }

  /**
   * 微信openid,JSAPI支付时使用
   * 
   * @param openid
   */
  public void setOpenid(String openid) {
    this.openid = openid;
  }

  /**
   * 支付子项
   * 
   * @return
   */
  public List<PayItemReqDto> getPayItemReqList() {
    return payItemReqList;
  }

  /**
   * 支付子项
   * 
   * @param payItemReqList
   */
  public void setPayItemReqList(List<PayItemReqDto> payItemReqList) {
    this.payItemReqList = payItemReqList;
  }

}
