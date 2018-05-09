package api.pay.single.dto.req;

import java.io.Serializable;

import api.pay.single.dto.base.PaymentRepeatBase;

public class CreatePaymentRepeatReqDto extends PaymentRepeatBase implements Serializable {

  private static final long serialVersionUID = 7697700251602794801L;

  public CreatePaymentRepeatReqDto() {}

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
