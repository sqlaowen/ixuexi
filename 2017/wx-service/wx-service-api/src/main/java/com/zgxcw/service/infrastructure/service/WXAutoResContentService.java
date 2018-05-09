package com.zgxcw.service.infrastructure.service;

import java.util.List;

import com.zgxcw.framework.service.ServiceException;
import com.zgxcw.service.infrastructure.dto.request.wxautorescontent.CreateWXAutoResContentRequest;
import com.zgxcw.service.infrastructure.dto.request.wxautorescontent.UpdateWXAutoResContentRequest;
import com.zgxcw.service.infrastructure.dto.response.WXAutoResContentResponse;

public interface WXAutoResContentService {

  /**
   * 保存
   * 
   * @param request :CreateWXAutoResContentRequest
   * @return
   * @throws ServiceException
   */
  String saveOne(CreateWXAutoResContentRequest request) throws ServiceException;

  /**
   * 根据id查询
   * 
   * @param contentId
   * @return
   * @throws ServiceException
   */
  WXAutoResContentResponse findById(String contentId) throws ServiceException;

  /**
   * 根据自动回复id查询
   * 
   * @param resId
   * @return
   * @throws ServiceException
   */
  List<WXAutoResContentResponse> findByResId(String resId) throws ServiceException;

  /**
   * 修改
   * 
   * @param request :UpdateWXAutoResContentRequest
   * @return
   * @throws ServiceException
   */
  int eidtById(UpdateWXAutoResContentRequest request) throws ServiceException;

  /**
   * 根据resId删除
   * @param resId
   * @return
   * @throws ServiceException
   */
  int deleteByResId(String resId) throws  ServiceException;
}
