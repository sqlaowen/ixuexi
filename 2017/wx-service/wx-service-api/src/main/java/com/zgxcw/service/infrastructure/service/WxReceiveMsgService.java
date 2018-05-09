package com.zgxcw.service.infrastructure.service;

import com.zgxcw.framework.service.ServiceException;
import com.zgxcw.service.infrastructure.dto.request.WxReceiveMsg.CreateWxReceiveMsgRequest;
import com.zgxcw.service.infrastructure.dto.response.WxReceiveMsgResponse;

/**
 * Created by huolh on 2016/7/25.
 */
public interface WxReceiveMsgService {

  /**
   * 根据id查询
   *
   * @param id :消息id
   * @return WXAutoResResponse
   * @throws ServiceException
   */
  WxReceiveMsgResponse findById(String id) throws ServiceException;

  /**
   * 保存
   *
   * @param request :CreateWxReceiveMsgRequest
   * @return
   * @throws ServiceException
   */
  String saveOne(CreateWxReceiveMsgRequest request) throws ServiceException;
}
