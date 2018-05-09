package com.pay.single.impl;

import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.pay.single.bean.PaymentRepeat;
import com.pay.single.dao.PaymentRepeatMapper;

import api.pay.single.dto.req.CreatePaymentRepeatReqDto;
import api.pay.single.service.PaymentRepeatService;
import api.pay.utils.ServiceException;

public class PaymentRepeatServiceImpl implements PaymentRepeatService {

  private Logger log = LoggerFactory.getLogger(this.getClass());
  
  private PaymentRepeatMapper paymentRepeatMapper;
  public void setPaymentRepeatMapper(PaymentRepeatMapper paymentRepeatMapper) {
    this.paymentRepeatMapper = paymentRepeatMapper;
  }
  
  @Override
  public String saveOne(CreatePaymentRepeatReqDto dto) throws ServiceException {
    if (null == dto) {
      log.error("请求参数CreatePaymentRepeatReqDto为空...");
      throw new ServiceException("请求参数CreatePaymentRepeatReqDto为空...");
    }
    PaymentRepeat record = new PaymentRepeat();
    BeanUtils.copyProperties(dto, record);
    String id = UUID.randomUUID().toString().replaceAll("-", "");
    record.setReapeatId(id);
    record.setCreateTime(new Date());
    paymentRepeatMapper.saveOne(record);
    return id;
  }

}
