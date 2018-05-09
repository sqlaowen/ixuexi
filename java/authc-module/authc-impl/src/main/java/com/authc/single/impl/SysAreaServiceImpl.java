package com.authc.single.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;

import com.authc.single.bean.SysArea;
import com.authc.single.dao.SysAreaMapper;
import com.github.pagehelper.PageHelper;

import api.authc.single.dto.PageReq;
import api.authc.single.dto.PageRes;
import api.authc.single.dto.req.FindSysAreaReqDto;
import api.authc.single.dto.res.SysAreaResDto;
import api.authc.single.service.SysAreaService;

public class SysAreaServiceImpl implements SysAreaService {

  private SysAreaMapper sysAreaMapper;
  
  public void setSysAreaMapper(SysAreaMapper sysAreaMapper) {
    this.sysAreaMapper = sysAreaMapper;
  }

  @Override
  public PageRes<SysAreaResDto> findList(PageReq<FindSysAreaReqDto> req) {
    PageHelper.startPage(req.getPageNum(), req.getPageSize());
    SysArea model=new SysArea();
    BeanUtils.copyProperties(req.getT(),model);
    List<SysArea> list = sysAreaMapper.findList(model);
    PageRes<SysArea> pr=new PageRes<SysArea>(list);
    PageRes<SysAreaResDto> res=new PageRes<SysAreaResDto>();
    BeanUtils.copyProperties(pr, res);
    return res;
  }

}
