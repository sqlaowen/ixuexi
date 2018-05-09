package com.x2016.service;

import java.util.List;

import com.x2016.poco.SysRole;
import com.x2016.util.PageReq;
import com.x2016.util.PageRes;

public interface SysRoleService {

  SysRole findById(String roleId);

  PageRes<SysRole> findList(PageReq<SysRole> req);
  
  /**
   * 通过用户id查询角色list
   * @param userId
   * @return
   */
  List<SysRole> findListByUserId(String userId);

  int save(SysRole record);

  int editById(SysRole record);
}
