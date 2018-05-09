package com.x2016.controller;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

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

import com.x2016.poco.SysPermission;
import com.x2016.service.SysPermissionService;
import com.x2016.util.ResData;

@Controller
@RequestMapping("/menu")
public class SysMenuController {

  @Autowired
  private SysPermissionService sysPermService;

  @RequiresRoles({"root"})
  @RequestMapping("/index")
  public String index() {
    return "/system/sys_menu/index";
  }

  @RequiresPermissions(value = {"smenu:add","smenu:edit"}, logical = Logical.OR)
  @RequestMapping("/add")
  public String add(String id, ModelMap map) {
    SysPermission model = new SysPermission();
    if (StringUtils.isNotBlank(id))
      model = sysPermService.findById(id);
    map.addAttribute("model", model);
    return "/system/sys_menu/add";
  }

  // 保存
  @RequiresPermissions(value = {"smenu:save"})
  @ResponseBody
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public ResData save(SysPermission model) {
    ResData res = new ResData();
    res.setCode("success");
    try {
      if (StringUtils.isBlank(model.getPermId())){
        model.setPermId(UUID.randomUUID().toString().replaceAll("-", ""));
        model.setCreateTime(new Date());
        sysPermService.save(model);
      }
      else{
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
  @RequiresPermissions(value = {"smenu:delete"})
  @ResponseBody
  @RequestMapping(value = "/del", method = RequestMethod.POST)
  public ResData del(String id, HttpServletRequest request) {
    ResData res = new ResData();
    res.setCode("success");
    if (StringUtils.isBlank(id)) {
      res.setCode("fail");
      res.setMsg("参数-菜单id为空...");
      return res;
    }
    SysPermission model = new SysPermission();
    model.setUpdateTime(new Date());
    model.setPermId(id);
    model.setPermStatus(1);// 禁用
    try {
      // sysPermService.editById(model);
    } catch (Exception e) {
      res.setCode("fail");
      res.setMsg(e.getMessage());
    }
    return res;
  }
}
