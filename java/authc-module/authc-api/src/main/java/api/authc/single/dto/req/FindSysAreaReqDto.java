package api.authc.single.dto.req;

import java.util.Date;

import api.authc.single.dto.base.SysAreaBase;

public class FindSysAreaReqDto extends SysAreaBase {

  private static final long serialVersionUID = 4187639686344044167L;

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
