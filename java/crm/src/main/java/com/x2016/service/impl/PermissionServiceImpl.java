package com.x2016.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.x2016.poco.SysPermission;
import com.x2016.service.PermissionService;

import api.authc.single.dto.req.perm.CreateSysPermissionReqDto;
import api.authc.single.dto.req.perm.FindSysPermissionReqDto;
import api.authc.single.dto.req.perm.UpdateSysPermissionReqDto;
import api.authc.single.dto.res.SysPermissionResDto;
import api.authc.single.service.SysPermissionService;

@Service("permService")
public class PermissionServiceImpl implements PermissionService {

  @Autowired
  private SysPermissionService sysPermissionService;

  @Override
  public SysPermission findById(String permId) {
    return buildRes(sysPermissionService.findById(permId));
  }

  @Override
  public List<SysPermission> findList(SysPermission record) {
    FindSysPermissionReqDto recordDto = new FindSysPermissionReqDto();
    BeanUtils.copyProperties(record, recordDto);
    return buildResList(sysPermissionService.findList(recordDto));
  }

  @Override
  public List<SysPermission> findMenu() {
    return buildResList(sysPermissionService.findMenu());
  }

  @Override
  public List<SysPermission> findLeftTreeMenu(String userId) {
    return buildResList(sysPermissionService.findLeftTreeMenu(userId));
  }

  @Override
  public List<SysPermission> findPermByMenuId(String menuId) {
    return buildResList(sysPermissionService.findPermByMenuId(menuId));
  }

  @Override
  public String save(SysPermission record) {
    CreateSysPermissionReqDto recordDto = new CreateSysPermissionReqDto();
    BeanUtils.copyProperties(record, recordDto);
    return sysPermissionService.save(recordDto);
  }

  @Override
  public int editById(SysPermission record) {
    UpdateSysPermissionReqDto recordDto = new UpdateSysPermissionReqDto();
    BeanUtils.copyProperties(record, recordDto);
    return sysPermissionService.editById(recordDto);
  }

  @Override
  public List<SysPermission> findResourceByRoleId(String roleId) {
    return buildResList(sysPermissionService.findResourceByRoleId(roleId));
  }

  /**
   * 根据userId查询资源list
   * 
   * @param userId
   * @return
   */
  @Override
  public List<SysPermission> findListPermByUserId(String userId) {
    return buildResList(sysPermissionService.findListPermByUserId(userId));
  }

  private SysPermission buildRes(SysPermissionResDto dto) {
    SysPermission res = null;
    if (null != dto) {
      res = new SysPermission();
      BeanUtils.copyProperties(dto, res);
    }
    return res;
  }

  private List<SysPermission> buildResList(List<SysPermissionResDto> dtoList) {
    List<SysPermission> resList = null;
    if (null != dtoList) {
      resList = new ArrayList<SysPermission>();
      SysPermission res = null;
      for (SysPermissionResDto dto : dtoList) {
        res = buildRes(dto);
        if (null != res) {
          resList.add(res);
        }
      }
    }
    return resList;
  }

}
