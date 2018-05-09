package api.authc.single.dto.req.role;

import java.util.Date;

import api.authc.single.dto.base.SysRoleBase;

public class CreateSysRoleReqDto extends SysRoleBase {

  private static final long serialVersionUID = -7615212146838224916L;

  // create_time
  private Date createTime;

  // create_user
  private String createUser;

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
