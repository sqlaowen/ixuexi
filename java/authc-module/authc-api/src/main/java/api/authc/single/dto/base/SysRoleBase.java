package api.authc.single.dto.base;

import java.io.Serializable;

public abstract class SysRoleBase implements Serializable {
  
  private static final long serialVersionUID = -4511805845135950682L;

  // role_name
  private String roleName;

  // role_code
  private String roleCode;

  // role_note
  private String roleNote;

  // role_status[0:可用, 1:不可用]
  private Integer roleStatus;

  
  /**
   * role_name
   *
   * @return
   */
  public String getRoleName() {
    return roleName;
  }

  /**
   * role_name
   *
   * @param roleName
   */
  public void setRoleName(String roleName) {
    this.roleName = roleName == null ? null : roleName.trim();
  }

  /**
   * role_code
   *
   * @return
   */
  public String getRoleCode() {
    return roleCode;
  }

  /**
   * role_code
   *
   * @param roleCode
   */
  public void setRoleCode(String roleCode) {
    this.roleCode = roleCode == null ? null : roleCode.trim();
  }

  /**
   * role_note
   *
   * @return
   */
  public String getRoleNote() {
    return roleNote;
  }

  /**
   * role_note
   *
   * @param roleNote
   */
  public void setRoleNote(String roleNote) {
    this.roleNote = roleNote == null ? null : roleNote.trim();
  }

  /**
   * role_status[0:可用, 1:不可用]
   *
   * @return
   */
  public Integer getRoleStatus() {
    return roleStatus;
  }

  /**
   * role_status[0:可用, 1:不可用]
   *
   * @param roleStatus
   */
  public void setRoleStatus(Integer roleStatus) {
    this.roleStatus = roleStatus;
  }

}