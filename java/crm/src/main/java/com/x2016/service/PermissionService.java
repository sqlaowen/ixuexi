package com.x2016.service;

import java.util.List;

import com.x2016.poco.SysPermission;

public interface PermissionService {

  //根据id取资源
  SysPermission findById(String permId);

  //取资源
  List<SysPermission> findList(SysPermission record);

  //取菜单
  List<SysPermission> findMenu();
  
  //取左侧菜单树
  List<SysPermission> findLeftTreeMenu(String userId);

  //取权限
  List<SysPermission> findPermByMenuId(String menuId);
  
//根据角色id取资源
  List<SysPermission> findResourceByRoleId(String roleId);
  
  /**
   * 根据userId查询权限list
   * @param userId
   * @return
   */
  List<SysPermission> findListPermByUserId(String userId);
  

  String save(SysPermission record);

  int editById(SysPermission record);
}
