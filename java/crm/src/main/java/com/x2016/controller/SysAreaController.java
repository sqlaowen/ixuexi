package com.x2016.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.x2016.poco.SysArea;
import com.x2016.service.AreaService;

import api.authc.single.dto.PageReq;
import api.authc.single.dto.PageRes;

@Controller
@RequestMapping("area")
public class SysAreaController {

  @Autowired
  private AreaService areaService;
  
  @RequestMapping("index")
  public String index(){
    return "/sys_area/index";
  }
  
  @ResponseBody
  @RequestMapping(value="list",method=RequestMethod.POST)
  public PageRes<SysArea> findList(SysArea record,Integer pageNum,Integer pageSize){
    if(record.getAreaName()!=null && !record.getAreaName().trim().equals(""))
      record.setAreaName("%"+record.getAreaName()+"%");
    PageReq<SysArea> req=new PageReq<SysArea>();
    req.setT(record);
    req.setPageNum(pageNum);
    req.setPageSize(pageSize);
    return areaService.findList(req);
  }
}
