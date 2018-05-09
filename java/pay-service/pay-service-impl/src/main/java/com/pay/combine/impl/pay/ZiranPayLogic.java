package com.pay.combine.impl.pay;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.pay.combine.validate.PayReqDtoValidate;
import com.pay.consts.PaymentStateConst;

import api.pay.combine.dto.PayItemReqDto;
import api.pay.combine.dto.PayReqDto;
import api.pay.combine.dto.ResData;
import api.pay.single.dto.req.CreateMergePaymentLogReqDto;
import api.pay.single.dto.req.CreatePaymentLogReqDto;
import api.pay.single.dto.req.CreatePaymentOrderReqDto;
import api.pay.single.dto.req.CreatePaymentRefMergeReqDto;
import api.pay.single.dto.req.mergepayment.CreateMergePaymentReqDto;
import api.pay.single.dto.req.payment.CreatePaymentReqDto;
import api.pay.single.dto.req.payment.EditPaymentReqDto;
import api.pay.single.dto.res.PaymentGatewayResDto;
import api.pay.single.dto.res.PaymentResDto;
import api.pay.single.service.MergePaymentLogService;
import api.pay.single.service.MergePaymentService;
import api.pay.single.service.PaymentLogService;
import api.pay.single.service.PaymentOrderService;
import api.pay.single.service.PaymentRefMergeService;
import api.pay.single.service.PaymentService;

public class ZiranPayLogic {

	private static Logger log = LoggerFactory.getLogger(ZiranPayLogic.class);

	private static PaymentService paymentService;
	public void setPaymentService(PaymentService paymentService) {
		ZiranPayLogic.paymentService = paymentService;
	}

	private static PaymentLogService paymentLogService;
	public void setPaymentLogService(PaymentLogService paymentLogService) {
		ZiranPayLogic.paymentLogService = paymentLogService;
	}

	private static PaymentOrderService paymentOrderService;
	public void setPaymentOrderService(PaymentOrderService paymentOrderService) {
		ZiranPayLogic.paymentOrderService = paymentOrderService;
	}

	private static MergePaymentService mergePaymentService;
	public void setMergePaymentService(MergePaymentService mergePaymentService) {
		ZiranPayLogic.mergePaymentService = mergePaymentService;
	}

	private static PaymentRefMergeService paymentRefMergeService;
	public void setPaymentRefMergeService(PaymentRefMergeService paymentRefMergeService) {
		ZiranPayLogic.paymentRefMergeService = paymentRefMergeService;
	}
	
	private static MergePaymentLogService mergePaymentLogService;
	public void setMergePaymentLogService(MergePaymentLogService mergePaymentLogService) {
		ZiranPayLogic.mergePaymentLogService = mergePaymentLogService;
	}

	/**
	 * 支付业务逻辑
	 * 
	 * @param dto
	 * @param gateway
	 * @return 返回合并支付单id
	 */
	public static ResData<String> payLogic(PayReqDto dto, PaymentGatewayResDto gateway) {

		ResData<String> _res = new ResData<>();
		_res.setCode("FAIL");

		List<PaymentResDto> paymentResDtoList = new ArrayList<PaymentResDto>();
		PaymentResDto payment = null;
		int i = 0;// 确定 重复请求支付,支付单一致性验证 是哪一个子项
		Long totalFee = 0l;// 总金额(分)
		Long timeOut = 4070880000000l;// 超时时间(毫秒) 2099-01-01 00:00:00

		for (PayItemReqDto itemDto : dto.getPayItemReqList()) {
			//
			totalFee += itemDto.getTotalFee();
			if (timeOut > itemDto.getTimeOut().getTime()) {
				timeOut = itemDto.getTimeOut().getTime();
			}
			i++;

			//
			payment = paymentService.findByOrderId(itemDto.getOrderNo());
			if (null != payment) {
				log.debug("订单号:{}已存在,对应支付单:{}", itemDto.getOrderNo(), JSON.toJSONString(payment));
				_res = PayReqDtoValidate.inParamChangeValidate(itemDto, payment, dto, --i);
				if (_res.getCode().equalsIgnoreCase("FAIL")) {
					return _res;
				}

				// 更新网关
				EditPaymentReqDto editPaymentReqDto = new EditPaymentReqDto();
				editPaymentReqDto.setPaymentId(payment.getPaymentId());
				//
				if (!StringUtils.equalsIgnoreCase(payment.getGatewayId(), gateway.getGatewayId())) {
					editPaymentReqDto.setGatewayId(gateway.getGatewayId());
					payment.setGatewayId(gateway.getGatewayId());
				}
				if (!StringUtils.equalsIgnoreCase(payment.getReturnUrl(), dto.getReturnUrl())) {
					editPaymentReqDto.setReturnUrl(dto.getReturnUrl() == null ? "" : dto.getReturnUrl());
					payment.setReturnUrl(dto.getReturnUrl());
				}
				if (!StringUtils.equalsIgnoreCase(payment.getFromIp(), dto.getFromIp())) {
					editPaymentReqDto.setFromIp(dto.getFromIp() == null ? "" : dto.getFromIp());
					payment.setFromIp(dto.getFromIp());
				}
				if (!StringUtils.equalsIgnoreCase(payment.getBankCode(), dto.getBankCode())) {
					editPaymentReqDto.setBankCode(dto.getBankCode() == null ? "" : dto.getBankCode());
					payment.setBankCode(dto.getBankCode());
				}
				if (!StringUtils.equalsIgnoreCase(payment.getOpenid(), dto.getOpenid())) {
					editPaymentReqDto.setOpenid(dto.getOpenid() == null ? "" : dto.getOpenid());
					payment.setOpenid(dto.getOpenid());
				}
				if (!StringUtils.equalsIgnoreCase(payment.getSpDetail(), itemDto.getSpDetail())) {
					editPaymentReqDto.setSpDetail(itemDto.getSpDetail() == null ? "" : itemDto.getSpDetail());
					payment.setSpDetail(itemDto.getSpDetail());
				}
				if (!StringUtils.equalsIgnoreCase(payment.getExtraCommonParam(), itemDto.getExtraCommonParam())) {
					editPaymentReqDto.setExtraCommonParam(itemDto.getExtraCommonParam() == null ? "" : itemDto.getExtraCommonParam());
					payment.setExtraCommonParam(itemDto.getExtraCommonParam());
				}
				paymentService.editById(editPaymentReqDto);

				//
				paymentResDtoList.add(payment);
			} else {
				// 保存支付单
				log.debug("1.保存支付单...");
				CreatePaymentReqDto createPaymentReqDto = new CreatePaymentReqDto();
				createPaymentReqDto.setMerchantId(dto.getMerchantId());
				createPaymentReqDto.setGatewayId(gateway.getGatewayId());
				createPaymentReqDto.setTotalFee(itemDto.getTotalFee());
				createPaymentReqDto.setSpName(itemDto.getSpName());
				createPaymentReqDto.setSpDetail(itemDto.getSpDetail());
				createPaymentReqDto.setTimeOut(itemDto.getTimeOut());
				createPaymentReqDto.setReturnUrl(dto.getReturnUrl());
				createPaymentReqDto.setNotifyUrl(dto.getNotifyUrl());
				createPaymentReqDto.setExtraCommonParam(itemDto.getExtraCommonParam());
				createPaymentReqDto.setFromIp(dto.getFromIp());
				createPaymentReqDto.setPaymentSource(dto.getPaymentSource());
				createPaymentReqDto.setBankCode(dto.getBankCode());
				createPaymentReqDto.setOpenid(dto.getOpenid());
				String paymentId = paymentService.saveOne(createPaymentReqDto);

				// 保存订单ref支付单
				log.debug("2.保存订单ref支付单...");
				CreatePaymentOrderReqDto createPaymentOrderReqDto = new CreatePaymentOrderReqDto();
				createPaymentOrderReqDto.setOrderId(itemDto.getOrderNo());
				createPaymentOrderReqDto.setPaymentId(paymentId);
				paymentOrderService.saveOne(createPaymentOrderReqDto);

				// 保存支付单状态日志
				log.debug("3.保存支付单状态日志...");
				CreatePaymentLogReqDto createPaymentLogReqDto = new CreatePaymentLogReqDto();
				createPaymentLogReqDto.setPaymentId(paymentId);
				createPaymentLogReqDto.setPaymentStateId(PaymentStateConst.TRADE_CREATE);
				paymentLogService.saveOne(createPaymentLogReqDto);

				/////////////////////////////////////////////////////////////////
				// 支付单
				payment = new PaymentResDto();
				payment.setMerchantId(dto.getMerchantId());
				payment.setPaymentStateId(PaymentStateConst.TRADE_CREATE);
				payment.setGatewayId(gateway.getGatewayId());
				payment.setTotalFee(itemDto.getTotalFee());
				payment.setSpName(itemDto.getSpName());
				payment.setSpDetail(itemDto.getSpDetail());
				payment.setTimeOut(itemDto.getTimeOut());
				payment.setReturnUrl(dto.getReturnUrl());
				payment.setNotifyUrl(dto.getNotifyUrl());
				payment.setExtraCommonParam(itemDto.getExtraCommonParam());
				payment.setFromIp(dto.getFromIp());
				payment.setPaymentSource(dto.getPaymentSource());
				payment.setBankCode(dto.getBankCode());
				payment.setOpenid(dto.getOpenid());
				payment.setPaymentId(paymentId);
				
				paymentResDtoList.add(payment);
			}
		}

		// 保存合并支付单
		log.debug("4.保存合并支付单 & 合并支付单状态日志...");
		CreateMergePaymentReqDto createMergePaymentReqDto = new CreateMergePaymentReqDto();
		createMergePaymentReqDto.setMerchantId(dto.getMerchantId());
		createMergePaymentReqDto.setGatewayId(gateway.getGatewayId());
		createMergePaymentReqDto.setTotalFee(totalFee);
		createMergePaymentReqDto.setReturnUrl(dto.getReturnUrl());
		createMergePaymentReqDto.setNotifyUrl(dto.getNotifyUrl());
		createMergePaymentReqDto.setBankCode(dto.getBankCode());
		createMergePaymentReqDto.setTimeOut(new Date(timeOut));
		createMergePaymentReqDto.setPaymentSource(dto.getPaymentSource());
		createMergePaymentReqDto.setOpenid(dto.getOpenid());
		String mergePaymentId = mergePaymentService.saveOne(createMergePaymentReqDto);
		
		CreateMergePaymentLogReqDto createMergePaymentLogReqDto=new CreateMergePaymentLogReqDto();
		createMergePaymentLogReqDto.setMergePaymentId(mergePaymentId);
		createMergePaymentLogReqDto.setPaymentStateId(PaymentStateConst.TRADE_CREATE);
		mergePaymentLogService.saveOne(createMergePaymentLogReqDto);

		// 保存合并支付单ref支付单
		log.debug("5.保存合并支付单ref支付单...");
		CreatePaymentRefMergeReqDto createPaymentRefMergeReqDto = null;
		for (PaymentResDto pdto : paymentResDtoList) {
			createPaymentRefMergeReqDto = new CreatePaymentRefMergeReqDto();
			createPaymentRefMergeReqDto.setMergePaymentId(mergePaymentId);
			createPaymentRefMergeReqDto.setPaymentId(pdto.getPaymentId());
			paymentRefMergeService.saveOne(createPaymentRefMergeReqDto);
		}

		_res.setCode("SUCCESS");
		_res.setT(mergePaymentId);
		return _res;
	}
}
