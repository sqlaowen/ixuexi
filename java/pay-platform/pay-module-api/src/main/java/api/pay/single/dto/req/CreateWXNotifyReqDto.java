package api.pay.single.dto.req;

import java.io.Serializable;
import java.util.Collection;

import api.pay.single.dto.base.WXNotifyBase;

public class CreateWXNotifyReqDto extends WXNotifyBase implements Serializable {

  private static final long serialVersionUID = -1319730660602062423L;

  public CreateWXNotifyReqDto() {}

  // create_user
  private String createUser;

  private Collection<CreateWXCouponReqDto> wxCouponList;

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

  public Collection<CreateWXCouponReqDto> getWxCouponList() {
    return wxCouponList;
  }

  public void setWxCouponList(Collection<CreateWXCouponReqDto> wxCouponList) {
    this.wxCouponList = wxCouponList;
  }
}
