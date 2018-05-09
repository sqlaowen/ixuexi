package com.x2016.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.x2016.poco.SysPermission;
import com.x2016.service.PermissionService;
import com.x2016.util.ResData;

@Controller
@RequestMapping("/menu")
public class SysMenuController {

  @Autowired
  private PermissionService permService;
  
	@RequestMapping("/index")
  public String index() {
    return "/system/sys_menu/index";
  }

//	@RequiresPermissions({"sys:edit","sys:add"})
	@RequestMapping("/add")
  public String add(String id,ModelMap map) {
	  SysPermission model=new SysPermission();
	  if(null!=id)
	    model=permService.findById(id);
	  map.addAttribute("model", model);
    return "/system/sys_menu/add";
  }
	
	//保存
//	@RequiresRoles("xxrole")
	@ResponseBody
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ResData save(SysPermission model){
	  ResData res = new ResData();
    res.setCode("success");
    try{
  	  if(null==model.getPermId())
  	    permService.save(model);
  	  else
  	    permService.editById(model);
    } catch(Exception e){
      res.setCode("fail");
      res.setMsg(e.getMessage());
    }
	  return res;
	}
	
	//删除
//	@RequiresRoles("xxrole")
  @ResponseBody
  @RequestMapping(value="/del",method=RequestMethod.POST)
  public ResData del(String id,HttpServletRequest request){
    ResData res = new ResData();
    res.setCode("success");
    if(null==id){
      res.setCode("fail");
      res.setMsg("参数-菜单id为空...");
      return res;
    }
    SysPermission model=new SysPermission();
    model.setPermId(id);
    model.setPermStatus(1);//禁用
    try{
        //permService.editById(model);
    } catch(Exception e){
      res.setCode("fail");
      res.setMsg(e.getMessage());
    }
    return res;
  }
}
