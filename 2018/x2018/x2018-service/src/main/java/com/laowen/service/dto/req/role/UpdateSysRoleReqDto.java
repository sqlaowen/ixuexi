package com.laowen.service.dto.req.role;

import com.laowen.service.dto.base.SysRoleBase;

import java.util.Date;

public class UpdateSysRoleReqDto extends SysRoleBase {

    private static final long serialVersionUID = -7615212146838224916L;

    // 角色id
    private String roleId;

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
