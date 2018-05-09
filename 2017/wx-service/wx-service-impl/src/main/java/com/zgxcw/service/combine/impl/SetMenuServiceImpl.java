package com.zgxcw.service.combine.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.zgxcw.framework.service.ServiceException;
import com.zgxcw.service.combine.service.SetMenuService;
import com.zgxcw.service.infrastructure.dto.response.WXSelfMenuResponse;
import com.zgxcw.service.infrastructure.service.WXSelfMenuService;
import com.zgxcw.service.utils.AccessTokenUtil;
import com.zgxcw.service.utils.HttpsUtil;

public class SetMenuServiceImpl implements SetMenuService {

  private Logger log=LoggerFactory.getLogger(this.getClass());
  
  private WXSelfMenuService wxSelfMenuService;
  public void setWxSelfMenuService(WXSelfMenuService wxSelfMenuService) {
    this.wxSelfMenuService = wxSelfMenuService;
  }
  
  private HttpsUtil httpsUtil;
  public void setHttpsUtil(HttpsUtil httpsUtil) {
    this.httpsUtil = httpsUtil;
  }

  /**
   * 设置菜单
   * 
   * @param wxId :微信网关id
   * @return
   * @throws ServiceException
   */
  @Override
  public String createMenu(String wxId) throws ServiceException {
    String dataStr = getMenu(wxId);
    String url = String.format("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s",AccessTokenUtil.getAccessToken(wxId));
    String rev = httpsUtil.getHttpsResponse(url, "POST", dataStr);
    log.debug("设置菜单返回:{},对应微信网关id:{}", rev, wxId);
    return rev;
  }
  
  /**
   * 删除菜单
   * 
   * @param wxId :微信网关id
   * @return
   * @throws ServiceException
   */
  @Override
  public String delMenu(String wxId) throws ServiceException {
    String url = String.format("https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=%s",AccessTokenUtil.getAccessToken(wxId));
    String rev = httpsUtil.getHttpsResponse(url, "GET", null);
    log.debug("删除菜单返回:{},对应微信网关id:{}", rev, wxId);
    return rev;
  }
  
  /**
   * 获取将要设置的菜单
   * 
   * @param wxId :微信网关id
   * @return
   * @throws ServiceException
   */
  public String getMenu(String wxId) throws ServiceException {
    if (StringUtils.isBlank(wxId)) {
      log.error("请求参数wxId不能为空...");
      throw new ServiceException("请求参数wxId不能为空...");
    }
    List<WXSelfMenuResponse> menuList = wxSelfMenuService.findList(wxId);
    if (null == menuList || menuList.size() == 0) {
      log.error("微信网关id:{}对应菜单为空...", wxId);
      throw new ServiceException("微信网关id:" + wxId + "对应菜单为空...");
    }
    log.debug("微信网关id:{}对应菜单:{}", wxId, JSON.toJSONString(menuList));
    plug(menuList);
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    sb.append("\"button\":[");
    int i = 0;
    for (WXSelfMenuResponse p : menuList) {
      if (StringUtils.isBlank(p.getMenuPid())) {
        if (null != p.getChildren()) {
          if (i > 0)
            sb.append(",");
          sb.append("{");
          sb.append(String.format("\"name\":\"%s\"", p.getMenuName()));
          sb.append(",\"sub_button\":[");
          for (int j = 0; j < p.getChildren().size(); j++) {
            if (j > 0)
              sb.append(",");
            sb.append(setMenuJson(p.getChildren().get(j)));
          }
          sb.append("]");
          sb.append("}");
        } else {
          if (i > 0)
            sb.append(",");
          sb.append(setMenuJson(p));
        }
        i++;
      }
    }
    sb.append("]}");
    log.debug("微信网关id:{}对应菜单json:{}", wxId, sb.toString());
    return sb.toString();
  }

  //
  private String setMenuJson(WXSelfMenuResponse menu) {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    sb.append(String.format("\"name\":\"%s\"", menu.getMenuName()));
    sb.append(String.format(",\"type\":\"%s\"", menu.getMenuType().toLowerCase()));
    if (StringUtils.isNotBlank(menu.getMenuKey())) {
      sb.append(String.format(",\"key\":\"%s\"", menu.getMenuKey()));
    }
    if (StringUtils.isNotBlank(menu.getMenuUrl())) {
      sb.append(String.format(",\"url\":\"%s\"", menu.getMenuUrl()));
    }
    sb.append("}");
    return sb.toString();
  }

  //设置上级下级
  private void plug(List<WXSelfMenuResponse> menuList) {
    for (WXSelfMenuResponse p : menuList) {
      for (WXSelfMenuResponse c : menuList) {
        if (StringUtils.isBlank(c.getMenuPid()) || !c.getMenuPid().equals(p.getMenuId())) {
          continue;
        }
        c.setParent(p);
        if (null == p.getChildren()) {
          p.setChildren(new ArrayList<WXSelfMenuResponse>());
        }
        if (!p.getChildren().contains(c)) {
          p.getChildren().add(c);
        }
      }
    }
  }
  
}
