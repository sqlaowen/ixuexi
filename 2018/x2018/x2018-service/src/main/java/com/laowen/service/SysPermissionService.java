package com.laowen.service;

import com.laowen.entity.SysPermission;

import java.util.List;

public interface SysPermissionService {

    /**
     * 根据id取资源
     *
     * @param permId
     * @return
     */
    SysPermission findById(String permId);

    /**
     * 取资源
     *
     * @param sysPermission
     * @return
     */
    List<SysPermission> findList(SysPermission sysPermission);

    /**
     * 取菜单
     *
     * @return
     */
    List<SysPermission> findMenu();

    /**
     * 取左侧菜单树
     *
     * @param userId
     * @return
     */
    List<SysPermission> findLeftTreeMenu(String userId);

    /**
     * 取权限
     *
     * @param menuId
     * @return
     */
    List<SysPermission> findPermByMenuId(String menuId);

    /**
     * 根据角色id取资源
     *
     * @param roleId
     * @return
     */
    List<SysPermission> findResourceByRoleId(String roleId);

    /**
     * 根据userId查询权限list
     *
     * @param userId
     * @return
     */
    List<SysPermission> findListPermByUserId(String userId);

    /**
     * 保存
     *
     * @param sysPermission
     * @return
     */
    String save(SysPermission sysPermission);

    /**
     * 更新
     *
     * @param sysPermission
     * @return
     */
    int editById(SysPermission sysPermission);

}
