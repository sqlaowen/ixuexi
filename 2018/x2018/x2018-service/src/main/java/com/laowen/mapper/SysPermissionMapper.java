package com.laowen.mapper;

import java.util.List;

import com.laowen.entity.SysPermission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SysPermissionMapper {

    SysPermission findById(String permId);

    List<SysPermission> findList(SysPermission record);

    List<SysPermission> findMenu();

    //取左侧菜单树
    List<SysPermission> findLeftTreeMenu(@Param("userId") String userId);

    List<SysPermission> findPermByMenuId(String menuId);

    /**
     * 根据userId查询资源list
     *
     * @param userId
     * @return
     */
    List<SysPermission> findListPermByUserId(@Param("userId") String userId);

    //根据角色id取资源
    List<SysPermission> findResourceByRoleId(String roleId);

    int save(SysPermission record);

    int editById(SysPermission record);
}
