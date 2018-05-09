package com.authc.single.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;

import com.authc.single.bean.SysPermission;
import com.authc.single.dao.SysPermissionMapper;

import api.authc.single.dto.req.perm.CreateSysPermissionReqDto;
import api.authc.single.dto.req.perm.FindSysPermissionReqDto;
import api.authc.single.dto.req.perm.UpdateSysPermissionReqDto;
import api.authc.single.dto.res.SysPermissionResDto;
import api.authc.single.service.SysPermissionService;

public class SysPermissionServiceImpl implements SysPermissionService {

  private SysPermissionMapper sysPermMapper;

  public void setSysPermMapper(SysPermissionMapper sysPermMapper) {
    this.sysPermMapper = sysPermMapper;
  }

  @Override
  public SysPermissionResDto findById(String permId) {
    return buildRes(sysPermMapper.findById(permId));
  }
  
  @Override
  public List<SysPermissionResDto> findList(FindSysPermissionReqDto record) {
    SysPermission model=new SysPermission();
    BeanUtils.copyProperties(record, model);
    return buildResList(sysPermMapper.findList(model));
  }

  @Override
  public List<SysPermissionResDto> findMenu() {
    return buildResList(sysPermMapper.findMenu());
  }

  @Override
  public List<SysPermissionResDto> findLeftTreeMenu(String userId) {
    return buildResList(sysPermMapper.findLeftTreeMenu(userId));
  }

  @Override
  public List<SysPermissionResDto> findPermByMenuId(String menuId) {
    return buildResList(sysPermMapper.findPermByMenuId(menuId));
  }

  @Override
  public String save(CreateSysPermissionReqDto record) {
    SysPermission model=new SysPermission();
    String id=UUID.randomUUID().toString().replaceAll("-", "");
    BeanUtils.copyProperties(record, model);
    model.setPermId(id);
    model.setCreateTime(new Date());
    sysPermMapper.save(model);
    return id;
  }

  @Override
  public int editById(UpdateSysPermissionReqDto record) {
    SysPermission model=new SysPermission();
    BeanUtils.copyProperties(record, model);
    model.setUpdateTime(new Date());
    return sysPermMapper.editById(model);
  }

  @Override
  public List<SysPermissionResDto> findResourceByRoleId(String roleId) {
    return buildResList(sysPermMapper.findResourceByRoleId(roleId));
  }

  /**
   * 根据userId查询资源list
   * 
   * @param userId
   * @return
   */
  @Override
  public List<SysPermissionResDto> findListPermByUserId(String userId) {
    return buildResList(sysPermMapper.findListPermByUserId(userId));
  }

  
  private SysPermissionResDto buildRes(SysPermission model){
    SysPermissionResDto res=null;
    if(null!=model){
      res=new SysPermissionResDto();
      BeanUtils.copyProperties(model, res);
    }
    return res;
  }
  
  private List<SysPermissionResDto> buildResList(List<SysPermission> list){
    List<SysPermissionResDto> resList=null;
    if(null!=list){
      resList=new ArrayList<SysPermissionResDto>();
      SysPermissionResDto res=null;
      for(SysPermission model:list){
        res=buildRes(model);
        if(null!=res){
          resList.add(res);
        }
      }
    }
    return resList;
  }

}
