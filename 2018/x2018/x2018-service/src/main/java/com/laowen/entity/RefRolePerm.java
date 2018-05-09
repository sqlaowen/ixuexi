package com.laowen.entity;

import java.io.Serializable;

public class RefRolePerm implements Serializable {

    private static final long serialVersionUID = 1182627427708494356L;

    // ref_id
    private String refId;

    // 资源id
    private String permId;

    // 角色id
    private String roleId;

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

}