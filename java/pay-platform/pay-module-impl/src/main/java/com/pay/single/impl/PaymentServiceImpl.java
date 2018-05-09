package com.pay.single.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.pay.consts.PaymentStateConst;
import com.pay.single.bean.Payment;
import com.pay.single.dao.PaymentMapper;

import api.pay.single.dto.req.payment.CreatePaymentReqDto;
import api.pay.single.dto.req.payment.EditPaymentReqDto;
import api.pay.single.dto.res.PaymentResDto;
import api.pay.single.service.PaymentService;
import api.pay.utils.ServiceException;

public class PaymentServiceImpl implements PaymentService {

  private Logger log = LoggerFactory.getLogger(this.getClass());
  
  private PaymentMapper paymentMapper;
  public void setPaymentMapper(PaymentMapper paymentMapper) {
    this.paymentMapper = paymentMapper;
  }

  @Override
  public String saveOne(CreatePaymentReqDto dto) throws ServiceException {
    if (null == dto) {
      log.error("请求参数CreatePaymentReqDto为空...");
      throw new ServiceException("请求参数CreatePaymentReqDto为空...");
    }
    Payment record = new Payment();
    BeanUtils.copyProperties(dto, record);
    String id = UUID.randomUUID().toString().replaceAll("-", "");
    record.setPaymentId(id);
    record.setPaymentStateId(PaymentStateConst.TRADE_CREATE);
    record.setCreateTime(new Date());
    paymentMapper.saveOne(record);
    return id;
  }

  @Override
  public PaymentResDto findById(String paymentId) throws ServiceException {
    if (null == paymentId) {
      log.error("请求参数paymentId为空...");
      throw new ServiceException("请求参数paymentId为空...");
    }
    return buildRes(paymentMapper.findById(paymentId));
  }

  @Override
  public int editById(EditPaymentReqDto dto) throws ServiceException {
    if (null == dto) {
      log.error("请求参数EditPaymentReqDto为空...");
      throw new ServiceException("请求参数EditPaymentReqDto为空...");
    }
    if (null == dto.getPaymentId() || "".equals(dto.getPaymentId().trim())) {
      log.error("请求参数EditPaymentReqDto.PaymentId为空...");
      throw new ServiceException("请求参数EditPaymentReqDto.PaymentId为空...");
    }
    Payment record = new Payment();
    BeanUtils.copyProperties(dto, record);
    record.setUpdateTime(new Date());
    return paymentMapper.editById(record);
  }

  // 根据订单id查询支付单
  @Override
  public PaymentResDto findByOrderId(String orderId) throws ServiceException {
    if (null == orderId || "".equals(orderId.trim())) {
      log.error("请求参数orderId为空...");
      throw new ServiceException("请求参数orderId为空...");
    }
    return buildRes(paymentMapper.findByOrderId(orderId));
  }

  //根据订单id或支付单id查询支付单 
  @Override
  public PaymentResDto findByPaymentIdOrderId(String orderId, String paymentId) throws ServiceException {
    if ((null == orderId || "".equals(orderId.trim()))
        && (null == paymentId || "".equals(paymentId.trim()))) {
      log.error("请求参数:orderId和paymentId至少提供一个...");
      throw new ServiceException("请求参数:orderId和paymentId至少提供一个...");
    }
    return buildRes(paymentMapper.findByPaymentIdOrderId(orderId,paymentId));
  }
  
  //根据合并支付单id查询
  @Override
  public List<PaymentResDto> findByMergePayemntId(String mergePaymentId) throws ServiceException {
    if(StringUtils.isBlank(mergePaymentId)){
      log.error("请求参数mergePaymentId为空...");
      throw new ServiceException("请求参数mergePaymentId为空...");
    }
    return buildResList(paymentMapper.findByMergePayemntId(mergePaymentId));
  }
  
  //
  private PaymentResDto buildRes(Payment record) {
    if (null == record) {
      return null;
    }
    PaymentResDto res = new PaymentResDto();
    BeanUtils.copyProperties(record, res);
    return res;
  }
  
  //
  private List<PaymentResDto> buildResList(List<Payment> recordList) {
    List<PaymentResDto> resList = null;
    if (null != recordList) {
      resList = new ArrayList<PaymentResDto>();
      PaymentResDto res = null;
      for (Payment record : recordList) {
        res = buildRes(record);
        if (null != res) {
          resList.add(res);
        }
      }
    }
    return resList;
  }

}
