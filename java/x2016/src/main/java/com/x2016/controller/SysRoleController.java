package com.x2016.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.x2016.poco.SysRole;
import com.x2016.service.RefRolePermService;
import com.x2016.service.RefUserRoleService;
import com.x2016.service.SysRoleService;
import com.x2016.util.PageReq;
import com.x2016.util.PageRes;
import com.x2016.util.ResData;

@Controller
@RequestMapping("/role")
public class SysRoleController {

	@Autowired
	private SysRoleService sysRoleService;
	
	@Autowired
	private RefRolePermService refRolePermService;
	
	@Autowired
	private RefUserRoleService refUserRoleService;
	
	@RequiresRoles({"root"})
	@RequestMapping("/index")
	public String index() {
		return "/system/sys_role/index";
	}
	
	@RequestMapping(value="/list0")
  public String list() {
    return "/system/sys_role/list";
  }
	
	@RequiresPermissions(value = {"srole:add","srole:edit"}, logical = Logical.OR)
	@RequestMapping("/add")
	public String add(String id,ModelMap map) {
	  SysRole model=new SysRole();
	  if(StringUtils.isNotBlank(id))
	    model=sysRoleService.findById(id);
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
    return sysRoleService.findList(req);
  }
	
  //保存
	@RequiresPermissions({"srole:save"})
  @ResponseBody
  @RequestMapping(value="/save",method=RequestMethod.POST)
  public ResData save(SysRole model){
    ResData res = new ResData();
    res.setCode("success");
    try{
      if(StringUtils.isBlank(model.getRoleId())){
        model.setRoleId(UUID.randomUUID().toString().replaceAll("-", ""));
        model.setCreateTime(new Date());
        sysRoleService.save(model);
      }
      else{
        model.setUpdateTime(new Date());
        sysRoleService.editById(model);
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
    return sysRoleService.findListByUserId(userId);
  }
    
//保存授权
  @RequiresPermissions({"srole:grant"})
  @ResponseBody
  @RequestMapping(value="/grant",method=RequestMethod.POST)
  public ResData saveRolePerm(String roleId,String permIds){
    ResData res = new ResData();
    res.setCode("fail");
    if(StringUtils.isBlank(roleId)){
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
      refRolePermService.saveBatch(roleId,permList);
      res.setCode("success");
    } catch(Exception e){
      res.setMsg("保存授权异常:"+e.getMessage());
    }
    return res;
  }
  
  //保存用戶ref角色
//  @RequiresPermissions(value = {"sysRole:save"})
  @RequestMapping(value="/userRole",method=RequestMethod.POST)
  @ResponseBody
  public ResData saveUserRole(String userId,String roleIds){
    ResData res = new ResData();
    res.setCode("fail");
    if(StringUtils.isBlank(userId)){
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
      refUserRoleService.saveBatch(userId, roleIdList);
      res.setCode("success");
    } catch(Exception e){
      res.setMsg("保存用户ref角色异常:"+e.getMessage());
    }
    return res;
  }
  
  //删除
  @RequiresPermissions(value = {"srole:delete"})
  @ResponseBody
  @RequestMapping(value="/del",method=RequestMethod.POST)
  public ResData del(String id){
    ResData res = new ResData();
    res.setCode("success");
    if(StringUtils.isBlank(id)){
      res.setCode("fail");
      res.setMsg("参数-角色id为空...");
      return res;
    }
    SysRole model=new SysRole();
    model.setRoleId(id);
    model.setRoleStatus(1);//禁用
    model.setUpdateTime(new Date());
    try{
      sysRoleService.editById(model);
    } catch(Exception e){
      res.setCode("fail");
      res.setMsg(e.getMessage());
    }
    return res;
  }
  
  
  
}
