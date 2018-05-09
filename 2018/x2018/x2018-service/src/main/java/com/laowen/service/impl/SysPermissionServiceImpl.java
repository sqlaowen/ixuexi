package com.laowen.service.impl;

import com.laowen.entity.SysPermission;
import com.laowen.mapper.SysPermissionMapper;
import com.laowen.service.SysPermissionService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class SysPermissionServiceImpl implements SysPermissionService {

    private SysPermissionMapper sysPermMapper;

    @Override
    public SysPermission findById(String permId) {
        return sysPermMapper.findById(permId);
    }

    @Override
    public List<SysPermission> findList(SysPermission sysPermission) {
        return sysPermMapper.findList(sysPermission);
    }

    @Override
    public List<SysPermission> findMenu() {
        return sysPermMapper.findMenu();
    }

    @Override
    public List<SysPermission> findLeftTreeMenu(String userId) {
        return sysPermMapper.findLeftTreeMenu(userId);
    }

    @Override
    public List<SysPermission> findPermByMenuId(String menuId) {
        return sysPermMapper.findPermByMenuId(menuId);
    }

    @Override
    public String save(SysPermission sysPermission) {
        String id = UUID.randomUUID().toString().replaceAll("-", "");
        sysPermission.setPermId(id);
        sysPermission.setCreateTime(new Date());
        sysPermMapper.save(sysPermission);
        return id;
    }

    @Override
    public int editById(SysPermission sysPermission) {
        sysPermission.setUpdateTime(new Date());
        return sysPermMapper.editById(sysPermission);
    }

    @Override
    public List<SysPermission> findResourceByRoleId(String roleId) {
        return sysPermMapper.findResourceByRoleId(roleId);
    }

    /**
     * 根据userId查询资源list
     *
     * @param userId
     * @return
     */
    @Override
    public List<SysPermission> findListPermByUserId(String userId) {
        return sysPermMapper.findListPermByUserId(userId);
    }

}
