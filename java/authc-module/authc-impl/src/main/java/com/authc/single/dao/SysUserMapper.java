package com.authc.single.dao;

import java.util.List;

import com.authc.single.bean.SysUser;

public interface SysUserMapper {

  SysUser findById(String roleId);
  
  SysUser findByUserAccount(String userAccount);

  List<SysUser> findList(SysUser record);

  int save(SysUser record);

  int editById(SysUser record);

}
