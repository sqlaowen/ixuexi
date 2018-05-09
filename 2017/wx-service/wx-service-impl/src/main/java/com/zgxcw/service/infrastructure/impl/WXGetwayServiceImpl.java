package com.zgxcw.service.infrastructure.impl;

import com.zgxcw.framework.service.ServiceException;
import com.zgxcw.service.infrastructure.bean.WXGetway;
import com.zgxcw.service.infrastructure.dao.WXGetwayMapper;
import com.zgxcw.service.infrastructure.dto.request.wxgetway.CreateWXGetwayRequest;
import com.zgxcw.service.infrastructure.dto.request.wxgetway.UpdateWXGetwayRequest;
import com.zgxcw.service.infrastructure.dto.response.WXGetwayResponse;
import com.zgxcw.service.infrastructure.service.WXGetwayService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

/**
 * Created by huolh on 2016/3/1.
 */
public class WXGetwayServiceImpl implements WXGetwayService {
  private Logger logger = LoggerFactory.getLogger(WXGetwayServiceImpl.class);
  private WXGetwayMapper wxGetwayMapper;

  public void setWxGetwayMapper(WXGetwayMapper wxGetwayMapper) {
    this.wxGetwayMapper = wxGetwayMapper;
  }

  @Override
  public WXGetwayResponse findById(String wxId) throws ServiceException {
    logger.debug("---- WXGetwayServiceImpl.findById start...");
    if(StringUtils.isBlank(wxId)){
      logger.error("请求参数wxId为空");
      throw new ServiceException("请求参数wxId为空");
    }
    WXGetway model = wxGetwayMapper.findById(wxId);
    WXGetwayResponse response = null;
    if(model!=null){
      response = new WXGetwayResponse();
      BeanUtils.copyProperties(model,response);
    }
    logger.debug("---- WXGetwayServiceImpl.findById end...");
    return response;
  }

  @Override
  public String saveOne(CreateWXGetwayRequest request) throws ServiceException {
    logger.debug("---- WXGetwayServiceImpl.saveOne start...");
    if(request == null){
      logger.error("请求参数request为空");
      throw new ServiceException("请求参数request为空");
    }
    WXGetway model = new WXGetway();
    BeanUtils.copyProperties(request,model);
    model.setWxId(UUID.randomUUID().toString().replace("-", ""));
    wxGetwayMapper.saveOne(model);
    logger.debug("---- WXGetwayServiceImpl.saveOne end...");
    return model.getWxId();
  }

  @Override
  public int editById(UpdateWXGetwayRequest request) throws ServiceException {
    logger.debug("---- WXGetwayServiceImpl.editById start...");
    int response=0;
    if(request == null){
      logger.error("请求参数request为空");
      throw new ServiceException("请求参数request为空");
    }
    WXGetway model = new WXGetway();
    BeanUtils.copyProperties(request, model);
    response = wxGetwayMapper.editById(model);
    logger.debug("---- WXGetwayServiceImpl.editById end...");
    return response;
  }
}

