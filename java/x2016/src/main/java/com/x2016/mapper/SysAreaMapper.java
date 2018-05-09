package com.x2016.mapper;

import java.util.List;

import com.x2016.poco.SysArea;

public interface SysAreaMapper {
  
  int save(SysArea record);

  List<SysArea> findList(SysArea record);
}
