package com.x2016.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.x2016.service.RoleRefPermService;

import api.authc.single.service.RefRolePermService;

@Service
public class RolePermRefServiceImpl implements RoleRefPermService {

  @Autowired
  private RefRolePermService refRolePermService;
  
  @Override
  public int delByRoleId(String roleId) {
    return refRolePermService.delByRoleId(roleId);
  }

  @Override
  public int saveBatch(String roleId,List<String> permIdList) {
    return refRolePermService.saveBatch(roleId,permIdList);
  }

}