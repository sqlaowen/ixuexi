package com.zgxcw.service.infrastructure.impl;

import com.zgxcw.framework.service.ServiceException;
import com.zgxcw.service.infrastructure.bean.WxSendMsgLog;
import com.zgxcw.service.infrastructure.dao.WxSendMsgLogMapper;
import com.zgxcw.service.infrastructure.dto.request.WxSendMsgLog.CreateWxSendMsgLogRequest;
import com.zgxcw.service.infrastructure.service.WxSendMsgLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.UUID;

/**
 * Created by huolh on 2016/7/26.
 */
public class WxSendMsgLogImpl implements WxSendMsgLogService {
  private Logger logger = LoggerFactory.getLogger(WxSendMsgLogImpl.class);
  private WxSendMsgLogMapper wxSendMsgLogMapper;

  public void setWxSendMsgLogMapper(WxSendMsgLogMapper wxSendMsgLogMapper) {
    this.wxSendMsgLogMapper = wxSendMsgLogMapper;
  }

  @Override
  public String saveOne(CreateWxSendMsgLogRequest request) throws ServiceException {
    if (null == request) {
      logger.error("请求参数CreateWxSendMsgLogRequest为空...");
      throw new ServiceException("请求参数CreateWxSendMsgLogRequest为空...");
    }
    WxSendMsgLog model = new WxSendMsgLog();
    String id = UUID.randomUUID().toString().replaceAll("-", "");
    BeanUtils.copyProperties(request, model);
    model.setFansId(id);
    model.setCreateTime(new Date().getTime());
    try {
      wxSendMsgLogMapper.saveOne(model);
    } catch (Exception ex) {
      logger.error("调用saveOne方法失败,错误信息{}", ex);
      throw new ServiceException(ex.getMessage());
    }
    return id;
  }
}
