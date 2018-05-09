package api.authc.single.dto.base;

import java.io.Serializable;

public abstract class RefUserRoleBase implements Serializable {
   
  private static final long serialVersionUID = 5084735311000575844L;

  // 用户ID
  private String userId;

  // 角色id
  private String roleId;

  /**
   * 用户ID
   *
   * @return
   */
  public String getUserId() {
    return userId;
  }

  /**
   * 用户ID
   *
   * @param userId
   */
  public void setUserId(String userId) {
    this.userId = userId == null ? null : userId.trim();
  }

  /**
   * 角色id
   *
   * @return
   */
  public String getRoleId() {
    return roleId;
  }

  /**
   * 角色id
   *
   * @param roleId
   */
  public void setRoleId(String roleId) {
    this.roleId = roleId == null ? null : roleId.trim();
  }

}