package com.pay.single.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.pay.ex.ServiceException;
import com.pay.single.bean.AliReturn;
import com.pay.single.dao.AliReturnMapper;

import api.pay.single.dto.req.CreateAliReturnReqDto;
import api.pay.single.service.AliReturnService;

public class AliReturnServiceImpl implements AliReturnService {

  private Logger log=LoggerFactory.getLogger(this.getClass());
  private AliReturnMapper aliReturnMapper;
  public void setAliReturnMapper(AliReturnMapper aliReturnMapper) {
    this.aliReturnMapper = aliReturnMapper;
  }
  
  @Override
  public int saveOne(CreateAliReturnReqDto dto) throws ServiceException {
    if (null == dto) {
      log.error("请求参数CreateAliReturnReqDto为空...");
      throw new ServiceException("请求参数CreateAliReturnReqDto为空...");
    }
    
    AliReturn record=new AliReturn();
    BeanUtils.copyProperties(dto, record);
    record.setCreateTime(new Date());
    return aliReturnMapper.saveOne(record);
  }

}