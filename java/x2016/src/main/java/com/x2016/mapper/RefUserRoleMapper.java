package com.x2016.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface RefUserRoleMapper {
  
  int delByUserId(@Param("userId") String userId);

  //int saveBatch(@Param("userId") String userId,@Param("roleIdList") List<String> roleIdList);

  int saveBatch(@Param("mapList") List<Map<String,Object>> mapList);
}