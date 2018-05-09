package com.zgxcw.service.infrastructure.service;

import com.zgxcw.framework.service.ServiceException;
import com.zgxcw.service.infrastructure.dto.request.WxSendMsgLog.CreateWxSendMsgLogRequest;

/**
 * Created by huolh on 2016/7/26.
 */
public interface WxSendMsgLogService {
  /**
   * 保存
   *
   * @param request :CreateWxSendMsgLogRequest
   * @return
   * @throws ServiceException
   */
  String saveOne(CreateWxSendMsgLogRequest request) throws ServiceException;
}
