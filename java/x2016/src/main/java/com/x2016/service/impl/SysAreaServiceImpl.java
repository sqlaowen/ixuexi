package com.x2016.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.x2016.mapper.SysAreaMapper;
import com.x2016.poco.SysArea;
import com.x2016.service.SysAreaService;
import com.x2016.util.PageReq;
import com.x2016.util.PageRes;

@Service
public class SysAreaServiceImpl implements SysAreaService {

  @Autowired
  private SysAreaMapper sysAreaMapper;
  
  @Override
  public PageRes<SysArea> findList(PageReq<SysArea> req) {
    PageHelper.startPage(req.getPageNum(), req.getPageSize());
    List<SysArea> list = sysAreaMapper.findList(req.getT());
    return new PageRes<SysArea>(list);
  }
}
