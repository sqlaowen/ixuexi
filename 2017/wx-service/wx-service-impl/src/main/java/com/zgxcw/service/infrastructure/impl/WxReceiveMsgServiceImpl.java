package com.zgxcw.service.infrastructure.impl;

import com.zgxcw.framework.service.ServiceException;
import com.zgxcw.service.infrastructure.bean.WxReceiveMsg;
import com.zgxcw.service.infrastructure.dao.WxReceiveMsgMapper;
import com.zgxcw.service.infrastructure.dto.request.WxReceiveMsg.CreateWxReceiveMsgRequest;
import com.zgxcw.service.infrastructure.dto.response.WxReceiveMsgResponse;
import com.zgxcw.service.infrastructure.service.WxReceiveMsgService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.UUID;

/**
 * Created by huolh on 2016/7/25.
 */
public class WxReceiveMsgServiceImpl implements WxReceiveMsgService {
  private Logger logger = LoggerFactory.getLogger(WxReceiveMsgServiceImpl.class);
  private WxReceiveMsgMapper wxReceiveMsgMapper;

  public void setWxReceiveMsgMapper(WxReceiveMsgMapper wxReceiveMsgMapper) {
    this.wxReceiveMsgMapper = wxReceiveMsgMapper;
  }

  @Override
  public WxReceiveMsgResponse findById(String id) throws ServiceException {
    if (StringUtils.isBlank(id)) {
      logger.error("请求参数id为空...");
      throw new ServiceException("请求参数id为空...");
    }
    WxReceiveMsg model = null;
    try {
      model = wxReceiveMsgMapper.findById(id);
      return buildRes(model);
    } catch (Exception ex) {
      logger.error("调用findById方法失败,错误信息{}", ex);
      throw new ServiceException(ex.getMessage());
    }
  }

  @Override
  public String saveOne(CreateWxReceiveMsgRequest request) throws ServiceException {
    if (null == request) {
      logger.error("请求参数CreateWxReceiveMsgRequest为空...");
      throw new ServiceException("请求参数CreateWxReceiveMsgRequest为空...");
    }
    WxReceiveMsg model = new WxReceiveMsg();
    BeanUtils.copyProperties(request, model);
    String id = UUID.randomUUID().toString().replaceAll("-", "");
    model.setReceiveMsgId(id);
    model.setCreateTime(new Date().getTime());
    try {
      wxReceiveMsgMapper.saveOne(model);
    } catch (Exception ex) {
      logger.error("调用saveOne方法失败,错误信息{}", ex);
      throw new ServiceException(ex.getMessage());
    }
    return id;
  }

  private WxReceiveMsgResponse buildRes(WxReceiveMsg model) {
    WxReceiveMsgResponse res = null;
    if (null != model) {
      res = new WxReceiveMsgResponse();
      BeanUtils.copyProperties(model, res);
    }
    return res;
  }
}
