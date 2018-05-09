package com.x2016.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.x2016.poco.SysUser;
import com.x2016.service.UserService;

import api.authc.single.dto.PageReq;
import api.authc.single.dto.PageRes;
import api.authc.single.dto.req.user.CreateSysUserReqDto;
import api.authc.single.dto.req.user.FindSysUserReqDto;
import api.authc.single.dto.req.user.UpdateSysUserReqDto;
import api.authc.single.dto.res.SysUserResDto;
import api.authc.single.service.SysUserService;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private SysUserService sysUserService;
  
  @Override
  public SysUser findById(String roleId) {
    return buildRes(sysUserService.findById(roleId));
  }
  
  @Override
  public SysUser findByUserAccount(String userAccount) {
    return buildRes(sysUserService.findByUserAccount(userAccount));
  }

  @Override
  public PageRes<SysUser> findList(PageReq<SysUser> req) {
    PageReq<FindSysUserReqDto> reqDto=new PageReq<FindSysUserReqDto>();
    BeanUtils.copyProperties(req, reqDto);
    PageRes<SysUserResDto> resDto=sysUserService.findList(reqDto);
    
    PageRes<SysUser> res=new PageRes<>();
    BeanUtils.copyProperties(resDto, res);
    return res;
  }

  @Override
  public String save(SysUser record) {
    CreateSysUserReqDto recordDto=new CreateSysUserReqDto();
    BeanUtils.copyProperties(record, recordDto);
    return sysUserService.save(recordDto);
  }

  @Override
  public int editById(SysUser record) {
    UpdateSysUserReqDto recordDto=new UpdateSysUserReqDto();
    BeanUtils.copyProperties(record, recordDto);
    return sysUserService.editById(recordDto);
  }

  private SysUser buildRes(SysUserResDto dto) {
    SysUser res = null;
    if (null != dto) {
      res = new SysUser();
      BeanUtils.copyProperties(dto, res);
    }
    return res;
  }
}
