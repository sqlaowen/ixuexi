package com.zgxcw.service.infrastructure.impl;

import com.zgxcw.framework.service.ServiceException;
import com.zgxcw.service.infrastructure.bean.WxSendTempleteLog;
import com.zgxcw.service.infrastructure.dao.WxSendTempleteLogMapper;
import com.zgxcw.service.infrastructure.dto.request.WxSendTempleteLog.CreateWxSendTempleteLogRequest;
import com.zgxcw.service.infrastructure.service.WxSendTempleteLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.UUID;

/**
 * Created by huolh on 2016/7/26.
 */
public class WxSendTempleteLogImpl implements WxSendTempleteLogService {
  private Logger logger = LoggerFactory.getLogger(WxSendMsgLogImpl.class);
  private WxSendTempleteLogMapper wxSendTempleteLogMapper;

  public void setWxSendTempleteLogMapper(WxSendTempleteLogMapper wxSendTempleteLogMapper) {
    this.wxSendTempleteLogMapper = wxSendTempleteLogMapper;
  }

  @Override
  public String saveOne(CreateWxSendTempleteLogRequest request) throws ServiceException {
    if (null == request) {
      logger.error("请求参数CreateWxSendTempleteLogRequest为空...");
      throw new ServiceException("请求参数CreateWxSendTempleteLogRequest为空...");
    }
    WxSendTempleteLog model = new WxSendTempleteLog();
    String id = UUID.randomUUID().toString().replaceAll("-", "");
    BeanUtils.copyProperties(request, model);
    model.setLogId(id);
    model.setCreateTime(new Date().getTime());
    try {
      wxSendTempleteLogMapper.saveOne(model);
    } catch (Exception ex) {
      logger.error("调用saveOne方法失败,错误信息{}", ex);
      throw new ServiceException(ex.getMessage());
    }
    return id;
  }
}
