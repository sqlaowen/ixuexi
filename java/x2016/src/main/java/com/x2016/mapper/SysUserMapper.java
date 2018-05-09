package com.x2016.mapper;

import java.util.List;

import com.x2016.poco.SysUser;

public interface SysUserMapper {

  SysUser findById(String roleId);
  
  SysUser findByUserAccount(String userAccount);

  List<SysUser> findList(SysUser record);

  int save(SysUser record);

  int editById(SysUser record);

}
