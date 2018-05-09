package com.pay.combine.validate;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.pay.consts.GatewayCodeConst;
import com.pay.consts.PaymentSourceConst;
import com.pay.consts.PaymentStateConst;

import api.pay.combine.dto.PayItemReqDto;
import api.pay.combine.dto.PayReqDto;
import api.pay.combine.dto.ResData;
import api.pay.single.dto.res.PaymentResDto;

public class PayReqDtoValidate {

	private static Logger log = LoggerFactory.getLogger(PayReqDtoValidate.class);

	/**
	 * 验证请求支付输入参数
	 * 
	 * @param dto
	 * @return
	 */
	public static ResData<String> inParamValidate(PayReqDto dto) {
		ResData<String> res = new ResData<String>();
		res.setCode("FAIL");

		String errMsg = "";
		if (null == dto) {
			errMsg = "请求参数PayReqDto为空...";
			log.error(errMsg);
			res.setMsg(errMsg);
			return res;
		}
		//////////////////////////////////////////////////////////////
		String reqJson = JSON.toJSONString(dto);
		List<String> errList = new ArrayList<String>();
		
		//
		if (StringUtils.isBlank(dto.getMerchantId())) {
			errMsg = String.format("请求参数PayReqDto.merchantId为空...");
			errList.add(errMsg);
		}
		if (StringUtils.isBlank(dto.getGatewayCode())) {
			errMsg = String.format("请求参数PayReqDto.gatewayCode为空...");
			errList.add(errMsg);
		} else {
			if (dto.getGatewayCode().equalsIgnoreCase(GatewayCodeConst.GATEWAY_ALIDIRECTPAY)
					|| dto.getGatewayCode().equalsIgnoreCase(GatewayCodeConst.GATEWAY_ALIMOBILEPAY)
					|| dto.getGatewayCode().equalsIgnoreCase(GatewayCodeConst.GATEWAY_WXAPPPAY)
					|| dto.getGatewayCode().equalsIgnoreCase(GatewayCodeConst.GATEWAY_WXJSAPIPAY)
					|| dto.getGatewayCode().equalsIgnoreCase(GatewayCodeConst.GATEWAY_WXNATIVEPAY)) {
				// 支付网关编码正确...
			} else {
				errMsg = String.format("请求参数PayReqDto.gatewayCode不正确,请参照[com.pay.consts.GatewayCodeConst]...");
				errList.add(errMsg);
			}
			// return url
			if (dto.getGatewayCode().equalsIgnoreCase(GatewayCodeConst.GATEWAY_ALIDIRECTPAY)
			// || dto.getGatewayCode().equalsIgnoreCase(GatewayCodeConst.GATEWAY_ALIMERGEPAY)
			) {
				if (StringUtils.isBlank((dto.getReturnUrl()))) {
					errMsg = String.format("请求参数PayReqDto.returnUrl为空...");
					errList.add(errMsg);
				}
			}
		}
		if (StringUtils.isBlank((dto.getNotifyUrl()))) {
			errMsg = String.format("请求参数PayReqDto.notifyUrl为空...");
			errList.add(errMsg);
		}
		if (StringUtils.isBlank(dto.getPaymentSource())) {
			errMsg = String.format("请求参数PayReqDto.paymentSource为空...");
			errList.add(errMsg);
		} else {
			if (dto.getPaymentSource().equalsIgnoreCase(PaymentSourceConst.PAYSOURCE_ORDER)) {
				// 支付单来源正确...
			} else {
				errMsg = String.format("请求参数PayReqDto.paymentSource不正确,请参照[com.pay.consts.PaymentSourceConst]...");
				errList.add(errMsg);
			}
		}
		if (StringUtils.isBlank(dto.getVersion())) {
			errMsg = String.format("请求参数PayReqDto.version为空...");
			errList.add(errMsg);
		} else if (!dto.getVersion().equals("1.0")) {
			errMsg = String.format("请求参数PayReqDto.version应该为 1.0...");
			errList.add(errMsg);
		}
		// openid
		if(dto.getGatewayCode().equalsIgnoreCase(GatewayCodeConst.GATEWAY_WXJSAPIPAY)){
			if(StringUtils.isBlank(dto.getOpenid())){
				errMsg = String.format("请求参数PayReqDto.openid为空...");
				errList.add(errMsg);
			}
		}

		// 支付子项验证
		if (null == dto.getPayItemReqList() || dto.getPayItemReqList().size() == 0) {
			errMsg = "请求参数PayReqDto.payItemReqList为空...";
			errList.add(errMsg);
		} else {
			int i = 0;
			for (PayItemReqDto item : dto.getPayItemReqList()) {
				if (StringUtils.isBlank(item.getOrderNo())) {
					errMsg = String.format("请求参数PayReqDto.payItemReqList[%s].orderNo为空...", i);
					errList.add(errMsg);
				}
				if (null == item.getTotalFee()) {
					errMsg = String.format("请求参数PayReqDto.payItemReqList[%s].totalFee为空...", i);
					errList.add(errMsg);
				} else if (item.getTotalFee() < 0) {
					errMsg = String.format("请求参数PayReqDto.payItemReqList[%s].totalFee小于0...", i);
					errList.add(errMsg);
				}
				if (StringUtils.isBlank((item.getSpName()))) {
					errMsg = String.format("请求参数PayReqDto.payItemReqList[%s].spName为空...", i);
					errList.add(errMsg);
				}
//				if (dto.getGatewayCode().equalsIgnoreCase(GatewayCodeConst.GATEWAY_ALIMOBILEPAY)) {
//					if (StringUtils.isBlank((item.getSpDetail()))) {
//						errMsg = String.format("请求参数PayReqDto.payItemReqList[%s].spDetail为空...", i);
//						errList.add(errMsg);
//					}
//				}
				if (null == item.getTimeOut()) {
					errMsg = String.format("请求参数PayReqDto.payItemReqList[%s].timeOut为空...", i);
					errList.add(errMsg);
				} else if (item.getTimeOut().getTime() < System.currentTimeMillis()) {
					errMsg = String.format("请求参数PayReqDto.payItemReqList[%s].timeOut已过期...", i);
					errList.add(errMsg);
				}
				i++;
			}
		}

		//
		if (errList.size() > 0) {
			String msg = StringUtils.join(errList, "\r\n") + "\r\n对应支付请求:" + reqJson;
			log.error(msg);
			res.setMsg(msg);
			return res;
		}

		// 输入参数没有异常
		res.setCode("SUCCESS");
		return res;
	}

	/**
	 * 重复请求支付,支付单一致性验证
	 * 
	 * @param payItemReqDto
	 * @param payment
	 * @param payReqDto
	 * @param i
	 * @return
	 */
	public static ResData<String> inParamChangeValidate(PayItemReqDto payItemReqDto, PaymentResDto payment, PayReqDto payReqDto, Integer i) {

		ResData<String> res = new ResData<String>();
		res.setCode("FAIL");

		String errMsg = "";
		String orderNo = payItemReqDto.getOrderNo();
		// 判断支付单的状态
		if (PaymentStateConst.TRADE_SUCCESS == payment.getPaymentStateId()) {
			errMsg = String.format("订单号:%s已支付成功...", orderNo);
		} else if (PaymentStateConst.TRADE_FAIL == payment.getPaymentStateId()) {
			errMsg = String.format("订单号:%s已支付失败...", orderNo);
		} else if (PaymentStateConst.TRADE_TIMEOUT == payment.getPaymentStateId()) {
			errMsg = String.format("订单号:%s超时未支付...", orderNo);
		} else if (PaymentStateConst.TRADE_CLOSE == payment.getPaymentStateId()) {
			errMsg = String.format("订单号:%s已关闭...", orderNo);
		} else if (PaymentStateConst.TRADE_NOT_EXIST == payment.getPaymentStateId()) {
			errMsg = String.format("订单号:%s已关闭(TRADE_NOT_EXIST)...", orderNo);
		}
		if (!"".equals(errMsg)) {
			log.error(errMsg);
			res.setMsg(errMsg);
			return res;
		}
		//////////////////////////////////////////////////////////////////////////////////////
		List<String> errList = new ArrayList<String>();
		// 判断业务参数变化
		if (payment.getTimeOut().getTime() < System.currentTimeMillis()) {
			errMsg = String.format("支付单已过期,过期时间:%s,对应订单:%s", payment.getTimeOut(), orderNo);
			errList.add(errMsg);
		}
		if (!StringUtils.equalsIgnoreCase(payment.getMerchantId(), payReqDto.getMerchantId())) {
			errMsg = String.format("payReqDto.merchantId异常,原merchantId:%s,新merchantId:%s", payment.getMerchantId(), payReqDto.getMerchantId());
			errList.add(errMsg);
		}
		// return url
		if (payReqDto.getGatewayCode().equalsIgnoreCase(GatewayCodeConst.GATEWAY_ALIDIRECTPAY)
		// || payReqDto.getGatewayCode().equalsIgnoreCase(GatewayCodeConst.GATEWAY_ALIMERGEPAY)
		) {
			if ((null != payReqDto.getReturnUrl() && null != payment.getReturnUrl())
					&& !StringUtils.equalsIgnoreCase(payReqDto.getReturnUrl(), payment.getReturnUrl())) {
				errMsg = String.format("payReqDto.returnUrl异常,原returnUrl:%s,新returnUrl:%s", payment.getReturnUrl(), payReqDto.getReturnUrl());
				errList.add(errMsg);
			}
		}
		if (!StringUtils.equalsIgnoreCase(payReqDto.getNotifyUrl(),payment.getNotifyUrl())) {
			errMsg = String.format("payReqDto.notifyUrl异常,原notifyUrl:%s,新notifyUrl:%s", payment.getNotifyUrl(), payReqDto.getNotifyUrl());
			errList.add(errMsg);
		}
		if (!StringUtils.equalsIgnoreCase(payReqDto.getPaymentSource(),payment.getPaymentSource())) {
			errMsg = String.format("payReqDto.paymentSource异常,原paymentSource:%s,新paymentSource:%s", payment.getPaymentSource(), payReqDto.getPaymentSource());
			errList.add(errMsg);
		}
		if (payReqDto.getGatewayCode().equalsIgnoreCase(GatewayCodeConst.GATEWAY_WXJSAPIPAY)){
			if ((null != payReqDto.getOpenid() && null != payment.getOpenid())
					&& !StringUtils.equalsIgnoreCase(payReqDto.getOpenid(), payment.getOpenid())) {
				errMsg = String.format("payReqDto.openid异常,原openid:%s,新openid:%s", payment.getOpenid(), payReqDto.getOpenid());
				errList.add(errMsg);
			}
		}
		
		// 子项验证
		if (payItemReqDto.getTotalFee() != payment.getTotalFee()) {
			errMsg = String.format("payReqDto.payItemReqDto[%s].totalFee异常,原totalFee:%s,新totalFee:%s,对应订单:%s", i, payment.getTotalFee(), payItemReqDto.getTotalFee(), orderNo);
			errList.add(errMsg);
		}
		if (!payItemReqDto.getSpName().equalsIgnoreCase(payment.getSpName())) {
			errMsg = String.format("payReqDto.payItemReqDto[%s].spName异常,原spName:%s,新spName:%s,对应订单:%s", i, payment.getSpName(), payItemReqDto.getSpName(), orderNo);
			errList.add(errMsg);
		}

		//
		if (errList.size() > 0) {
			String msg = StringUtils.join(errList, "\r\n") + "\r\n对应支付请求:" + JSON.toJSONString(payReqDto);
			log.error(msg);
			res.setMsg(msg);
			return res;
		}

		// 未检测到异常
		res.setCode("SUCCESS");
		return res;
	}
}
