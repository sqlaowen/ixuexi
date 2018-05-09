package api.pay.single.dto.res;

import java.util.Date;

import api.pay.single.dto.base.PaymentLogBase;

public class PaymentLogResDto extends PaymentLogBase {

  private static final long serialVersionUID = 4632195513312694365L;

  private Integer paymentLogId;

  private Date createTime;

  public Integer getPaymentLogId() {
    return paymentLogId;
  }

  public void setPaymentLogId(Integer paymentLogId) {
    this.paymentLogId = paymentLogId;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }
}
