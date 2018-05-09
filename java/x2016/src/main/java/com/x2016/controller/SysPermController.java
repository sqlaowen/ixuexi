package com.x2016.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.x2016.poco.SysPermission;
import com.x2016.poco.SysUser;
import com.x2016.service.SysPermissionService;
import com.x2016.util.ResData;

@Controller
@RequestMapping("/perm")
public class SysPermController {

  @Autowired
  private SysPermissionService sysPermService;

  // 权限
  @RequiresRoles({"root"})
  @RequestMapping("/index")
  public String index() {
    return "/system/sys_perm/index";
  }

  @RequiresPermissions(value = {"sperm:add","sperm:edit"}, logical = Logical.OR)
  @RequestMapping("/add")
  public String add(String id, ModelMap map) {
    SysPermission model = new SysPermission();
    if (StringUtils.isNotBlank(id))
      model = sysPermService.findById(id);
    map.addAttribute("model", model);
    return "/system/sys_perm/add";
  }

  // 取菜单
  @ResponseBody
  @RequestMapping(value = "/menu", method = RequestMethod.POST)
  public List<SysPermission> findMenu() {
    return sysPermService.findMenu();
  }

  // 取左侧菜单树
  @ResponseBody
  @RequestMapping(value = "/left", method = RequestMethod.POST)
  public List<SysPermission> findLeftTreeMenu() {
    SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
    return sysPermService.findLeftTreeMenu(user.getUserId());
  }

  // 根据id取资源
  @ResponseBody
  @RequestMapping(value = "/{permId}", method = RequestMethod.POST)
  public SysPermission findById(@PathVariable String permId) {
    return sysPermService.findById(permId);
  }

  // 取资源
  @ResponseBody
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public List<SysPermission> findList(SysPermission record) {
    return sysPermService.findList(record);
  }

  // 取权限
  @ResponseBody
  @RequestMapping(value = "/auth/{menuId}", method = RequestMethod.POST)
  public List<SysPermission> findPermByMenuId(@PathVariable String menuId) {
    return sysPermService.findPermByMenuId(menuId);
  }

  // 根据角色id取资源
  @ResponseBody
  @RequestMapping(value = "/role", method = RequestMethod.POST)
  public List<SysPermission> findResourceByRoleId(String roleId) {
    return sysPermService.findResourceByRoleId(roleId);
  }

  // 保存
  @RequiresPermissions(value = {"sperm:save"})
  @ResponseBody
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public ResData save(SysPermission model) {
    ResData res = new ResData();
    res.setCode("success");
    try {
      if (StringUtils.isBlank(model.getPermId())) {
        model.setPermId(UUID.randomUUID().toString().replaceAll("-", ""));
        model.setCreateTime(new Date());
        sysPermService.save(model);
      } else {
        model.setUpdateTime(new Date());
        sysPermService.editById(model);
      }
    } catch (Exception e) {
      res.setCode("fail");
      res.setMsg(e.getMessage());
    }
    return res;
  }

  // 删除
  @RequiresPermissions(value = {"sperm:delete"})
  @ResponseBody
  @RequestMapping(value = "/del", method = RequestMethod.POST)
  public ResData del(String id) {
    ResData res = new ResData();
    res.setCode("success");
    if (StringUtils.isBlank(id)) {
      res.setCode("fail");
      res.setMsg("参数-权限id为空...");
      return res;
    }
    SysPermission model = new SysPermission();
    model.setPermId(id);
    model.setPermStatus(1);// 禁用
    model.setUpdateTime(new Date());
    try {
      sysPermService.editById(model);
    } catch (Exception e) {
      res.setCode("fail");
      res.setMsg(e.getMessage());
    }
    return res;
  }
}
