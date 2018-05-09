package api.authc.single.dto.req.perm;

import java.util.Date;

import api.authc.single.dto.base.SysPermissionBase;

public class UpdateSysPermissionReqDto extends SysPermissionBase {

  private static final long serialVersionUID = -266199995956589020L;
  
  // 资源id
  private String permId;

  // update_time
  private Date updateTime;

  // update_user
  private String updateUser;

  /**
   * 资源id
   *
   * @return
   */
  public String getPermId() {
    return permId;
  }

  /**
   * 资源id
   *
   * @param permId
   */
  public void setPermId(String permId) {
    this.permId = permId == null ? null : permId.trim();
  }

  /**
   * update_time
   *
   * @return
   */
  public Date getUpdateTime() {
    return updateTime;
  }

  /**
   * update_time
   *
   * @param updateTime
   */
  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  /**
   * update_user
   *
   * @return
   */
  public String getUpdateUser() {
    return updateUser;
  }

  /**
   * update_user
   *
   * @param updateUser
   */
  public void setUpdateUser(String updateUser) {
    this.updateUser = updateUser == null ? null : updateUser.trim();
  }

}
