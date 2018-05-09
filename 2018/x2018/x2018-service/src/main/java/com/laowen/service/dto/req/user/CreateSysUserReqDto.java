package com.laowen.service.dto.req.user;

import com.laowen.service.dto.base.SysUserBase;

import java.util.Date;

public class CreateSysUserReqDto extends SysUserBase {

    private static final long serialVersionUID = 986322614177581168L;

    // create_time
    private Date createTime;

    // create_user
    private String createUser;

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

}
