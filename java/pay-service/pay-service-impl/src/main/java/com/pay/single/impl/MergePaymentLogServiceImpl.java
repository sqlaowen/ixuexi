package com.pay.single.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.pay.single.bean.MergePaymentLog;
import com.pay.single.dao.MergePaymentLogMapper;
import com.pay.utils.RandomGUID;

import api.pay.single.dto.req.CreateMergePaymentLogReqDto;
import api.pay.single.service.MergePaymentLogService;
import api.pay.utils.ServiceException;

public class MergePaymentLogServiceImpl implements MergePaymentLogService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	private MergePaymentLogMapper mergePaymentLogMapper;
	public void setMergePaymentLogMapper(MergePaymentLogMapper mergePaymentLogMapper) {
		this.mergePaymentLogMapper = mergePaymentLogMapper;
	}

	@Override
	public String saveOne(CreateMergePaymentLogReqDto dto) throws ServiceException {
		if (null == dto) {
			log.error("请求参数CreatePaymentLogReqDto为空...");
			throw new ServiceException("请求参数CreatePaymentLogReqDto为空...");
		}
		if (null == dto.getMergePaymentId()) {
			log.error("请求参数CreatePaymentLogReqDto.MergePaymentId为空...");
			throw new ServiceException("请求参数CreatePaymentLogReqDto.MergePaymentId为空...");
		}
		if (null == dto.getPaymentStateId()) {
			log.error("请求参数CreatePaymentLogReqDto.PaymentStateId为空...");
			throw new ServiceException("请求参数CreatePaymentLogReqDto.PaymentStateId为空...");
		}
		MergePaymentLog record = new MergePaymentLog();
		BeanUtils.copyProperties(dto, record);
		String id = new RandomGUID().toString();
		record.setLogId(id);
		record.setCreateTime(new Date());
		mergePaymentLogMapper.saveOne(record);
		return id;
	}

}
