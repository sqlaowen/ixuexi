package com.authc.single.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.authc.single.dao.RefUserRoleMapper;

import api.authc.single.service.RefUserRoleService;

public class RefUserRoleServiceImpl implements RefUserRoleService {

  private RefUserRoleMapper refUserRoleMapper;
  
  public void setRefUserRoleMapper(RefUserRoleMapper refUserRoleMapper) {
    this.refUserRoleMapper = refUserRoleMapper;
  }

  @Override
  public int delByUserId(String userId) {
    return refUserRoleMapper.delByUserId(userId);
  }

  @Override
  public int saveBatch(String userId, List<String> roleIdList) {
    refUserRoleMapper.delByUserId(userId);
    
    List<Map<String,Object>> mapList=new ArrayList<>();
    Map<String,Object> map=null;
    for (String str : roleIdList) {
      map=new HashMap<>();
      map.put("refId", UUID.randomUUID().toString().replaceAll("-", ""));
      map.put("userId", userId);
      map.put("roleId", str);
      mapList.add(map);
    }
    return refUserRoleMapper.saveBatch(mapList);
  }

}