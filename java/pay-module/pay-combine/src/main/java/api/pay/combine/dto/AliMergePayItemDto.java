package api.pay.combine.dto;

import java.io.Serializable;
import java.util.Date;

public class AliMergePayItemDto implements Serializable {

  private static final long serialVersionUID = 2288151095582071164L;

  private String orderId;

  private Long totalFee;

  private String spName;

  private String spDetail;

  private Date timeOut;

  private String extraCommonParam;

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

  public String getExtraCommonParam() {
    return extraCommonParam;
  }

  public void setExtraCommonParam(String extraCommonParam) {
    this.extraCommonParam = extraCommonParam == null ? null : extraCommonParam.trim();
  }

}
