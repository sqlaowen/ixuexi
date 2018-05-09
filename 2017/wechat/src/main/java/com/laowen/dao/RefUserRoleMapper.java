package com.laowen.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RefUserRoleMapper {

    int delByUserId(@Param("userId") String userId);

    //int saveBatch(@Param("userId") String userId,@Param("roleIdList") List<String> roleIdList);

    int saveBatch(@Param("mapList") List<Map<String, Object>> mapList);
}