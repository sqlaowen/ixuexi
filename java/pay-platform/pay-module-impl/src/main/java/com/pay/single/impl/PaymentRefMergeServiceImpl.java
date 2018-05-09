package com.pay.single.impl;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.pay.single.bean.PaymentRefMerge;
import com.pay.single.dao.PaymentRefMergeMapper;

import api.pay.single.dto.req.CreatePaymentRefMergeReqDto;
import api.pay.single.service.PaymentRefMergeService;
import api.pay.utils.ServiceException;

public class PaymentRefMergeServiceImpl implements PaymentRefMergeService {
  
  private Logger log = LoggerFactory.getLogger(this.getClass());
  
  private PaymentRefMergeMapper paymentRefMergeMapper;
  public void setPaymentRefMergeMapper(PaymentRefMergeMapper paymentRefMergeMapper) {
    this.paymentRefMergeMapper = paymentRefMergeMapper;
  }
  
  @Override
  public String saveOne(CreatePaymentRefMergeReqDto dto) throws ServiceException {
    if (null == dto) {
      log.error("请求参数CreatePaymentRefMergeReqDto为空...");
      throw new ServiceException("请求参数CreatePaymentRefMergeReqDto为空...");
    }
    if (StringUtils.isBlank(dto.getMergePaymentId())) {
      log.error("请求参数CreatePaymentRefMergeReqDto.mergePaymentId为空...");
      throw new ServiceException("请求参数CreatePaymentRefMergeReqDto.mergePaymentId为空...");
    }
    if (StringUtils.isBlank(dto.getPaymentId())) {
      log.error("请求参数CreatePaymentRefMergeReqDto.paymentId为空...");
      throw new ServiceException("请求参数CreatePaymentRefMergeReqDto.paymentId为空...");
    }
    PaymentRefMerge record = new PaymentRefMerge();
    BeanUtils.copyProperties(dto, record);
    String id = UUID.randomUUID().toString().replaceAll("-", "");
    record.setRefId(id);
    record.setCreateTime(new Date());
    paymentRefMergeMapper.saveOne(record);
    return id;
  }

}
