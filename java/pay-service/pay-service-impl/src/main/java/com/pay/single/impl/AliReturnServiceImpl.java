package com.pay.single.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.pay.single.bean.AliReturn;
import com.pay.single.dao.AliReturnMapper;
import com.pay.utils.RandomGUID;

import api.pay.single.dto.req.CreateAliReturnReqDto;
import api.pay.single.service.AliReturnService;
import api.pay.utils.ServiceException;

public class AliReturnServiceImpl implements AliReturnService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	private AliReturnMapper aliReturnMapper;
	public void setAliReturnMapper(AliReturnMapper aliReturnMapper) {
		this.aliReturnMapper = aliReturnMapper;
	}

	@Override
	public String saveOne(CreateAliReturnReqDto dto) throws ServiceException {
		if (null == dto) {
			log.error("请求参数CreateAliReturnReqDto为空...");
			throw new ServiceException("请求参数CreateAliReturnReqDto为空...");
		}

		AliReturn record = new AliReturn();
		BeanUtils.copyProperties(dto, record);
		String id = new RandomGUID().toString();
		record.setAliNotifyId(id);
		record.setCreateTime(new Date());
		aliReturnMapper.saveOne(record);
		return id;
	}

}