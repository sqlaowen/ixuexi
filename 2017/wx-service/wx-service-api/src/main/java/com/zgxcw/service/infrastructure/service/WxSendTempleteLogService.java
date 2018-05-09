package com.zgxcw.service.infrastructure.service;

import com.zgxcw.framework.service.ServiceException;
import com.zgxcw.service.infrastructure.dto.request.WxSendTempleteLog.CreateWxSendTempleteLogRequest;

/**
 * Created by huolh on 2016/7/26.
 */
public interface WxSendTempleteLogService {
  /**
   * 保存
   *
   * @param request :CreateWxSendMsgLogRequest
   * @return
   * @throws ServiceException
   */
  String saveOne(CreateWxSendTempleteLogRequest request) throws ServiceException;
}
