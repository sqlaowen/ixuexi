package com.pay.combine.impl.pay;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.pay.combine.ali.AlipaySubmit;
import com.pay.consts.GatewayCodeConst;

import api.pay.combine.dto.PayReqDto;
import api.pay.combine.dto.ResData;
import api.pay.single.dto.res.MergePaymentResDto;
import api.pay.single.dto.res.PaymentGatewayResDto;
import api.pay.single.service.MergePaymentService;
import api.pay.single.service.PaymentGatewayService;
import api.pay.utils.ServiceException;

public class AlipayImpl {

	private static Logger log = LoggerFactory.getLogger(AlipayImpl.class);

	private static PaymentGatewayService paymentGatewayService;
	public void setPaymentGatewayService(PaymentGatewayService paymentGatewayService) {
		AlipayImpl.paymentGatewayService = paymentGatewayService;
	}

	private static MergePaymentService mergePaymentService;
	public void setMergePaymentService(MergePaymentService mergePaymentService) {
		AlipayImpl.mergePaymentService = mergePaymentService;
	}

	/**
	 * ali支付
	 * 
	 * @param dto
	 *            PayReqDto
	 * @return
	 * @throws ServiceException
	 */
	public static ResData<String> aliPay(PayReqDto dto) throws ServiceException {

		ResData<String> resData = new ResData<String>();
		resData.setCode("FAIL");
		String errMsg = "";

		PaymentGatewayResDto gateway = paymentGatewayService.findByCodeAndMerchantId(dto.getMerchantId(), dto.getGatewayCode());
		if (null == gateway) {
			errMsg = String.format("根据merchantId:{}和gatewayCode:{}没有找到相对应的支付网关...", dto.getMerchantId(), dto.getGatewayCode());
			resData.setMsg(errMsg);
			log.error(errMsg);
			return resData;
		}
		if (gateway.getGatewayState() == 1) {
			errMsg = String.format("根据merchantId:{}和gatewayCode:{}找到相对应的支付网关处理禁用状态...", dto.getMerchantId(), dto.getGatewayCode());
			resData.setMsg(errMsg);
			log.error(errMsg);
			return resData;
		}

		//
		resData = ZiranPayLogic.payLogic(dto, gateway);
		if (resData.getCode().equalsIgnoreCase("FAIL")) {
			return resData;
		}
		log.debug("----> merge_payment_id:{}", resData.getT());

		MergePaymentResDto mergePayResDto = mergePaymentService.findById(resData.getT());
		resData = new ResData<String>();// 初始化
		resData.setCode("FAIL");

		// ============请求支付 begin
		String formHtml = null;
		Map<String, String> map = null;
		log.debug("请求ali支付form表单数据:{}", JSON.toJSONString(mergePayResDto));
		
		// ali 即时到账/纯网关 [create_direct_pay_by_user]
		if (dto.getGatewayCode().equalsIgnoreCase(GatewayCodeConst.GATEWAY_ALIDIRECTPAY)) {
			map = createDirectPayByUser(mergePayResDto, gateway);
			formHtml = AlipaySubmit.getRequestForm(map, gateway.getPayApi(), "post", "MD5", gateway.getGatewayKey());
		}
		// ali APP支付[mobile.securitypay.pay]
		else if (dto.getGatewayCode().equalsIgnoreCase(GatewayCodeConst.GATEWAY_ALIMOBILEPAY)) {
			map = mobileSecuritypayPay(mergePayResDto, gateway);
			formHtml = AlipaySubmit.getRequestPrePayStr(map, gateway.getGatewayKey());
		} else {
			errMsg = String.format("请求平台支付参数[支付网关编码]设置不正确,对应请求:%s...", JSON.toJSONString(dto));
			log.error(errMsg);
			resData.setMsg(errMsg);
			return resData;
		}

		// ============请求支付 end

		// 没有异常
		resData.setCode("SUCCESS");
		resData.setT(formHtml);
		return resData;
	}

	/**
	 * ali 即时到账/纯网关 [create_direct_pay_by_user]
	 * 
	 * @param dto
	 * @param gateway
	 * @param bankCode
	 * @return
	 */
	private static Map<String, String> createDirectPayByUser(MergePaymentResDto dto, PaymentGatewayResDto gateway) {
		// 把请求参数打包成数组
		Map<String, String> map = new HashMap<String, String>();
		map.put("service", "create_direct_pay_by_user"); // 接口名
		map.put("partner", gateway.getGatewayAccount()); // partnerId
		map.put("seller_email", gateway.getGatewayUserName()); // 卖家支付宝账号
		map.put("_input_charset", "utf-8"); // 编码方式
		map.put("notify_url", gateway.getNotifyUrl()); // 异步url
		map.put("return_url", gateway.getReturnUrl()); // 同步url
		map.put("out_trade_no", dto.getMergePaymentId()); // 商户唯一支付单id
		map.put("subject", gateway.getMerchantName()); // 商品名
		map.put("payment_type", "1");// 支付类型(1:商品购买)
		map.put("total_fee", String.format("%#.2f", dto.getTotalFee() / 100.0)); // 总金额
		// map.put("extra_common_param", dto.getExtraCommonParam()); //
		// 公用回传参数,此参数不需要传到支付宝,保留在支付单里就可以了
		// 计算过期时间 按照分钟计算 取值范围：1m～15d。
		double mins = (dto.getTimeOut().getTime() - System.currentTimeMillis()) / 1000.0 / 60.0;
		map.put("it_b_pay", (int) Math.ceil(mins) + "m"); // 过期时间

		if (StringUtils.isNotBlank(dto.getBankCode())) {
			map.put("paymethod", "bankPay");
			map.put("defaultbank", dto.getBankCode());
		}
		log.debug("ali即时到账/纯网关 [create_direct_pay_by_user]支付请求参数:{}", map);
		return map;
	}

	/**
	 * ali APP支付[mobile.securitypay.pay]
	 * 
	 * @param dto
	 * @param gateway
	 * @return
	 */
	private static Map<String, String> mobileSecuritypayPay(MergePaymentResDto dto, PaymentGatewayResDto gateway) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("service", "mobile.securitypay.pay");// 接口名
		map.put("partner", gateway.getGatewayAccount());// 合作者身份id
		map.put("_input_charset", "utf-8");// 字符编码
		map.put("notify_url", gateway.getNotifyUrl());
		map.put("out_trade_no", dto.getMergePaymentId());
		map.put("subject", gateway.getMerchantName());
		map.put("payment_type", "1");
		map.put("seller_id", gateway.getGatewayUserName());
		map.put("total_fee", String.format("%#.2f", dto.getTotalFee() / 100.0)); // 总金额
		// 计算过期时间 按照分钟计算 取值范围：1m～15d。
		double mins = (dto.getTimeOut().getTime() - System.currentTimeMillis()) / 1000.0 / 60.0;
		map.put("it_b_pay", (int) Math.ceil(mins) + "m"); // 过期时间
		log.debug("ali移动支付[mobile.securitypay.pay]支付请求参数:{}", map);
		return map;
	}

//	// ali合并支付暂时不使用
//	/**
//	 * ali 合并支付[trade_merge_pay_by_user]
//	 *
//	 * @param dtoList
//	 * @param gateway
//	 * @param bankCode
//	 * @return
//	 */
//	private static Map<String, String> tradeMergePayByUser(List<PaymentResDto> dtoList, PaymentGatewayResDto gateway, String bankCode) {
//		// 把请求参数打包成数组
//		Map<String, String> sParaTemp = new HashMap<String, String>();
//		sParaTemp.put("service", "trade_merge_pay_by_user"); // 接口名
//		sParaTemp.put("partner", gateway.getGatewayAccount()); // partnerId
//		sParaTemp.put("_input_charset", "utf-8"); // 编码方式
//		sParaTemp.put("notify_url", gateway.getNotifyUrl()); // 异步url
//		sParaTemp.put("return_url", gateway.getReturnUrl()); // 同步url
//
//		if (StringUtils.isNotBlank(bankCode)) {
//			sParaTemp.put("paymethod", "motoPay");
//			sParaTemp.put("default_login", "N");
//			sParaTemp.put("defaultbank", bankCode);
//		}
//		sParaTemp.put("merge_trade_info", getMergeTradeInfo(dtoList, gateway));
//		// 合并支付交易信息
//		return sParaTemp;
//	}
//
//	/**
//	 * 合并支付交易信息
//	 *
//	 * @param dtoList
//	 * @param gateway
//	 * @return
//	 */
//	private static String getMergeTradeInfo(List<PaymentResDto> dtoList, PaymentGatewayResDto gateway) {
//
//		StringBuilder sb = new StringBuilder();
//		Map<String, String> para = null;
//		for (PaymentResDto dto : dtoList) {
//			para = new HashMap<String, String>();
//			para.put("partner", gateway.getGatewayAccount());
//			para.put("out_trade_no", dto.getPaymentId());
//			para.put("subject", dto.getSpName());
//			para.put("seller_email", gateway.getGatewayUserName());
//			para.put("total_fee", String.format("%#.2f", dto.getTotalFee() / 100.0));
//			// para.put("extra_common_param", dto.getExtraCommonParam());//回传参数,此参数不需要传到支付宝,保留在支付单里就可以了
//			// 计算过期时间 按照分钟计算 取值范围：1m～15d。
//			if (null != dto.getTimeOut()) {
//				double mins = (dto.getTimeOut().getTime() - System.currentTimeMillis()) / 1000.0 / 60.0;
//				para.put("it_b_pay", (int) Math.ceil(mins) + "m");
//			}
//			// 生成要请求给支付宝的参数数组
//			para = AlipaySubmit.buildRequestPara(para, "MD5", gateway.getGatewayKey());
//
//			if (sb.length() != 0) {
//				sb.append("&-&");
//			}
//			List<String> keys = new ArrayList<String>(para.keySet());
//			for (int i = 0; i < keys.size(); i++) {
//				String name = keys.get(i);
//				String value = para.get(name);
//				if (i == 0)
//					sb.append(name + "=" + value);
//				else {
//					sb.append("&+&" + name + "=" + value);
//				}
//			}
//		}
//		return sb.toString();
//	}

}
