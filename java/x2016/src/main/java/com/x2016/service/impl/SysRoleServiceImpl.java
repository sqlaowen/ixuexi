package com.x2016.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.x2016.mapper.SysRoleMapper;
import com.x2016.poco.SysRole;
import com.x2016.service.SysRoleService;
import com.x2016.util.PageReq;
import com.x2016.util.PageRes;

@Service
public class SysRoleServiceImpl implements SysRoleService {

  @Autowired
  private SysRoleMapper sysRoleMapper;
  
  @Override
  public SysRole findById(String roleId) {
    return sysRoleMapper.findById(roleId);
  }

  @Override
  public PageRes<SysRole> findList(PageReq<SysRole> req) {
    PageHelper.startPage(req.getPageNum(), req.getPageSize());
    List<SysRole> list = sysRoleMapper.findList(req.getT());
    return new PageRes<SysRole>(list);
  }

  @Override
  public int save(SysRole record) {
    return sysRoleMapper.save(record);
  }

  @Override
  public int editById(SysRole record) {
    return sysRoleMapper.editById(record);
  }

  /**
   * 通过用户id查询角色list
   */
  @Override
  public List<SysRole> findListByUserId(String userId) {
    return sysRoleMapper.findListByUserId(userId);
  }

}
