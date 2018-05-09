package com.x2016.service;

import java.util.List;

import com.x2016.poco.SysRole;

import api.authc.single.dto.PageReq;
import api.authc.single.dto.PageRes;

public interface RoleService {

  SysRole findById(String roleId);

  PageRes<SysRole> findList(PageReq<SysRole> req);
  
  /**
   * 通过用户id查询角色list
   * @param userId
   * @return
   */
  List<SysRole> findListByUserId(String userId);

  String save(SysRole record);

  int editById(SysRole record);
}
