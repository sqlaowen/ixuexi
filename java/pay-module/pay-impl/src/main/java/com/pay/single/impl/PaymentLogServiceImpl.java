package com.pay.single.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.pay.ex.ServiceException;
import com.pay.single.bean.PaymentLog;
import com.pay.single.dao.PaymentLogMapper;

import api.pay.single.dto.req.CreatePaymentLogReqDto;
import api.pay.single.service.PaymentLogService;

public class PaymentLogServiceImpl implements PaymentLogService {
  
  private Logger log=LoggerFactory.getLogger(this.getClass());
  private PaymentLogMapper paymentLogMapper;
  public void setPaymentLogMapper(PaymentLogMapper paymentLogMapper) {
    this.paymentLogMapper = paymentLogMapper;
  }

  @Override
  public int saveOne(CreatePaymentLogReqDto dto) throws ServiceException {
    if(null==dto){
      log.error("请求参数CreatePaymentLogReqDto为空...");
      throw new ServiceException("请求参数CreatePaymentLogReqDto为空...");
    }
    if(null==dto.getPaymentId()){
      log.error("请求参数CreatePaymentLogReqDto.PaymentId为空...");
      throw new ServiceException("请求参数CreatePaymentLogReqDto.PaymentId为空...");
    }
    if(null==dto.getPaymentStateId()){
      log.error("请求参数CreatePaymentLogReqDto.PaymentStateId为空...");
      throw new ServiceException("请求参数CreatePaymentLogReqDto.PaymentStateId为空...");
    }
    PaymentLog record=new PaymentLog();
    BeanUtils.copyProperties(dto, record);
    record.setCreateTime(new Date());
    return paymentLogMapper.saveOne(record);
  }
}
