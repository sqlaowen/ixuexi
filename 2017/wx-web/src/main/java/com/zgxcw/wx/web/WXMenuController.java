package com.zgxcw.wx.web;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zgxcw.service.combine.service.SetMenuService;
import com.zgxcw.service.infrastructure.dto.request.wxselfmenu.CreateWXSelfMenuRequest;
import com.zgxcw.service.infrastructure.dto.request.wxselfmenu.UpdateWXSelfMenuRequest;
import com.zgxcw.service.infrastructure.dto.response.WXSelfMenuResponse;
import com.zgxcw.service.infrastructure.service.WXSelfMenuService;
import com.zgxcw.wx.domain.WXMenu;
import com.zgxcw.wx.utils.ResData;

@Controller
@RequestMapping("/wx/menu")
public class WXMenuController {

  @Autowired
  private WXSelfMenuService wxSelfMenuService;
  
  @Autowired
  private SetMenuService setMenuService;

  @RequestMapping("/index")
  public String index() {
    return "/wx_menu/index";
  }

  @RequestMapping("/add")
  public String add(String menuId, ModelMap map) {
    WXMenu model = new WXMenu();
    if (null != menuId) {
      model = buildModel(wxSelfMenuService.findById(menuId));
    }
    map.addAttribute("model", model);
    return "/wx_menu/add";
  }

  // 查询所有菜单
  @ResponseBody
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public List<WXMenu> getList(String wxId) {
    return buildModelList(wxSelfMenuService.findList(wxId));

  }

  //添加/修改
  @ResponseBody
  @RequestMapping(value="/save",method=RequestMethod.POST)
  public ResData save(WXMenu model) {
    ResData res = new ResData();
    res.setCode("fail");
//    if (model.getMenuType().toUpperCase().equals("CLICK")) {
//      res.setCode("fail");
//      res.setMsg("请选择回复消息...");
//      return res;
//    }
    try {
      if (StringUtils.isBlank(model.getMenuId())) {
        Integer menuSeq = model.getMenuSeq();
        String wxId = model.getWxId();
        WXSelfMenuResponse menuRes = wxSelfMenuService.findByMenuSeq(menuSeq, wxId);
        if (null != menuRes) {
          res.setMsg("位置[ " + model.getMenuSeq() + " ]菜单已经存在...");
          return res;
        }
        CreateWXSelfMenuRequest createDto = new CreateWXSelfMenuRequest();
        BeanUtils.copyProperties(model, createDto);
        // 设置上级ID
        if (",1,2,3,".indexOf("," + menuSeq + ",") < 0) {
          menuSeq = Integer.parseInt(menuSeq.toString().substring(0, 1));
          menuRes = wxSelfMenuService.findByMenuSeq(menuSeq, wxId);
          if (null == menuRes) {
            res.setMsg("位置[ " + model.getMenuSeq() + " ]上级菜单不存在...");
            return res;
          }
          createDto.setMenuPid(menuRes.getMenuId());
        }
        wxSelfMenuService.saveOne(createDto);
      } else {
        UpdateWXSelfMenuRequest updateDto = new UpdateWXSelfMenuRequest();
        BeanUtils.copyProperties(model, updateDto);
        wxSelfMenuService.editById(updateDto);
      }
      res.setCode("success");
    } catch (Exception e) {
      res.setMsg(e.getMessage());
    }
    return res;
  }

  // 删除
  @ResponseBody
  @RequestMapping(value="/del",method=RequestMethod.POST)
  public ResData del(String id) {
    ResData res = new ResData();
    res.setCode("fail");
    if (null == id) {
      res.setMsg("请求参数菜单id为空...");
      return res;
    }
    List<WXMenu> menuList = buildModelList(wxSelfMenuService.findChildById(id));
    if (null != menuList && menuList.size()>0) {
      res.setMsg("请先删除子菜单...");
      return res;
    }
    try {
      wxSelfMenuService.delById(id);
      res.setCode("success");
    } catch (Exception e) {
      res.setMsg("删除失败:" + e.getMessage());
    }
    return res;
  }
  
  // 同步到微信
  @ResponseBody
  @RequestMapping(value = "/sync", method = RequestMethod.POST)
  public ResData wxSync(String wxId) {
    ResData res = new ResData();
    res.setCode("fail");
    try {
      String json = setMenuService.createMenu(wxId);
      JSONObject obj = (JSONObject) JSON.parse(json);
      if (obj.get("errmsg").toString().equals("ok")) {
        res.setCode("success");
        res.setMsg("恭喜同步成功!菜单缓存时间为24小时");
      } else {
        res.setMsg(obj.get("errmsg").toString());
      }
    } catch (Exception e) {
      res.setMsg("同步微信失败:" + e.getMessage());
    }
    return res;
  }

  //
  private WXMenu buildModel(WXSelfMenuResponse res) {
    WXMenu model = null;
    if (null != res) {
      model = new WXMenu();
      BeanUtils.copyProperties(res, model);
    }
    return model;
  }

  //
  private List<WXMenu> buildModelList(List<WXSelfMenuResponse> resList) {
    List<WXMenu> list = new ArrayList<>();
    if (null != resList) {
      WXMenu model = null;
      for (WXSelfMenuResponse res : resList) {
        model = buildModel(res);
        if (null != model) {
          list.add(model);
        }
      }
    }
    return list;
  }
}
