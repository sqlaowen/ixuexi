package com.pay.single.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.pay.single.bean.PaymentOrder;
import com.pay.single.dao.PaymentOrderMapper;
import com.pay.utils.RandomGUID;

import api.pay.single.dto.req.CreatePaymentOrderReqDto;
import api.pay.single.service.PaymentOrderService;
import api.pay.utils.ServiceException;

public class PaymentOrderServiceImpl implements PaymentOrderService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	private PaymentOrderMapper paymentOrderMapper;
	public void setPaymentOrderMapper(PaymentOrderMapper paymentOrderMapper) {
		this.paymentOrderMapper = paymentOrderMapper;
	}

	public String saveOne(CreatePaymentOrderReqDto dto) throws ServiceException {
		if (null == dto) {
			log.error("请求参数CreatePaymentOrderReqDto为空...");
			throw new ServiceException("请求参数CreatePaymentOrderReqDto为空...");
		}
		if (null == dto.getPaymentId() || "".equals(dto.getPaymentId())) {
			log.error("请求参数CreatePaymentOrderReqDto.PaymentId为空...");
			throw new ServiceException("请求参数CreatePaymentOrderReqDto.PaymentId为空...");
		}
		if (null == dto.getOrderId() || "".equals(dto.getOrderId())) {
			log.error("请求参数CreatePaymentOrderReqDto.OrderId为空...");
			throw new ServiceException("请求参数CreatePaymentOrderReqDto.OrderId为空...");
		}
		PaymentOrder record = new PaymentOrder();
		BeanUtils.copyProperties(dto, record);
		String id = new RandomGUID().toString();
		record.setPaymentOrderId(id);
		record.setCreateTime(new Date());
		paymentOrderMapper.saveOne(record);
		return id;
	}
}
