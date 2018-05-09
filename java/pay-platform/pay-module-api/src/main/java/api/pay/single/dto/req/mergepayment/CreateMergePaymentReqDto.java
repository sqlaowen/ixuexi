package api.pay.single.dto.req.mergepayment;

import java.io.Serializable;

import api.pay.single.dto.base.MergePaymentBase;

public class CreateMergePaymentReqDto extends MergePaymentBase implements Serializable {

  private static final long serialVersionUID = 7836743228333044197L;

  public CreateMergePaymentReqDto() {}

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
