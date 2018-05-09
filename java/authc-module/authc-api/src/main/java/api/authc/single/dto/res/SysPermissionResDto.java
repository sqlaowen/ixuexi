package api.authc.single.dto.res;

import java.util.Date;

import api.authc.single.dto.base.SysPermissionBase;

public class SysPermissionResDto extends SysPermissionBase {

  private static final long serialVersionUID = 986322614177581168L;

  // 资源id
  private String permId;

  // create_time
  private Date createTime;

  // create_user
  private String createUser;

  // update_time
  private Date updateTime;

  // update_user
  private String updateUser;

//  private List<SysPermissionBase> childs;

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

//  public List<SysPermission> getChilds() {
//    return childs;
//  }
//
//  public void setChilds(List<SysPermission> childs) {
//    this.childs = childs;
//  }
}
