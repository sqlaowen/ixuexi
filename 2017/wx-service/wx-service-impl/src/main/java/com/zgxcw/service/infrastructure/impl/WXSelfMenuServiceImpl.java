package com.zgxcw.service.infrastructure.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.zgxcw.framework.service.ServiceException;
import com.zgxcw.service.infrastructure.bean.WXSelfMenu;
import com.zgxcw.service.infrastructure.dao.WXSelfMenuMapper;
import com.zgxcw.service.infrastructure.dto.request.wxselfmenu.CreateWXSelfMenuRequest;
import com.zgxcw.service.infrastructure.dto.request.wxselfmenu.UpdateWXSelfMenuRequest;
import com.zgxcw.service.infrastructure.dto.response.WXSelfMenuResponse;
import com.zgxcw.service.infrastructure.service.WXSelfMenuService;

public class WXSelfMenuServiceImpl implements WXSelfMenuService {

  private Logger log = LoggerFactory.getLogger(this.getClass());
  private WXSelfMenuMapper wxSelfMenuMapper;

  public void setWxSelfMenuMapper(WXSelfMenuMapper wxSelfMenuMapper) {
    this.wxSelfMenuMapper = wxSelfMenuMapper;
  }

  /**
   * 根据id查询
   * 
   * @param menuId
   * @return
   * @throws ServiceException
   */
  @Override
  public WXSelfMenuResponse findById(String menuId) throws ServiceException {
    if (null == menuId || "".equals(menuId.trim())) {
      log.error("请求参数menuId为空...");
      throw new ServiceException("请求参数menuId为空...");
    }
    return buildRes(wxSelfMenuMapper.findById(menuId));
  }

  /**
   * 查询所有
   * 
   * @param wxId
   * @return
   * @throws ServiceException
   */
  @Override
  public List<WXSelfMenuResponse> findList(String wxId) throws ServiceException {
    if (StringUtils.isBlank(wxId)) {
      log.error("请求参数wxId为空...");
      throw new ServiceException("请求参数wxId为空...");
    }
    return buildResList(wxSelfMenuMapper.findList(wxId));
  }
  
  /**
   * 根据id查询子级
   * 
   * @param wxId
   * @return
   * @throws ServiceException
   */
  public List<WXSelfMenuResponse> findChildById(String wxId) throws ServiceException{
    if (StringUtils.isBlank(wxId)) {
      log.error("请求参数wxId为空...");
      throw new ServiceException("请求参数wxId为空...");
    }
    return buildResList(wxSelfMenuMapper.findChildById(wxId));
  }

  /**
   * 根据菜单顺序查询菜单
   * 
   * @param menuSeq
   * @param wxId
   * @return
   * @throws ServiceException
   */
  public WXSelfMenuResponse findByMenuSeq(Integer menuSeq, String wxId) throws ServiceException {
    if (null == menuSeq) {
      log.error("请求参数menuSeq为空...");
      throw new ServiceException("请求参数menuSeq为空...");
    }
    if (StringUtils.isBlank(wxId)) {
      log.error("请求参数wxId为空...");
      throw new ServiceException("请求参数wxId为空...");
    }
    return buildRes(wxSelfMenuMapper.findByMenuSeq(menuSeq, wxId));
  }

  /**
   * 保存
   * 
   * @param request
   * @return
   * @throws ServiceException
   */
  @Override
  public String saveOne(CreateWXSelfMenuRequest request) throws ServiceException {
    if (null == request) {
      log.error("请求参数CreateWXSelfMenuRequest为空...");
      throw new ServiceException("请求参数CreateWXSelfMenuRequest为空...");
    }
    if (null == request.getWxId() || "".equals(request.getWxId().trim())) {
      log.error("请求参数CreateWXSelfMenuRequest.WxId为空...");
      throw new ServiceException("请求参数CreateWXSelfMenuRequest.WxId为空...");
    }
    WXSelfMenu model = new WXSelfMenu();
    BeanUtils.copyProperties(request, model);
    String id = UUID.randomUUID().toString().replaceAll("-", "");
    model.setMenuId(id);
    model.setCreateTime(new Date().getTime());
    wxSelfMenuMapper.saveOne(model);
    return id;
  }

  /**
   * 修改
   * 
   * @param request :UpdateWXSelfMenuRequest
   * @return
   * @throws ServiceException
   */
  public int editById(UpdateWXSelfMenuRequest request) throws ServiceException {
    if (null == request) {
      log.error("请求参数UpdateWXSelfMenuRequest为空...");
      throw new ServiceException("请求参数UpdateWXSelfMenuRequest为空...");
    }
    if (null == request.getMenuId() || "".equals(request.getMenuId().trim())) {
      log.error("请求参数UpdateWXSelfMenuRequest.MenuId为空...");
      throw new ServiceException("请求参数UpdateWXSelfMenuRequest.MenuId为空...");
    }
    if (null == request.getWxId() || "".equals(request.getWxId().trim())) {
      log.error("请求参数UpdateWXSelfMenuRequest.WxId为空...");
      throw new ServiceException("请求参数UpdateWXSelfMenuRequest.WxId为空...");
    }
    WXSelfMenu model = new WXSelfMenu();
    BeanUtils.copyProperties(request, model);
    int rev = 0;
    model.setUpdateTime(new Date().getTime());
    rev = wxSelfMenuMapper.editById(model);
    return rev;
  }

  /**
   * 删除所有
   * 
   * @param wxId
   * @return
   * @throws ServiceException
   */
  @Override
  public int delAll(String wxId) throws ServiceException {
    if (null == wxId) {
      log.error("请求参数wxId为空...");
      throw new ServiceException("请求参数wxId为空...");
    }
    return wxSelfMenuMapper.delAll(wxId);
  }
  
  /**
   * 删除
   * 
   * @param menuId
   * @return
   * @throws ServiceException
   */
  @Override
  public int delById(String menuId) throws ServiceException {
    if (null == menuId) {
      log.error("请求参数menuId为空...");
      throw new ServiceException("请求参数menuId为空...");
    }
    return wxSelfMenuMapper.delById(menuId);
  }

  /**
   * 构建WXSelfMenuResponse
   * 
   * @param model :WXSelfMenu
   * @return
   */
  private WXSelfMenuResponse buildRes(WXSelfMenu model) {
    WXSelfMenuResponse res = null;
    if (null != model) {
      res = new WXSelfMenuResponse();
      BeanUtils.copyProperties(model, res);
    }
    return res;
  }

  /**
   * 构建List<WXSelfMenuResponse>
   * 
   * @param modelList :List<WXSelfMenu>
   * @return List<WXSelfMenuResponse>
   */
  private List<WXSelfMenuResponse> buildResList(List<WXSelfMenu> modelList) {
    List<WXSelfMenuResponse> resList = null;
    if (null != modelList) {
      resList = new ArrayList<WXSelfMenuResponse>();
      WXSelfMenuResponse res = null;
      for (WXSelfMenu model : modelList) {
        res = buildRes(model);
        if (null != res) {
          resList.add(res);
        }
      }
    }
    return resList;
  }

}
