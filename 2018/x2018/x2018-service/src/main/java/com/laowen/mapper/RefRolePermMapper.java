package com.laowen.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RefRolePermMapper {

    int delByRoleId(@Param("roleId") String roleId);

    /*int saveBatch(@Param("roleId") String roleId,@Param("permIdList") List<String> permIdList);*/

    int saveBatch(@Param("mapList") List<Map<String, Object>> mapList);
    
    /*int saveOne(RefRolePerm record);*/
}