package com.x2016.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.x2016.poco.SysRole;
import com.x2016.service.RoleService;

import api.authc.single.dto.PageReq;
import api.authc.single.dto.PageRes;
import api.authc.single.dto.req.role.CreateSysRoleReqDto;
import api.authc.single.dto.req.role.FindSysRoleReqDto;
import api.authc.single.dto.req.role.UpdateSysRoleReqDto;
import api.authc.single.dto.res.SysRoleResDto;
import api.authc.single.service.SysRoleService;

@Service
public class RoleServiceImpl implements RoleService {

  @Autowired
  private SysRoleService sysRoleService;

  @Override
  public SysRole findById(String roleId) {
    return buildRes(sysRoleService.findById(roleId));
  }

  @Override
  public PageRes<SysRole> findList(PageReq<SysRole> req) {
    PageReq<FindSysRoleReqDto> reqDto = new PageReq<FindSysRoleReqDto>();
    BeanUtils.copyProperties(req, reqDto);
    PageRes<SysRoleResDto> resDto = sysRoleService.findList(reqDto);

    PageRes<SysRole> res = new PageRes<SysRole>();
    BeanUtils.copyProperties(resDto, res);
    return res;
  }

  @Override
  public String save(SysRole record) {
    CreateSysRoleReqDto recordDto = new CreateSysRoleReqDto();
    BeanUtils.copyProperties(record, recordDto);
    return sysRoleService.save(recordDto);
  }

  @Override
  public int editById(SysRole record) {
    UpdateSysRoleReqDto recordDto = new UpdateSysRoleReqDto();
    BeanUtils.copyProperties(record, recordDto);
    return sysRoleService.editById(recordDto);
  }

  /**
   * 通过用户id查询角色list
   */
  @Override
  public List<SysRole> findListByUserId(String userId) {
    return buildResList(sysRoleService.findListByUserId(userId));
  }


  private SysRole buildRes(SysRoleResDto dto) {
    SysRole res = null;
    if (null != dto) {
      res = new SysRole();
      BeanUtils.copyProperties(dto, res);
    }
    return res;
  }

  private List<SysRole> buildResList(List<SysRoleResDto> dtoList) {
    List<SysRole> resList = null;
    if (null != dtoList) {
      resList = new ArrayList<SysRole>();
      SysRole res = null;
      for (SysRoleResDto dto : dtoList) {
        res = buildRes(dto);
        if (null != res) {
          resList.add(res);
        }
      }
    }
    return resList;
  }
}
