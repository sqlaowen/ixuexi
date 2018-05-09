package com.laowen.bean;

import java.io.Serializable;
import java.util.Date;

public class SysUser implements Serializable {

    private static final long serialVersionUID = 4669383843449927237L;

    // 用户ID
    private String userId;

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

    // create_time
    private Date createTime;

    // create_user
    private String createUser;

    // update_time
    private Date updateTime;

    // update_user
    private String updateUser;

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

    @Override
    public String toString() {
        return "SysUser{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userAccount='" + userAccount + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", userSex=" + userSex +
                ", userPhone='" + userPhone + '\'' +
                ", userStatus=" + userStatus +
                ", userNote='" + userNote + '\'' +
                ", createTime=" + createTime +
                ", createUser='" + createUser + '\'' +
                ", updateTime=" + updateTime +
                ", updateUser='" + updateUser + '\'' +
                '}';
    }
}
