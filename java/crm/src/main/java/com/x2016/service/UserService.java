package com.x2016.service;

import com.x2016.poco.SysUser;

import api.authc.single.dto.PageReq;
import api.authc.single.dto.PageRes;

public interface UserService {

  SysUser findById(String roleId);
  
  SysUser findByUserAccount(String userAccount);

  PageRes<SysUser> findList(PageReq<SysUser> req);

  String save(SysUser record);

  int editById(SysUser record);
}
