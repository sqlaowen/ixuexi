package api.authc.single.dto.base;

import java.io.Serializable;

public abstract class RefRolePermBase implements Serializable {
   
  private static final long serialVersionUID = -661064917907593907L;

  // 资源id
  private String permId;

  // 角色id
  private String roleId;

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