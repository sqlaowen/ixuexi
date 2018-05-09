package com.x2016.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.x2016.service.UserRefRoleService;

import api.authc.single.service.RefUserRoleService;

@Service
public class UserRefRoleServiceImpl implements UserRefRoleService {

  @Autowired
  private RefUserRoleService refUserRoleService;
  
  @Override
  public int delByUserId(String userId) {
    return refUserRoleService.delByUserId(userId);
  }

  @Override
  public int saveBatch(String userId, List<String> roleIdList) {
    return refUserRoleService.saveBatch(userId, roleIdList);
  }

}