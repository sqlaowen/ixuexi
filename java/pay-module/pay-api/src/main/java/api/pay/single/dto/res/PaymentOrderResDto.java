package api.pay.single.dto.res;

import java.io.Serializable;
import java.util.Date;

import api.pay.single.dto.base.PaymentOrderBase;

public class PaymentOrderResDto extends PaymentOrderBase implements Serializable {

  private static final long serialVersionUID = -6095146847080510593L;

  private Integer paymentOrderId;

  private Date refCreate;

  public Integer getPaymentOrderId() {
    return paymentOrderId;
  }

  public void setPaymentOrderId(Integer paymentOrderId) {
    this.paymentOrderId = paymentOrderId;
  }

  public Date getRefCreate() {
    return refCreate;
  }

  public void setRefCreate(Date refCreate) {
    this.refCreate = refCreate;
  }
}
