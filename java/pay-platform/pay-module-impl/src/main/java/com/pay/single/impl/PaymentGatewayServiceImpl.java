package com.pay.single.impl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.pay.single.bean.PaymentGateway;
import com.pay.single.dao.PaymentGatewayMapper;

import api.pay.single.dto.res.PaymentGatewayResDto;
import api.pay.single.service.PaymentGatewayService;
import api.pay.utils.ServiceException;

public class PaymentGatewayServiceImpl implements PaymentGatewayService {

  private Logger log=LoggerFactory.getLogger(this.getClass());
  
  private PaymentGatewayMapper paymentGatewayMapper;
  public void setPaymentGatewayMapper(PaymentGatewayMapper paymentGatewayMapper) {
    this.paymentGatewayMapper = paymentGatewayMapper;
  }

  @Override
  public PaymentGatewayResDto findById(String gatewayId) throws ServiceException {
    if(StringUtils.isBlank(gatewayId)){
      log.error("请求参数gatewayId为空...");
      throw new ServiceException("请求参数gatewayId为空...");
    }
    return buildRes(paymentGatewayMapper.findById(gatewayId));
  }

  @Override
  public PaymentGatewayResDto findByCodeAndMerchantId(String merchantId, String gatewayCode) throws ServiceException {
    if(StringUtils.isBlank(merchantId)){
      log.error("请求参数merchantId为空...");
      throw new ServiceException("请求参数merchantId为空...");
    }
    if(StringUtils.isBlank(gatewayCode)){
      log.error("请求参数gatewayCode为空...");
      throw new ServiceException("请求参数gatewayCode为空...");
    }
    return buildRes(paymentGatewayMapper.findByCodeAndMerchantId(merchantId, gatewayCode));
  }

  //
  private PaymentGatewayResDto buildRes(PaymentGateway record) {
    if (null == record) {
      return null;
    }
    PaymentGatewayResDto res = new PaymentGatewayResDto();
    BeanUtils.copyProperties(record, res);
    return res;
  }

}
