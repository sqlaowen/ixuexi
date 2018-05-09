package api.pay.single.dto.res;

import java.io.Serializable;
import java.util.Date;

import api.pay.single.dto.base.PaymentOrderBase;

public class PaymentOrderResDto extends PaymentOrderBase implements Serializable {

  private static final long serialVersionUID = -6095146847080510593L;

  public PaymentOrderResDto() {}

  // 订单ref支付单id
  private String paymentOrderId;

  // create_time
  private Date createTime;

  // create_user
  private String createUser;

  /**
   * 订单ref支付单id
   *
   * @return
   */
  public String getPaymentOrderId() {
    return paymentOrderId;
  }

  /**
   * 订单ref支付单id
   *
   * @param paymentOrderId
   */
  public void setPaymentOrderId(String paymentOrderId) {
    this.paymentOrderId = paymentOrderId == null ? null : paymentOrderId.trim();
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
}
