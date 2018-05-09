package com.laowen.service.impl;

import com.github.pagehelper.PageHelper;
import com.laowen.PageRequest;
import com.laowen.PageResponse;
import com.laowen.entity.SysRole;
import com.laowen.mapper.SysRoleMapper;
import com.laowen.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public SysRole findById(String roleId) {
        return sysRoleMapper.findById(roleId);
    }

    @Override
    public PageResponse<SysRole> findList(PageRequest<SysRole> req) {
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        List<SysRole> list = sysRoleMapper.findList(req.getT());
        return new PageResponse<SysRole>(list);
    }

    @Override
    public String save(SysRole sysRole) {
        String id = UUID.randomUUID().toString().replaceAll("-", "");
        sysRole.setCreateTime(new Date());
        sysRole.setRoleId(id);
        sysRoleMapper.save(sysRole);
        return id;
    }

    @Override
    public int editById(SysRole sysRole) {

        sysRole.setUpdateTime(new Date());
        return sysRoleMapper.editById(sysRole);
    }

    @Override
    public List<SysRole> findListByUserId(String userId) {
        return sysRoleMapper.findListByUserId(userId);
    }

}
