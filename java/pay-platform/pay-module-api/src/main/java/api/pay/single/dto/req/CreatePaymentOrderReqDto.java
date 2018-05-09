package api.pay.single.dto.req;

import java.io.Serializable;

import api.pay.single.dto.base.PaymentOrderBase;

public class CreatePaymentOrderReqDto extends PaymentOrderBase implements Serializable {

  private static final long serialVersionUID = -6095146847080510593L;

  public CreatePaymentOrderReqDto() {}

  // create_user
  private String createUser;


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
