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

import com.zgxcw.service.infrastructure.dto.request.wxautorescontent.CreateWXAutoResContentRequest;
import com.zgxcw.service.infrastructure.dto.request.wxautorescontent.UpdateWXAutoResContentRequest;
import com.zgxcw.service.infrastructure.dto.response.WXAutoResContentResponse;
import com.zgxcw.service.infrastructure.service.WXAutoResContentService;
import com.zgxcw.wx.domain.WXAutoResContent;
import com.zgxcw.wx.utils.PageRes;
import com.zgxcw.wx.utils.ResData;

@Controller
@RequestMapping("/wx/content")
public class WXMsgContentController {

  @Autowired
  private WXAutoResContentService wxAutoResContentService;

  @RequestMapping("/index")
  public String index() {
    return "/wx_passive/index";
  }

  @RequestMapping("/add")
  public String add(String id, ModelMap map) {
    WXAutoResContent model = new WXAutoResContent();
    if (null != id) {
      model = buildModel(wxAutoResContentService.findById(id));
    }
    map.addAttribute("model", model);
    return "/wx_passive/add";
  }

  @ResponseBody
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public PageRes<WXAutoResContent> list(String resId) {
    PageRes<WXAutoResContent> res = new PageRes<>();
    if (StringUtils.isBlank(resId)) {
      res.setMsg("请求参数resId为空...");
      return res;
    }
    List<WXAutoResContent> list = buildModelList(wxAutoResContentService.findByResId(resId));
    res.setList(list);
    res.setTotal(list.size());
    return res;
  }

  // 添加/修改
  @ResponseBody
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public ResData save(WXAutoResContent model) {
    ResData res = new ResData();
    res.setCode("fail");
    try {
      if (StringUtils.isBlank(model.getContentId())) {
        CreateWXAutoResContentRequest createDto = new CreateWXAutoResContentRequest();
        BeanUtils.copyProperties(model, createDto);
        wxAutoResContentService.saveOne(createDto);
      } else {
        UpdateWXAutoResContentRequest updateDto = new UpdateWXAutoResContentRequest();
        BeanUtils.copyProperties(model, updateDto);
        wxAutoResContentService.eidtById(updateDto);
      }
      res.setCode("success");
    } catch (Exception e) {
      res.setMsg(e.getMessage());
    }
    return res;
  }

  // 删除
  @ResponseBody
  @RequestMapping(value = "/del", method = RequestMethod.POST)
  public ResData del(String id) {
    ResData res = new ResData();
    res.setCode("fail");
    if (null == id) {
      res.setMsg("请求参数消息id为空...");
      return res;
    }
    try {
      UpdateWXAutoResContentRequest updateDto = new UpdateWXAutoResContentRequest();
      updateDto.setContentId(id);
      updateDto.setStatus(1);
      wxAutoResContentService.eidtById(updateDto);
      res.setCode("success");
    } catch (Exception e) {
      res.setMsg("删除失败:" + e.getMessage());
    }
    return res;
  }

  //
  private WXAutoResContent buildModel(WXAutoResContentResponse res) {
    WXAutoResContent model = null;
    if (null != res) {
      model = new WXAutoResContent();
      BeanUtils.copyProperties(res, model);
    }
    return model;
  }

  //
  private List<WXAutoResContent> buildModelList(List<WXAutoResContentResponse> resList) {
    List<WXAutoResContent> list = new ArrayList<>();
    if (null != resList) {
      WXAutoResContent model = null;
      for (WXAutoResContentResponse res : resList) {
        model = buildModel(res);
        if (null != model) {
          list.add(model);
        }
      }
    }
    return list;
  }
}
