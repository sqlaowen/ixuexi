package com.x2016.service;

import com.x2016.poco.SysUser;
import com.x2016.util.PageReq;
import com.x2016.util.PageRes;

public interface SysUserService {

  SysUser findById(String roleId);
  
  SysUser findByUserAccount(String userAccount);

  PageRes<SysUser> findList(PageReq<SysUser> req);

  int save(SysUser record);

  int editById(SysUser record);
}
