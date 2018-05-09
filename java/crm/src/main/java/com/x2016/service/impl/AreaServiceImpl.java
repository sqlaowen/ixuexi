package com.x2016.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.x2016.poco.SysArea;
import com.x2016.service.AreaService;

import api.authc.single.dto.PageReq;
import api.authc.single.dto.PageRes;
import api.authc.single.dto.req.FindSysAreaReqDto;
import api.authc.single.dto.res.SysAreaResDto;
import api.authc.single.service.SysAreaService;

@Service
public class AreaServiceImpl implements AreaService {

  @Autowired
  private SysAreaService sysAreaService;
  
  @Override
  public PageRes<SysArea> findList(PageReq<SysArea> req) {
    PageReq<FindSysAreaReqDto> reqDto=new PageReq<FindSysAreaReqDto>();
    BeanUtils.copyProperties(req, reqDto);
    PageRes<SysAreaResDto> resDto=sysAreaService.findList(reqDto);
    PageRes<SysArea> res=new PageRes<>();
    BeanUtils.copyProperties(resDto, res);
    return res;
  }
}
