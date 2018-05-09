package com.pay.single.impl;

import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.pay.single.bean.AliNotify;
import com.pay.single.dao.AliNotifyMapper;

import api.pay.single.dto.req.CreateAliNotifyReqDto;
import api.pay.single.service.AliNotifyService;
import api.pay.utils.ServiceException;

public class AliNotifyServiceImpl implements AliNotifyService {

  private Logger log=LoggerFactory.getLogger(this.getClass());
  private AliNotifyMapper aliNotifyMapper;
  public void setAliNotifyMapper(AliNotifyMapper aliNotifyMapper) {
    this.aliNotifyMapper = aliNotifyMapper;
  }

  @Override
  public String saveOne(CreateAliNotifyReqDto dto) throws ServiceException {
    if (null == dto) {
      log.error("请求参数CreateAliNotifyReqDto为空...");
      throw new ServiceException("请求参数CreateAliNotifyReqDto为空...");
    }
    
    AliNotify record=new AliNotify();
    BeanUtils.copyProperties(dto, record);
    String id=UUID.randomUUID().toString().replaceAll("-", "");
    record.setAliNotifyId(id);
    record.setCreateTime(new Date());
    aliNotifyMapper.saveOne(record);
    return id;
  }

}