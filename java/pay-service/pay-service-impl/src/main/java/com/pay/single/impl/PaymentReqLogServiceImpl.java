package com.pay.single.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.pay.single.bean.PaymentReqLog;
import com.pay.single.dao.PaymentReqLogMapper;
import com.pay.utils.RandomGUID;

import api.pay.single.dto.req.CreatePaymentReqLogReqDto;
import api.pay.single.service.PaymentReqLogService;
import api.pay.utils.ServiceException;

public class PaymentReqLogServiceImpl implements PaymentReqLogService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	private PaymentReqLogMapper paymentReqLogMapper;
	public void setPaymentReqLogMapper(PaymentReqLogMapper paymentReqLogMapper) {
		this.paymentReqLogMapper = paymentReqLogMapper;
	}

	@Override
	public String saveOne(CreatePaymentReqLogReqDto createPaymentReqLogReqDto) throws ServiceException {
		if(null == createPaymentReqLogReqDto){
			log.error("请求参数createPaymentReqLogReqDto为空...");
			throw new ServiceException("请求参数createPaymentReqLogReqDto为空...");
		}
		
		PaymentReqLog record=new PaymentReqLog();
		BeanUtils.copyProperties(createPaymentReqLogReqDto, record);
		String id=new RandomGUID().toString();
		record.setLogId(id);
		record.setCreateTime(new Date());
		paymentReqLogMapper.saveOne(record);
		return id;
	}

}
