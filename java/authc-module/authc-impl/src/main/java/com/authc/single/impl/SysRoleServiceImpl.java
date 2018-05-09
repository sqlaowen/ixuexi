package com.authc.single.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;

import com.authc.single.bean.SysRole;
import com.authc.single.dao.SysRoleMapper;
import com.github.pagehelper.PageHelper;

import api.authc.single.dto.PageReq;
import api.authc.single.dto.PageRes;
import api.authc.single.dto.req.role.CreateSysRoleReqDto;
import api.authc.single.dto.req.role.FindSysRoleReqDto;
import api.authc.single.dto.req.role.UpdateSysRoleReqDto;
import api.authc.single.dto.res.SysRoleResDto;
import api.authc.single.service.SysRoleService;

public class SysRoleServiceImpl implements SysRoleService {

  private SysRoleMapper sysRoleMapper;
  
  public void setSysRoleMapper(SysRoleMapper sysRoleMapper) {
    this.sysRoleMapper = sysRoleMapper;
  }

  @Override
  public SysRoleResDto findById(String roleId) {
    return buildRes(sysRoleMapper.findById(roleId));
  }

  @Override
  public PageRes<SysRoleResDto> findList(PageReq<FindSysRoleReqDto> req) {
    PageHelper.startPage(req.getPageNum(), req.getPageSize());
    SysRole model=new SysRole();
    BeanUtils.copyProperties(req.getT(),model);
    List<SysRole> list = sysRoleMapper.findList(model);
    PageRes<SysRole> pr=new PageRes<SysRole>(list);
    
    PageRes<SysRoleResDto> res=new PageRes<SysRoleResDto>();
    BeanUtils.copyProperties(pr, res);
    return res;
  }

  @Override
  public String save(CreateSysRoleReqDto record) {
    SysRole model=new SysRole();
    String id=UUID.randomUUID().toString().replaceAll("-", "");
    BeanUtils.copyProperties(record, model);
    model.setCreateTime(new Date());
    model.setRoleId(id);
    sysRoleMapper.save(model);
    return id;
  }

  @Override
  public int editById(UpdateSysRoleReqDto record) {
    SysRole model=new SysRole();
    BeanUtils.copyProperties(record, model);
    model.setUpdateTime(new Date());
    return sysRoleMapper.editById(model);
  }

  /**
   * 通过用户id查询角色list
   */
  @Override
  public List<SysRoleResDto> findListByUserId(String userId) {
    return buildResList(sysRoleMapper.findListByUserId(userId));
  }
  
  private SysRoleResDto buildRes(SysRole model){
    SysRoleResDto res=null;
    if(null!=model){
      res=new SysRoleResDto();
      BeanUtils.copyProperties(model, res);
    }
    return res;
  }
  
  private List<SysRoleResDto> buildResList(List<SysRole> list){
    List<SysRoleResDto> resList=null;
    if(null!=list){
      resList=new ArrayList<SysRoleResDto>();
      SysRoleResDto res=null;
      for(SysRole model:list){
        res=buildRes(model);
        if(null!=res){
          resList.add(res);
        }
      }
    }
    return resList;
  }

}
