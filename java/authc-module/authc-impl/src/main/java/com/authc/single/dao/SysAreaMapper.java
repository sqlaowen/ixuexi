package com.authc.single.dao;

import java.util.List;

import com.authc.single.bean.SysArea;

public interface SysAreaMapper {
  
  int save(SysArea record);

  List<SysArea> findList(SysArea record);
}
