package com.x2016.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.x2016.mapper.SysUserMapper;
import com.x2016.poco.SysUser;
import com.x2016.service.SysUserService;
import com.x2016.util.PageReq;
import com.x2016.util.PageRes;

@Service
public class SysUserServiceImpl implements SysUserService {

  @Autowired
  private SysUserMapper sysUserMapper;
  
  @Override
  public SysUser findById(String roleId) {
    return sysUserMapper.findById(roleId);
  }
  
  @Override
  public SysUser findByUserAccount(String userAccount) {
    return sysUserMapper.findByUserAccount(userAccount);
  }

  @Override
  public PageRes<SysUser> findList(PageReq<SysUser> req) {
    PageHelper.startPage(req.getPageNum(), req.getPageSize());
    List<SysUser> list = sysUserMapper.findList(req.getT());
    return new PageRes<SysUser>(list);
  }

  @Override
  public int save(SysUser record) {
    return sysUserMapper.save(record);
  }

  @Override
  public int editById(SysUser record) {
    return sysUserMapper.editById(record);
  }

}
