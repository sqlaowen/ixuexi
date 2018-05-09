package com.laowen.service;

import com.laowen.entity.SysUser;
import com.laowen.PageRequest;
import com.laowen.PageResponse;

public interface SysUserService {

    /**
     * 根据id查询
     *
     * @param userId
     * @return
     */
    SysUser findById(String userId);

    /**
     * 根据userAccount查询
     *
     * @param userAccount
     * @return
     */
    SysUser findByUserAccount(String userAccount);

    /**
     * 分页查询
     *
     * @param sysUserPageRequest
     * @return
     */
    PageResponse<SysUser> findList(PageRequest<SysUser> sysUserPageRequest);

    /**
     * 保存
     *
     * @param sysUser
     * @return
     */
    String save(SysUser sysUser);

    /**
     * 更新
     *
     * @param sysUser
     * @return
     */
    int editById(SysUser sysUser);
}
