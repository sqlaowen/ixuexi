package com.laowen.bean;

import java.io.Serializable;
import java.util.Date;

public class RefRolePerm implements Serializable {

    private static final long serialVersionUID = 4669383843449927237L;

    // ref_id
    private String refId;

    // 资源id
    private String permId;

    // 角色id
    private String roleId;

    // create_time
    private Date createTime;

    // create_user
    private String createUser;

    /**
     * ref_id
     *
     * @return
     */
    public String getRefId() {
        return refId;
    }

    /**
     * ref_id
     *
     * @param refId
     */
    public void setRefId(String refId) {
        this.refId = refId == null ? null : refId.trim();
    }

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

    @Override
    public String toString() {
        return "RefRolePerm{" +
                "refId='" + refId + '\'' +
                ", permId='" + permId + '\'' +
                ", roleId='" + roleId + '\'' +
                ", createTime=" + createTime +
                ", createUser='" + createUser + '\'' +
                '}';
    }

}
