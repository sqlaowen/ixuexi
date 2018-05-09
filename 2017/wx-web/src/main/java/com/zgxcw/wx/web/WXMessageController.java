package com.zgxcw.wx.web;


import com.zgxcw.service.infrastructure.dto.request.page.PageRequest;
import com.zgxcw.service.infrastructure.dto.request.wxautores.CreateWXAutoResRequest;
import com.zgxcw.service.infrastructure.dto.request.wxautores.QueryWxAutoResRequest;
import com.zgxcw.service.infrastructure.dto.request.wxautores.UpdateWXAutoResRequest;
import com.zgxcw.service.infrastructure.dto.request.wxautorescontent.CreateWXAutoResContentRequest;
import com.zgxcw.service.infrastructure.dto.response.PageResponse;
import com.zgxcw.service.infrastructure.dto.response.WXAutoResContentResponse;
import com.zgxcw.service.infrastructure.dto.response.WXAutoResResponse;
import com.zgxcw.service.infrastructure.service.WXAutoResContentService;
import com.zgxcw.service.infrastructure.service.WXAutoResService;
import com.zgxcw.wx.domain.QueryWxAutoRes;
import com.zgxcw.wx.domain.WXAutoRes;
import com.zgxcw.wx.utils.PageRes;
import com.zgxcw.wx.utils.ResData;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/wx/message")
public class WXMessageController {

  @Autowired
  private WXAutoResService wxAutoResService;

  @Autowired
  private WXAutoResContentService wxAutoResContentService;

  @RequestMapping("/index")
  public String index() {
    return "/wx_message/index";
  }

  @ResponseBody
  @RequestMapping(value = "/getautorespagelist", method = RequestMethod.POST)
  public PageRes<WXAutoRes> getAutoResPageList(QueryWxAutoRes request) {
    QueryWxAutoResRequest queryWxAutoResRequest = new QueryWxAutoResRequest();
    queryWxAutoResRequest.setWxId(request.getWxId());
    queryWxAutoResRequest.setResName(request.getResName());
    queryWxAutoResRequest.setResType(request.getResType());
    queryWxAutoResRequest.setResState(request.getResState());
    queryWxAutoResRequest.setMsgType(request.getMsgType());
    PageRequest<QueryWxAutoResRequest> pageRequest = new PageRequest<QueryWxAutoResRequest>();
    pageRequest.setT(queryWxAutoResRequest);
    pageRequest.setPageNum(request.getPageNum());
    pageRequest.setPageSize(request.getPageSize());

    PageRes<WXAutoRes> pageRes = new PageRes<WXAutoRes>();
    List<WXAutoRes> modelList = new ArrayList<WXAutoRes>();
    try{
      PageResponse<WXAutoResResponse> pageResponse = wxAutoResService.getPageList(pageRequest);
      if(pageResponse!=null&&pageResponse.getList()!=null){
        pageRes.setTotal(pageResponse.getTotal());
        for(WXAutoResResponse item : pageResponse.getList()){
          modelList.add(buildModel(item));
        }
      }
    }catch (Exception ex){
      pageRes.setTotal(0);
      pageRes.setMsg("error");
    }
    pageRes.setList(modelList);
    return pageRes;
  }

  @RequestMapping("/add")
  public String add(String id, ModelMap map) {
    WXAutoRes model = new WXAutoRes();
    if (null != id) {
      model = buildModel(wxAutoResService.findById(id));
      if(model.getResType().equals("text")){
        List<WXAutoResContentResponse> contentList = wxAutoResContentService.findByResId(id);
        if(contentList!=null&&contentList.size()>0){
          model.setMsgContent(contentList.get(0).getDescription());
        }
      }
    }
    map.addAttribute("model", model);
    return "/wx_message/add";
  }


  @ResponseBody
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public ResData save(WXAutoRes model) {
    ResData res = new ResData();
    res.setCode("fail");
    try {
      String resId = model.getResId();
      if (StringUtils.isBlank(model.getResId())) {
        CreateWXAutoResRequest createDto = new CreateWXAutoResRequest();
        BeanUtils.copyProperties(model, createDto);
        resId = wxAutoResService.saveOne(createDto);
      } else {
        UpdateWXAutoResRequest updateDto = new UpdateWXAutoResRequest();
        BeanUtils.copyProperties(model, updateDto);
        wxAutoResService.editById(updateDto);
      }
      if(model.getResType().equals("text")){
        wxAutoResContentService.deleteByResId(resId);
        CreateWXAutoResContentRequest createContentDto = new CreateWXAutoResContentRequest();
        createContentDto.setResId(resId);
        createContentDto.setStatus(0);
        createContentDto.setDescription(model.getMsgContent());
        wxAutoResContentService.saveOne(createContentDto);
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
      wxAutoResService.deleteById(id);
      res.setCode("success");
    } catch (Exception e) {
      res.setMsg("删除失败:" + e.getMessage());
    }
    return res;
  }

  // 删除
  @ResponseBody
  @RequestMapping(value = "/setenable", method = RequestMethod.POST)
  public ResData setEnable(@RequestParam(value = "resId", required = true) String resId,
      @RequestParam(value = "msgType", required = true) String msgType,
      @RequestParam(value = "wxId", required = true) String wxId) {
    ResData res = new ResData();
    res.setCode("fail");
    if (null == resId) {
      res.setMsg("请求参数消息resId为空...");
      return res;
    }
    if (null == msgType) {
      res.setMsg("请求参数消息msgType为空...");
      return res;
    }
    if (null == wxId) {
      res.setMsg("请求参数消息wxId为空...");
      return res;
    }
    try {
      wxAutoResService.editSetEnable(resId,msgType,wxId);
      res.setCode("success");
    } catch (Exception e) {
      res.setMsg("删除失败:" + e.getMessage());
    }
    return res;
  }

  private WXAutoRes buildModel(WXAutoResResponse res) {
    WXAutoRes model = null;
    if (null != res) {
      model = new WXAutoRes();
      BeanUtils.copyProperties(res, model);
    }    return model;
  }
}
