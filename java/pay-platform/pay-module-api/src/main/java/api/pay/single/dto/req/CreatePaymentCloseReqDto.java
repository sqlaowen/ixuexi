package api.pay.single.dto.req;

import java.io.Serializable;

import api.pay.single.dto.base.PaymentCloseBase;

public class CreatePaymentCloseReqDto extends PaymentCloseBase implements Serializable {

  private static final long serialVersionUID = -8077798628359488421L;

  public CreatePaymentCloseReqDto() {}

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
