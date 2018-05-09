package com.x2016.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.x2016.mapper.SysPermissionMapper;
import com.x2016.poco.SysPermission;
import com.x2016.service.SysPermissionService;

@Service
public class SysPermissionServiceImpl implements SysPermissionService {

  @Autowired
  private SysPermissionMapper sysPermMapper;
  
  @Override
  public SysPermission findById(String permId) {
    return sysPermMapper.findById(permId);
  }

  @Override
  public List<SysPermission> findList(SysPermission record) {
    return sysPermMapper.findList(record);
  }

  @Override
  public List<SysPermission> findMenu() {
    return sysPermMapper.findMenu();
  }
  
  @Override
  public List<SysPermission> findLeftTreeMenu(String userId) {
    return sysPermMapper.findLeftTreeMenu(userId);
  }

  @Override
  public List<SysPermission> findPermByMenuId(String menuId) {
    return sysPermMapper.findPermByMenuId(menuId);
  }

  @Override
  public int save(SysPermission record) {
    return sysPermMapper.save(record);
  }

  @Override
  public int editById(SysPermission record) {
    return sysPermMapper.editById(record);
  }

  @Override
  public List<SysPermission> findResourceByRoleId(String roleId) {
    return sysPermMapper.findResourceByRoleId(roleId);
  }

  /**
   * 根据userId查询资源list
   * @param userId
   * @return
   */
  @Override
  public List<SysPermission> findListPermByUserId(String userId) {
    return sysPermMapper.findListPermByUserId(userId);
  }

}
