package com.pay.single.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.pay.consts.PaymentStateConst;
import com.pay.single.bean.MergePayment;
import com.pay.single.dao.MergePaymentMapper;
import com.pay.utils.RandomGUID;

import api.pay.single.dto.req.mergepayment.CreateMergePaymentReqDto;
import api.pay.single.dto.req.mergepayment.EditMergePaymentReqDto;
import api.pay.single.dto.res.MergePaymentResDto;
import api.pay.single.service.MergePaymentService;
import api.pay.utils.ServiceException;

public class MergePaymentServiceImpl implements MergePaymentService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	private MergePaymentMapper mergePaymentMapper;
	public void setMergePaymentMapper(MergePaymentMapper mergePaymentMapper) {
		this.mergePaymentMapper = mergePaymentMapper;
	}

	@Override
	public String saveOne(CreateMergePaymentReqDto dto) throws ServiceException {
		// 参数验证
		validCreateMergePaymentReqDto(dto);

		MergePayment record = new MergePayment();
		BeanUtils.copyProperties(dto, record);
		String id = new RandomGUID().toString();
		record.setMergePaymentId(id);
		record.setPaymentStateId(PaymentStateConst.TRADE_CREATE);
		record.setCreateTime(new Date());
		mergePaymentMapper.saveOne(record);
		return id;
	}

	@Override
	public int editById(EditMergePaymentReqDto dto) throws ServiceException {
		if (null == dto) {
			log.error("请求参数EditMergePaymentReqDto为空...");
			throw new ServiceException("请求参数EditMergePaymentReqDto为空...");
		}
		if (StringUtils.isBlank(dto.getMergePaymentId())) {
			log.error("请求参数EditMergePaymentReqDto.mergePaymentId为空...");
			throw new ServiceException("请求参数EditMergePaymentReqDto.mergePaymentId为空...");
		}
		MergePayment record = new MergePayment();
		BeanUtils.copyProperties(dto, record);
		record.setUpdateTime(new Date());
		return mergePaymentMapper.editById(record);
	}

	@Override
	public MergePaymentResDto findById(String mergePaymentId) throws ServiceException {
		if (StringUtils.isBlank(mergePaymentId)) {
			log.error("请求参数mergePaymentId为空...");
			throw new ServiceException("请求参数mergePaymentId为空...");
		}
		return buildRes(mergePaymentMapper.findById(mergePaymentId));
	}

	@Override
	public List<MergePaymentResDto> findByPaymentId(String paymentId) throws ServiceException {
		if (StringUtils.isBlank(paymentId)) {
			log.error("请求参数paymentId为空...");
			throw new ServiceException("请求参数paymentId为空...");
		}
		return buildResList(mergePaymentMapper.findByPaymentId(paymentId));
	}

	@Override
	public List<MergePaymentResDto> findNoPay() throws ServiceException {
		return buildResList(mergePaymentMapper.findNoPay());
	}

	// 参数验证
	private void validCreateMergePaymentReqDto(CreateMergePaymentReqDto dto) throws ServiceException {
		if (null == dto) {
			log.error("请求参数CreateMergePaymentReqDto为空...");
			throw new ServiceException("请求参数CreateMergePaymentReqDto为空...");
		}
		if (StringUtils.isBlank(dto.getMerchantId())) {
			log.error("请求参数CreateMergePaymentReqDto.merchantId为空...");
			throw new ServiceException("请求参数CreateMergePaymentReqDto.merchantId为空...");
		}
		if (StringUtils.isBlank(dto.getGatewayId())) {
			log.error("请求参数CreateMergePaymentReqDto.gatewayId为空...");
			throw new ServiceException("请求参数CreateMergePaymentReqDto.gatewayId为空...");
		}
		if (null == dto.getTotalFee() || dto.getTotalFee() < 0) {
			log.error("请求参数CreateMergePaymentReqDto.totalFee为空或小于0...");
			throw new ServiceException("请求参数CreateMergePaymentReqDto.totalFee为空或小于0...");
		}
	}

	//
	private MergePaymentResDto buildRes(MergePayment record) {
		if (null == record) {
			return null;
		}
		MergePaymentResDto res = new MergePaymentResDto();
		BeanUtils.copyProperties(record, res);
		return res;
	}

	//
	private List<MergePaymentResDto> buildResList(List<MergePayment> recordList) {
		List<MergePaymentResDto> resList = null;
		if (null != recordList) {
			resList = new ArrayList<>();
			MergePaymentResDto res = null;
			for (MergePayment record : recordList) {
				res = buildRes(record);
				if (null != res) {
					resList.add(res);
				}
			}
		}
		return resList;
	}

}
