package api.pay.combine.dto;

import java.io.Serializable;
import java.util.List;

public class AliMergePayDto implements Serializable {

  private static final long serialVersionUID = 2288151095582071164L;

  private String returnUrl;

  private String notifyUrl;

  private String fromIp;

  private String paymentSource;//支付单来源
  
  private List<AliMergePayItemDto> itemList;

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

  public List<AliMergePayItemDto> getItemList() {
    return itemList;
  }

  public void setItemList(List<AliMergePayItemDto> itemList) {
    this.itemList = itemList;
  }

}
