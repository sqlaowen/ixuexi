package com.zgxcw.service.infrastructure.impl;

import com.zgxcw.framework.service.ServiceException;
import com.zgxcw.service.infrastructure.bean.WxMsgTemplete;
import com.zgxcw.service.infrastructure.dao.WxMsgTempleteMapper;
import com.zgxcw.service.infrastructure.dto.request.WxMsgTemplete.CreateWxMsgTempleteRequest;
import com.zgxcw.service.infrastructure.dto.response.WxMsgTempleteResponse;
import com.zgxcw.service.infrastructure.service.WxMsgTempleteService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

/**
 * Created by huolh on 2016/7/26.
 */
public class WxMsgTempleteImpl implements WxMsgTempleteService {
  private Logger logger = LoggerFactory.getLogger(WxMsgTempleteImpl.class);
  private WxMsgTempleteMapper wxMsgTempleteMapper;

  public void setWxMsgTempleteMapper(WxMsgTempleteMapper wxMsgTempleteMapper) {
    this.wxMsgTempleteMapper = wxMsgTempleteMapper;
  }

  @Override
  public WxMsgTempleteResponse findById(String tempId) throws ServiceException {
    if (StringUtils.isBlank(tempId)) {
      logger.error("请求参数tempId为空...");
      throw new ServiceException("请求参数tempId为空...");
    }
    WxMsgTemplete model = null;
    try {
      model = wxMsgTempleteMapper.findById(tempId);
      return buildRes(model);
    } catch (Exception ex) {
      logger.error("调用findById方法失败,错误信息{}", ex);
      throw new ServiceException(ex.getMessage());
    }
  }

  @Override
  public String saveOne(CreateWxMsgTempleteRequest request) throws ServiceException {
    if (null == request) {
      logger.error("请求参数CreateWxMsgTempleteRequest为空...");
      throw new ServiceException("请求参数CreateWxMsgTempleteRequest为空...");
    }
    if (StringUtils.isBlank(request.getTempId())) {
      logger.error("请求参数CreateWxMsgTempleteRequest.TempId为空...");
      throw new ServiceException("请求参数CreateWxMsgTempleteRequest.TempId为空...");
    }
    WxMsgTemplete model = new WxMsgTemplete();
    BeanUtils.copyProperties(request, model);
    try {
      wxMsgTempleteMapper.saveOne(model);
    } catch (Exception ex) {
      logger.error("调用saveOne方法失败,错误信息{}", ex);
      throw new ServiceException(ex.getMessage());
    }
    return model.getTempId();
  }

  private WxMsgTempleteResponse buildRes(WxMsgTemplete model) {
    WxMsgTempleteResponse res = null;
    if (null != model) {
      res = new WxMsgTempleteResponse();
      BeanUtils.copyProperties(model, res);
    }
    return res;
  }
}
