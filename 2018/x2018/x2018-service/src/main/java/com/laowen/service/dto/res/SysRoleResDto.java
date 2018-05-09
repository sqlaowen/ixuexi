package com.laowen.service.dto.res;

import com.laowen.service.dto.base.SysRoleBase;

import java.util.Date;

public class SysRoleResDto extends SysRoleBase {

    private static final long serialVersionUID = 986322614177581168L;

    // 角色id
    private String roleId;

    // create_time
    private Date createTime;

    // create_user
    private String createUser;

    // update_time
    private Date updateTime;

    // update_user
    private String updateUser;

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
}
