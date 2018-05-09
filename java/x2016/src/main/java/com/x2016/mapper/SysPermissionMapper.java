package com.x2016.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.x2016.poco.SysPermission;

public interface SysPermissionMapper {

  SysPermission findById(String permId);

  List<SysPermission> findList(SysPermission record);

  List<SysPermission> findMenu();
  
  //取左侧菜单树
  List<SysPermission> findLeftTreeMenu(@Param("userId") String userId);

  List<SysPermission> findPermByMenuId(String menuId);
  
  /**
   * 根据userId查询资源list
   * @param userId
   * @return
   */
  List<SysPermission> findListPermByUserId(@Param("userId") String userId);
  
  //根据角色id取资源
  List<SysPermission> findResourceByRoleId(String roleId);

  int save(SysPermission record);

  int editById(SysPermission record);
}
