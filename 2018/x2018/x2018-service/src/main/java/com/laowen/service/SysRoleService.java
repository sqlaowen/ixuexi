package com.laowen.service;

import com.laowen.entity.SysRole;
import com.laowen.PageRequest;
import com.laowen.PageResponse;

import java.util.List;

public interface SysRoleService {

    /**
     * 根据id查询
     *
     * @param roleId
     * @return
     */
    SysRole findById(String roleId);

    /**
     * 分页查询
     *
     * @param sysRolePageRequest
     * @return
     */
    PageResponse<SysRole> findList(PageRequest<SysRole> sysRolePageRequest);

    /**
     * 通过用户id查询角色list
     *
     * @param userId
     * @return
     */
    List<SysRole> findListByUserId(String userId);

    /**
     * 保存
     *
     * @param sysRole
     * @return
     */
    String save(SysRole sysRole);

    /**
     * 更新
     *
     * @param sysRole
     * @return
     */
    int editById(SysRole sysRole);
}
