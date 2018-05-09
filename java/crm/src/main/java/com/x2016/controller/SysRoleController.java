package com.x2016.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.x2016.poco.SysRole;
import com.x2016.service.RoleRefPermService;
import com.x2016.service.RoleService;
import com.x2016.service.UserRefRoleService;
import com.x2016.util.ResData;

import api.authc.single.dto.PageReq;
import api.authc.single.dto.PageRes;

@Controller
@RequestMapping("/role")
public class SysRoleController {

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private RoleRefPermService roleRefPermService;
	
	@Autowired
	private UserRefRoleService userRefRoleService;
	
	@RequestMapping("/index")
	public String index() {
		return "/system/sys_role/index";
	}
	
	@RequestMapping(value="/list0")
  public String list() {
	  
    return "/system/sys_role/list";
  }
	
	@RequestMapping("/add")
	public String add(String id,ModelMap map) {
	  SysRole model=new SysRole();
	  if(null!=id)
	    model=roleService.findById(id);
	  map.addAttribute("model", model);
	  return "/system/sys_role/add";
	}
	
	//异步加载角色列表
	@ResponseBody
  @RequestMapping(value = "list", method = RequestMethod.POST)
  public PageRes<SysRole> findList(SysRole record,Integer pageNum,Integer pageSize) {
    record.setRoleStatus(0);
	  PageReq<SysRole> req=new PageReq<SysRole>();
    req.setT(record);
    req.setPageNum(pageNum);
    req.setPageSize(pageSize);
    return roleService.findList(req);
  }
  //保存
  @ResponseBody
  @RequestMapping(value="/save",method=RequestMethod.POST)
  public ResData save(SysRole model){
    ResData res = new ResData();
    res.setCode("success");
    try{
      if(null==model.getRoleId()){
        model.setCreateTime(new Date());
        roleService.save(model);
      }
      else{
        model.setUpdateTime(new Date());
        roleService.editById(model);
      }
    } catch(Exception e){
      res.setCode("fail");
      res.setMsg(e.getMessage());
    }
    return res;
  }
  
  //根据userId取角色
  @ResponseBody
  @RequestMapping(value="/userId",method=RequestMethod.POST)
  public List<SysRole> findByUserId(String userId){
    return roleService.findListByUserId(userId);
  }
    
//保存授权
  @ResponseBody
  @RequestMapping(value="/grant",method=RequestMethod.POST)
  public ResData saveRolePerm(String roleId,String permIds){
    ResData res = new ResData();
    res.setCode("fail");
    if(null==roleId){
      res.setMsg("参数roleId不能为空");
      return res;
    }
    if(null==permIds||"".equals(permIds.trim())){
      res.setMsg("参数permIds不能为空");
      return res;
    }
    
    String [] strList=permIds.split(",");
    List<String> permList=new ArrayList<String>();
    for(String str:strList){
      permList.add(str);
    }
    
    try{
      roleRefPermService.saveBatch(roleId,permList);
      res.setCode("success");
    } catch(Exception e){
      res.setMsg("保存授权异常:"+e.getMessage());
    }
    return res;
  }
  
  //保存用戶ref角色
  @RequestMapping(value="/userRole",method=RequestMethod.POST)
  @ResponseBody
  public ResData saveUserRole(String userId,String roleIds){
    ResData res = new ResData();
    res.setCode("fail");
    if(null==userId){
      res.setMsg("参数userId不能为空");
      return res;
    }
    if(null==roleIds||"".equals(roleIds.trim())){
      res.setMsg("参数roleIds不能为空");
      return res;
    }
    String [] strList=roleIds.split(",");
    List<String> roleIdList=new ArrayList<String>();
    for(String str:strList){
      roleIdList.add(str);
    }
    try{
      userRefRoleService.saveBatch(userId, roleIdList);
      res.setCode("success");
    } catch(Exception e){
      res.setMsg("保存用户ref角色异常:"+e.getMessage());
    }
    return res;
  }
  
  //删除
  @ResponseBody
  @RequestMapping(value="/del",method=RequestMethod.POST)
  public ResData del(String id){
    ResData res = new ResData();
    res.setCode("success");
    if(null==id){
      res.setCode("fail");
      res.setMsg("参数-角色id为空...");
      return res;
    }
    SysRole model=new SysRole();
    model.setRoleId(id);
    model.setRoleStatus(1);//禁用
    model.setUpdateTime(new Date());
    try{
      roleService.editById(model);
    } catch(Exception e){
      res.setCode("fail");
      res.setMsg(e.getMessage());
    }
    return res;
  }
  
  
  
}
