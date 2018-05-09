package com.authc.single.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;

import com.authc.single.bean.SysUser;
import com.authc.single.dao.SysUserMapper;
import com.github.pagehelper.PageHelper;

import api.authc.single.dto.PageReq;
import api.authc.single.dto.PageRes;
import api.authc.single.dto.req.user.CreateSysUserReqDto;
import api.authc.single.dto.req.user.FindSysUserReqDto;
import api.authc.single.dto.req.user.UpdateSysUserReqDto;
import api.authc.single.dto.res.SysUserResDto;
import api.authc.single.service.SysUserService;

public class SysUserServiceImpl implements SysUserService {

  private SysUserMapper sysUserMapper;
  
  public void setSysUserMapper(SysUserMapper sysUserMapper) {
    this.sysUserMapper = sysUserMapper;
  }

  @Override
  public SysUserResDto findById(String roleId) {
    return buildRes(sysUserMapper.findById(roleId));
  }
  
  @Override
  public SysUserResDto findByUserAccount(String userAccount) {
    return buildRes(sysUserMapper.findByUserAccount(userAccount));
  }

  @Override
  public PageRes<SysUserResDto> findList(PageReq<FindSysUserReqDto> req) {
    PageHelper.startPage(req.getPageNum(), req.getPageSize());
    SysUser model=new SysUser();
    BeanUtils.copyProperties(req.getT(),model);
    List<SysUser> list = sysUserMapper.findList(model);
    PageRes<SysUser> pr=new PageRes<SysUser>(list);
    
    PageRes<SysUserResDto> res=new PageRes<SysUserResDto>();
    BeanUtils.copyProperties(pr, res);
    return res;
  }

  @Override
  public String save(CreateSysUserReqDto record) {
    SysUser model=new SysUser();
    String id=UUID.randomUUID().toString().replaceAll("-", "");
    BeanUtils.copyProperties(record, model);
    model.setCreateTime(new Date());
    model.setUserId(id);
    sysUserMapper.save(model);
    return id;
  }

  @Override
  public int editById(UpdateSysUserReqDto record) {
    SysUser model=new SysUser();
    BeanUtils.copyProperties(record, model);
    model.setUpdateTime(new Date());
    return sysUserMapper.editById(model);
  }

  
  private SysUserResDto buildRes(SysUser model){
    SysUserResDto res=null;
    if(null!=model){
      res=new SysUserResDto();
      BeanUtils.copyProperties(model, res);
    }
    return res;
  }
  
//  private List<SysUserResDto> buildResList(List<SysUser> list){
//    List<SysUserResDto> resList=null;
//    if(null!=list){
//      resList=new ArrayList<SysUserResDto>();
//      SysUserResDto res=null;
//      for(SysUser model:list){
//        res=buildRes(model);
//        if(null!=res){
//          resList.add(res);
//        }
//      }
//    }
//    return resList;
//  }
}
