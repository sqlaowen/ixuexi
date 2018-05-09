package com.pay.combine.impl.notify;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.pay.combine.impl.ThirdTradeQuery;
import com.pay.consts.GatewayCodeConst;
import com.pay.consts.PaymentStateConst;

import api.pay.single.dto.res.MergePaymentResDto;
import api.pay.single.dto.res.PaymentGatewayResDto;
import api.pay.single.service.PaymentGatewayService;
import api.pay.utils.ServiceException;

public class ZiranActiveQueryLogic {

	private Logger log = LoggerFactory.getLogger(ZiranActiveQueryLogic.class);

	private PaymentGatewayService paymentGatewayService;
	public void setPaymentGatewayService(PaymentGatewayService paymentGatewayService) {
		this.paymentGatewayService = paymentGatewayService;
	}

	public void activeQueryLogic(MergePaymentResDto mergePaymentResDto) throws ServiceException {

		String errMsg = "";
		PaymentGatewayResDto paymentGatewayResDto = paymentGatewayService.findById(mergePaymentResDto.getGatewayId());
		if (null == paymentGatewayResDto) {
			errMsg = String.format("主动查询未找到支付网关,对应合并支付单id:%s,支付网关id:%s", mergePaymentResDto.getMergePaymentId(), mergePaymentResDto.getGatewayId());
			log.info(errMsg);
			return;
		}

		//存放主动查询结果
		Map<String, String> map = new HashMap<>();

		// 去支付宝查询
		if (GatewayCodeConst.GATEWAY_ALIDIRECTPAY.equalsIgnoreCase(paymentGatewayResDto.getGatewayCode())
				|| GatewayCodeConst.GATEWAY_ALIMOBILEPAY.equalsIgnoreCase(paymentGatewayResDto.getGatewayCode())) {
			map = ThirdTradeQuery.aliTradeQuery(paymentGatewayResDto, mergePaymentResDto.getMergePaymentId());
			aliQueryLogic(map,mergePaymentResDto);
		}
		// 去微信查询
		else if (GatewayCodeConst.GATEWAY_WXNATIVEPAY.equalsIgnoreCase(paymentGatewayResDto.getGatewayCode())
				|| GatewayCodeConst.GATEWAY_WXJSAPIPAY.equalsIgnoreCase(paymentGatewayResDto.getGatewayCode())
				|| GatewayCodeConst.GATEWAY_WXAPPPAY.equalsIgnoreCase(paymentGatewayResDto.getGatewayCode())) {
			map = ThirdTradeQuery.wxTradeQuery(paymentGatewayResDto, mergePaymentResDto.getMergePaymentId());
			wxQueryLogic(map, mergePaymentResDto);
		} else {
			errMsg = String.format("主动查询找到的支付网关异常,对应网关:%s", JSON.toJSONString(paymentGatewayResDto));
			log.info(errMsg);
			throw new ServiceException(errMsg);
		}
	}

	// ali主动查询业务处理
	private void aliQueryLogic(Map<String, String> map,MergePaymentResDto mergePaymentResDto) throws ServiceException {

		String errMsg = "";
		if (!StringUtils.equalsIgnoreCase("T", map.get("is_success"))) {
			errMsg = String.format("ali交易查询提示:%s,对应返回:%s", map.get("error"), map);
			log.info(errMsg);
			return;
		}
		String tradeStatus = map.get("trade_status");
		
		ZiranNotifyLogicDto ziranNotifyLogicDto = new ZiranNotifyLogicDto();
		ziranNotifyLogicDto.setMergePaymentId(mergePaymentResDto.getMergePaymentId());
		if (tradeStatus.equalsIgnoreCase("TRADE_SUCCESS") || tradeStatus.equalsIgnoreCase("TRADE_FINISHED")) {// 交易成功  或  交易成功且结束
			ziranNotifyLogicDto.setPaymentStateId(PaymentStateConst.TRADE_SUCCESS);
			ziranNotifyLogicDto.setComments("ali主动查询处理合并支付单状态为：支付成功[" + tradeStatus + "]");
		} else if (tradeStatus.equalsIgnoreCase("TRADE_CLOSED")) {// 支付失败
			ziranNotifyLogicDto.setPaymentStateId(PaymentStateConst.TRADE_FAIL);
			ziranNotifyLogicDto.setComments("ali主动查询处理合并支付单状态为：支付失败[" + tradeStatus + "]");
		} else {
			log.info(String.format("ali主动查询,支付单状态:%s,业务处理结束(非成功或失败状态), 对应合并支付单id:%s,查询结果:%s", tradeStatus, mergePaymentResDto.getMergePaymentId(), map));
			return;
		}
		ziranNotifyLogicDto.setThirdTradeNo(map.get("out_trade_no"));
		try {
			Date tradeCloseTime = DateUtils.parseDate(map.get("gmt_close"), new String[] { "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd+HH:mm:ss" });
			ziranNotifyLogicDto.setTradeCloseTime(tradeCloseTime);
		} catch (ParseException e) {
			log.error("字符串转日期失败,原字符串为:{}", map.get("gmt_close"));
		}
		try {
			Date tradeTime = DateUtils.parseDate(map.get("gmt_payment"), new String[] { "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd+HH:mm:ss" });
			ziranNotifyLogicDto.setTradeTime(tradeTime);
		} catch (ParseException e) {
			log.error("字符串转日期失败,原字符串为:{}", map.get("gmt_payment"));
		}
		ziranNotifyLogicDto.setGatewayId(mergePaymentResDto.getGatewayId());
		ziranNotifyLogicDto.setBankCode(mergePaymentResDto.getBankCode());
		ziranNotifyLogicDto.setBuyerName(map.get("buyer_email"));
		// 支付宝返回 元
	    Double aliFee = Double.valueOf(map.get("total_fee"));
	    // 总消费（分）
	    Long totalFee = new BigDecimal(aliFee * 100).setScale(2, BigDecimal.ROUND_HALF_UP).longValue();
		ziranNotifyLogicDto.setTotalFee(totalFee);
		ziranNotifyLogicDto.setPaymentSource(mergePaymentResDto.getPaymentSource());
		
		ZiranNotifyLogic.notifyLogic(ziranNotifyLogicDto);
	}

	// wx主动查询业务处理
	private void wxQueryLogic(Map<String, String> map,MergePaymentResDto mergePaymentResDto) throws ServiceException {

		String errMsg = "";
		if (!StringUtils.equalsIgnoreCase("FAIL", map.get("return_code"))) {
			errMsg = String.format("wx交易查询提示:%s,对应合并支付单id:%s, 对应返回:%s", map.get("return_msg"), mergePaymentResDto.getMergePaymentId(),map);
			log.info(errMsg);
			return;
		}
		if (!StringUtils.equalsIgnoreCase("FAIL", map.get("result_code"))) {
			errMsg = String.format("wx交易查询提示码:%s,对应合并支付单id:%s, 提示描述:%s,返回:%s", map.get("err_code"), mergePaymentResDto.getMergePaymentId(), map.get("err_code_des"), map);
			log.info(errMsg);
			return;
		}
		
		String tradeStatus = map.get("trade_state");
		
		ZiranNotifyLogicDto ziranNotifyLogicDto = new ZiranNotifyLogicDto();
		ziranNotifyLogicDto.setMergePaymentId(mergePaymentResDto.getMergePaymentId());
		ziranNotifyLogicDto.setPaymentStateId(PaymentStateConst.TRADE_SUCCESS);
		if (tradeStatus.equalsIgnoreCase("SUCCESS")) {// 支付成功
			ziranNotifyLogicDto.setPaymentStateId(PaymentStateConst.TRADE_SUCCESS);
			ziranNotifyLogicDto.setComments("wx主动查询处理合并支付单状态为：支付成功[" + tradeStatus + "]");
		} else if (tradeStatus.equalsIgnoreCase("REFUND") // 转入退款
				|| tradeStatus.equalsIgnoreCase("CLOSED") // 已关闭
				|| tradeStatus.equalsIgnoreCase("REVOKED") // 已撤销（刷卡支付）
				|| tradeStatus.equalsIgnoreCase("PAYERROR")) { // 支付失败(其他原因，如银行返回失败)
			ziranNotifyLogicDto.setPaymentStateId(PaymentStateConst.TRADE_FAIL);
			ziranNotifyLogicDto.setComments("wx主动查询处理合并支付单状态为：支付失败[" + tradeStatus + "]");
		} else { // tradeStatus.equalsIgnoreCase("NOTPAY") // 未支付并不是支付失败
			log.info(String.format("wx主动查询,合并支付单状态:%s,业务处理结束(非成功或失败状态), 对应合并支付单id:%s,查询结果:%s", tradeStatus, mergePaymentResDto.getMergePaymentId(), map));
			return;
		}
		ziranNotifyLogicDto.setThirdTradeNo(map.get("out_trade_no"));
//		ziranNotifyLogicDto.setTradeCloseTime(null);//微信没有这个交易关闭时间返回
		try {
			Date tradeTime = DateUtils.parseDate(map.get("time_end"), new String[] { "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd+HH:mm:ss" });
			ziranNotifyLogicDto.setTradeTime(tradeTime);
		} catch (ParseException e) {
			log.error("wx异步通知通知time_end异常,异步通知信息:{},异常信息:{}", map, e.getMessage());
		}
		ziranNotifyLogicDto.setGatewayId(mergePaymentResDto.getGatewayId());
		ziranNotifyLogicDto.setBankCode(mergePaymentResDto.getBankCode());
		ziranNotifyLogicDto.setBuyerName(map.get("openid"));
		// 总消费（分）, 微信返回分
	    Long totalFee = Long.valueOf(map.get("total_fee"));
		ziranNotifyLogicDto.setTotalFee(totalFee);
		ziranNotifyLogicDto.setPaymentSource(mergePaymentResDto.getPaymentSource());
		
		ZiranNotifyLogic.notifyLogic(ziranNotifyLogicDto);
	}
}
