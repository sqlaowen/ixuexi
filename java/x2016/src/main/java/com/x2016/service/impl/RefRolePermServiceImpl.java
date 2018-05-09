package com.x2016.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.x2016.mapper.RefRolePermMapper;
import com.x2016.service.RefRolePermService;

@Service
public class RefRolePermServiceImpl implements RefRolePermService {

  @Autowired
  private RefRolePermMapper refRolePermMapper;
  
  @Override
  public int delByRoleId(String roleId) {
    return refRolePermMapper.delByRoleId(roleId);
  }

  @Override
  public int saveBatch(String roleId,List<String> permIdList) {
    refRolePermMapper.delByRoleId(roleId);
    List<Map<String,Object>> mapList=new ArrayList<>();
    Map<String,Object> map=null;
    for (String str : permIdList) {
      map=new HashMap<>();
      map.put("refId", UUID.randomUUID().toString().replaceAll("-", ""));
      map.put("roleId", roleId);
      map.put("permId", str);
      mapList.add(map);
    }
    return refRolePermMapper.saveBatch(mapList);
  }

}