package com.x2016.controller;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.x2016.poco.SysUser;
import com.x2016.service.SysUserService;
import com.x2016.util.PageReq;
import com.x2016.util.PageRes;
import com.x2016.util.ResData;

@Controller
@RequestMapping("/user")
public class SysUserController {

  @Autowired
  private SysUserService sysUserService;
  
	@RequestMapping("/index")
  public String index() {
    return "/system/sys_user/index";
  }
	
	@RequestMapping("/add")
  public String add(String id,ModelMap map) {
	  SysUser model=new SysUser();
	  if(StringUtils.isNotBlank(id))
	    model=sysUserService.findById(id);
	  map.addAttribute("model", model);
    return "/system/sys_user/add";
  }
	
//异步加载角色列表
  @ResponseBody
  @RequestMapping(value = "list", method = RequestMethod.POST)
  public PageRes<SysUser> findList(SysUser record,Integer pageNum,Integer pageSize) {
    record.setUserStatus(0);
    PageReq<SysUser> req=new PageReq<SysUser>();
    req.setT(record);
    req.setPageNum(pageNum);
    req.setPageSize(pageSize);
    return sysUserService.findList(req);
  }
	
	//保存
	@ResponseBody
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ResData save(SysUser model){
	  ResData res = new ResData();
    res.setCode("success");
    try{
  	  if(StringUtils.isBlank(model.getUserId())){
  	    model.setUserId(UUID.randomUUID().toString().replaceAll("-", ""));
  	    model.setCreateTime(new Date());
  	    sysUserService.save(model);
  	  }
  	  else{
  	    model.setUpdateTime(new Date());
  	    sysUserService.editById(model);
  	  }
    } catch(Exception e){
      res.setCode("fail");
      res.setMsg(e.getMessage());
    }
	  return res;
	}
	
	//删除
  @ResponseBody
  @RequestMapping(value="/del",method=RequestMethod.POST)
  public ResData del(String id){
    ResData res = new ResData();
    res.setCode("success");
    if(StringUtils.isBlank(id)){
      res.setCode("fail");
      res.setMsg("参数-菜单id为空...");
      return res;
    }
    SysUser model=new SysUser();
    model.setUserId(id);
    model.setUserStatus(1);//禁用
    model.setUpdateTime(new Date());
    try{
        sysUserService.editById(model);
    } catch(Exception e){
      res.setCode("fail");
      res.setMsg(e.getMessage());
    }
    return res;
  }
}
