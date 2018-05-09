package com.zgxcw.service.combine.service;

import com.zgxcw.framework.service.ServiceException;

public interface SetMenuService {

  /**
   * 设置菜单
   * 
   * @param wxId :微信网关id
   * @return
   * @throws ServiceException
   */
  String createMenu(String wxId) throws ServiceException;

  /**
   * 删除菜单
   * 
   * @param wxId :微信网关id
   * @return
   * @throws ServiceException
   */
  String delMenu(String wxId) throws ServiceException;
}
