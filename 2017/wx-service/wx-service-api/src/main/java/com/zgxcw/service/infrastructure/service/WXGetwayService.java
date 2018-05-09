package com.zgxcw.service.infrastructure.service;

import com.zgxcw.framework.service.ServiceException;
import com.zgxcw.service.infrastructure.dto.request.wxgetway.CreateWXGetwayRequest;
import com.zgxcw.service.infrastructure.dto.request.wxgetway.UpdateWXGetwayRequest;
import com.zgxcw.service.infrastructure.dto.response.WXGetwayResponse;

/**
 * Created by huolh on 2016/3/1.
 */
public interface WXGetwayService {
  /**
   * 根据Id查询
   * @param wxId
   * @return
   * @throws ServiceException
   */
  WXGetwayResponse findById(String wxId) throws ServiceException;

  /**
   * 保存一条记录
   * @param request
   * @return
   * @throws ServiceException
   */
  String saveOne(CreateWXGetwayRequest request) throws ServiceException;

  /**
   * 更新一条记录
   * @param request
   * @return
   * @throws ServiceException
   */
  int editById(UpdateWXGetwayRequest request) throws ServiceException;
}
