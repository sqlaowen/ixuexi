package api.authc.single.dto.base;

import java.io.Serializable;

public abstract class SysUserBase implements Serializable {
  
  private static final long serialVersionUID = 678761302615803533L;

  // 用户姓名
  private String userName;

  // 账号
  private String userAccount;

  // 密码
  private String userPwd;

  // 性别[0:男, 1:女]
  private Integer userSex;

  // 手机号
  private String userPhone;

  // 状态[0:可用, 1:不可用]
  private Integer userStatus;

  // 备注
  private String userNote;

 
  /**
   * 用户姓名
   *
   * @return
   */
  public String getUserName() {
    return userName;
  }

  /**
   * 用户姓名
   *
   * @param userName
   */
  public void setUserName(String userName) {
    this.userName = userName == null ? null : userName.trim();
  }

  /**
   * 账号
   *
   * @return
   */
  public String getUserAccount() {
    return userAccount;
  }

  /**
   * 账号
   *
   * @param userAccount
   */
  public void setUserAccount(String userAccount) {
    this.userAccount = userAccount == null ? null : userAccount.trim();
  }

  /**
   * 密码
   *
   * @return
   */
  public String getUserPwd() {
    return userPwd;
  }

  /**
   * 密码
   *
   * @param userPwd
   */
  public void setUserPwd(String userPwd) {
    this.userPwd = userPwd == null ? null : userPwd.trim();
  }

  /**
   * 性别[0:男, 1:女]
   *
   * @return
   */
  public Integer getUserSex() {
    return userSex;
  }

  /**
   * 性别[0:男, 1:女]
   *
   * @param userSex
   */
  public void setUserSex(Integer userSex) {
    this.userSex = userSex;
  }

  /**
   * 手机号
   *
   * @return
   */
  public String getUserPhone() {
    return userPhone;
  }

  /**
   * 手机号
   *
   * @param userPhone
   */
  public void setUserPhone(String userPhone) {
    this.userPhone = userPhone == null ? null : userPhone.trim();
  }

  /**
   * 状态[0:可用, 1:不可用]
   *
   * @return
   */
  public Integer getUserStatus() {
    return userStatus;
  }

  /**
   * 状态[0:可用, 1:不可用]
   *
   * @param userStatus
   */
  public void setUserStatus(Integer userStatus) {
    this.userStatus = userStatus;
  }

  /**
   * 备注
   *
   * @return
   */
  public String getUserNote() {
    return userNote;
  }

  /**
   * 备注
   *
   * @param userNote
   */
  public void setUserNote(String userNote) {
    this.userNote = userNote == null ? null : userNote.trim();
  }

}