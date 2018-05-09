package com.pay.single.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.pay.ex.ServiceException;
import com.pay.single.bean.PaymentClose;
import com.pay.single.dao.PaymentCloseMapper;

import api.pay.single.dto.req.CreatePaymentCloseReqDto;
import api.pay.single.service.PaymentCloseService;

public class PaymentCloseServiceImpl implements PaymentCloseService {

  private Logger log=LoggerFactory.getLogger(this.getClass());
  private PaymentCloseMapper paymentCloseMapper;
  public void setPaymentCloseMapper(PaymentCloseMapper paymentCloseMapper) {
    this.paymentCloseMapper = paymentCloseMapper;
  }

  @Override
  public int saveOne(CreatePaymentCloseReqDto dto) throws ServiceException {
    if (null == dto) {
      log.error("请求参数CreatePaymentCloseReqDto为空...");
      throw new ServiceException("请求参数CreatePaymentCloseReqDto为空...");
    }
//    if (null == dto.getCloseCode() || "".equals(dto.getCloseCode().trim())) {
//      log.error("请求参数CreatePaymentCloseReqDto.CloseCode为空...");
//      return 0;
//    }
    
    PaymentClose record=new PaymentClose();
    BeanUtils.copyProperties(dto, record);
    record.setCloseTime(new Date());
    return paymentCloseMapper.saveOne(record);
  }
  
}