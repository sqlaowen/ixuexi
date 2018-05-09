package com.laowen.dao;

import com.laowen.bean.SysRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysRoleMapper {

    SysRole findById(String roleId);

    List<SysRole> findList(SysRole sysRole);

    /**
     * 通过用户id查询角色list
     *
     * @param userId
     * @return
     */
    List<SysRole> findListByUserId(@Param("userId") String userId);

    int save(SysRole sysRole);

    int editById(SysRole sysRole);
}
