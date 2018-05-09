package com.x2016.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.x2016.mapper.RefUserRoleMapper;
import com.x2016.service.RefUserRoleService;

@Service
public class RefUserRoleServiceImpl implements RefUserRoleService {

  @Autowired
  private RefUserRoleMapper refUserRoleMapper;
  
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