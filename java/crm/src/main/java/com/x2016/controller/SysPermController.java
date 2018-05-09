package com.x2016.controller;

import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.x2016.poco.SysPermission;
import com.x2016.poco.SysUser;
import com.x2016.service.PermissionService;
import com.x2016.util.ResData;

@Controller
@RequestMapping("/perm")
public class SysPermController {

	@Autowired
	private PermissionService permService;

	//权限
	@RequestMapping("/index")
  public String index() {
    return "/system/sys_perm/index";
  }
	
	@RequestMapping("/add")
  public String add(String id,ModelMap map) {
	  SysPermission model=new SysPermission();
    if(null!=id)
      model=permService.findById(id);
    map.addAttribute("model", model);
    return "/system/sys_perm/add";
  }
	
	// 取菜单
	@ResponseBody
	@RequestMapping(value = "/menu", method = RequestMethod.POST)
	public List<SysPermission> findMenu() {
		return permService.findMenu();
	}
	
  //取左侧菜单树
   @ResponseBody
   @RequestMapping(value = "/left", method = RequestMethod.POST)
   public List<SysPermission> findLeftTreeMenu() {
     SysUser user= (SysUser)SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
     return permService.findLeftTreeMenu(user.getUserId());
   }

	// 根据id取资源
	@ResponseBody
	@RequestMapping(value = "/{permId}", method = RequestMethod.POST)
	public SysPermission findById(@PathVariable String permId) {
		return permService.findById(permId);
	}

	// 取资源
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public List<SysPermission> findList(SysPermission record) {
	  return permService.findList(record);
	}
	
	// 取权限
	@ResponseBody
	@RequestMapping(value = "/auth/{menuId}", method = RequestMethod.POST)
	public List<SysPermission> findPermByMenuId(@PathVariable String menuId) {
	  return permService.findPermByMenuId(menuId);
	}
	
//根据角色id取资源
 @ResponseBody
 @RequestMapping(value = "/role", method = RequestMethod.POST)
 public List<SysPermission> findResourceByRoleId(String roleId) {
   return permService.findResourceByRoleId(roleId);
 }

	// 保存
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResData save(SysPermission model) {
		ResData res = new ResData();
		res.setCode("success");
		try {
		  if(null==model.getPermId()){
        model.setCreateTime(new Date());
		    permService.save(model);
      }
      else{
        model.setUpdateTime(new Date());
        permService.editById(model);
      }
		} catch (Exception e) {
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
    if(null==id){
      res.setCode("fail");
      res.setMsg("参数-权限id为空...");
      return res;
    }
    SysPermission model=new SysPermission();
    model.setPermId(id);
    model.setPermStatus(1);//禁用
    model.setUpdateTime(new Date());
    try{
      permService.editById(model);
    } catch(Exception e){
      res.setCode("fail");
      res.setMsg(e.getMessage());
    }
    return res;
  }
}
