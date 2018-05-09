package com.zgxcw.service.infrastructure.service;

import java.util.List;

import com.zgxcw.framework.service.ServiceException;
import com.zgxcw.service.infrastructure.dto.request.wxselfmenu.CreateWXSelfMenuRequest;
import com.zgxcw.service.infrastructure.dto.request.wxselfmenu.UpdateWXSelfMenuRequest;
import com.zgxcw.service.infrastructure.dto.response.WXSelfMenuResponse;

public interface WXSelfMenuService {

  /**
   * 根据id查询
   * 
   * @param menuId
   * @return
   * @throws ServiceException
   */
  WXSelfMenuResponse findById(String menuId) throws ServiceException;

  /**
   * 查询所有
   * 
   * @param wxId
   * @return
   * @throws ServiceException
   */
  List<WXSelfMenuResponse> findList(String wxId) throws ServiceException;
  
  /**
   * 根据id查询子级
   * 
   * @param wxId
   * @return
   * @throws ServiceException
   */
  List<WXSelfMenuResponse> findChildById(String wxId) throws ServiceException;

  /**
   * 根据菜单顺序查询菜单
   * 
   * @param menuSeq
   * @param wxId
   * @return
   * @throws ServiceException
   */
  WXSelfMenuResponse findByMenuSeq(Integer menuSeq, String wxId) throws ServiceException;
  
  /**
   * 保存
   * 
   * @param request
   * @return
   * @throws ServiceException
   */
  String saveOne(CreateWXSelfMenuRequest request) throws ServiceException;

  /**
   * 修改
   * 
   * @param request :UpdateWXSelfMenuRequest
   * @return
   * @throws ServiceException
   */
  int editById(UpdateWXSelfMenuRequest request) throws ServiceException;

  /**
   * 删除所有
   * 
   * @param wxId
   * @return
   * @throws ServiceException
   */
  int delAll(String wxId) throws ServiceException;
  
  /**
   * 删除
   * 
   * @param menuId
   * @return
   * @throws ServiceException
   */
  int delById(String menuId) throws ServiceException;

}
