package com.pay.combine.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.pay.combine.impl.notify.AliasyncNotifyImpl;
import com.pay.combine.impl.notify.AlisyncNotifyImpl;
import com.pay.combine.impl.notify.WXasyncNotifyImpl;
import com.pay.combine.impl.notify.ZiranActiveQueryLogic;
import com.pay.combine.impl.pay.AlipayImpl;
import com.pay.combine.impl.pay.WXPayImpl;
import com.pay.combine.validate.PayReqDtoValidate;
import com.pay.consts.GatewayCodeConst;

import api.pay.combine.dto.PayItemReqDto;
import api.pay.combine.dto.PayReqDto;
import api.pay.combine.dto.ResData;
import api.pay.combine.service.ZiranPayService;
import api.pay.single.dto.req.CreatePaymentReqLogReqDto;
import api.pay.single.dto.res.MergePaymentResDto;
import api.pay.single.service.MergePaymentService;
import api.pay.single.service.PaymentReqLogService;
import api.pay.utils.ServiceException;

public class ZiranPayServiceImpl implements ZiranPayService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	private MergePaymentService mergePaymentService;
	public void setMergePaymentService(MergePaymentService mergePaymentService) {
		this.mergePaymentService = mergePaymentService;
	}

	private ZiranActiveQueryLogic ziranActiveQueryLogicService;
	public void setZiranActiveQueryLogicService(ZiranActiveQueryLogic ziranActiveQueryLogicService) {
		this.ziranActiveQueryLogicService = ziranActiveQueryLogicService;
	}
	
	private PaymentReqLogService paymentReqLogService;
	public void setPaymentReqLogService(PaymentReqLogService paymentReqLogService) {
		this.paymentReqLogService = paymentReqLogService;
	}

	// 支付
	@Override
	public ResData<String> pay(PayReqDto dto) throws ServiceException {

		String reqDto= JSON.toJSONString(dto);
		log.debug("平台支付请求参数:{}", reqDto);
		ResData<String> resData = new ResData<String>();
		resData.setCode("FAIL");
		
		// 验证输入参数有效性
		resData = PayReqDtoValidate.inParamValidate(dto);
		if ("FAIL".equalsIgnoreCase(resData.getCode())) {
			log.info("平台支付请求处理失败,请求参数:{},\r\n失败提示:{}", reqDto, resData.getMsg());
			saveReqLog(dto, resData);
			return resData;
		}

		// ali支付
		if (dto.getGatewayCode().equalsIgnoreCase(GatewayCodeConst.GATEWAY_ALIDIRECTPAY)
				|| dto.getGatewayCode().equalsIgnoreCase(GatewayCodeConst.GATEWAY_ALIMOBILEPAY)) {

			resData = AlipayImpl.aliPay(dto);
		}
		// wx支付方式
		else if (dto.getGatewayCode().equalsIgnoreCase(GatewayCodeConst.GATEWAY_WXAPPPAY)
				|| dto.getGatewayCode().equalsIgnoreCase(GatewayCodeConst.GATEWAY_WXJSAPIPAY)
				|| dto.getGatewayCode().equalsIgnoreCase(GatewayCodeConst.GATEWAY_WXNATIVEPAY)) {

			resData = WXPayImpl.wxPay(dto);
		}

//		if ("FAIL".equalsIgnoreCase(resData.getCode())) {
//			log.info("平台支付请求处理失败, 请求参数:{},\r\n失败提示:{}", JSON.toJSONString(dto), resData.getMsg());
//			saveReqLog(dto, resData);
//			return resData;
//		}
		log.debug("平台支付请求处理成功:{}", JSON.toJSONString(resData));
		saveReqLog(dto, resData);
		return resData;
	}

	// 异步通知
	@Override
	public Boolean asyncNotify(Map<String, String> map) throws ServiceException {
		log.debug("异步通知请求参数:{}", map);
		Boolean rev = false;
		// 1.保存异步通知
		// 2.验签
		// 3.处理支付业务逻辑[更新(合并)支付单状态等,记录支付单状态日志]
		// 4.放入队列(自己消费)
		String ziran_pay_type = map.get("ziran_pay_type");
		map.remove("ziran_pay_type");// 删除自定义参数
		// ali异步通知
		if (ziran_pay_type.equalsIgnoreCase("ali_async_notify")) {
			rev = AliasyncNotifyImpl.asyncNotify(map);
		}
		// 微信异步通知
		else if (ziran_pay_type.equalsIgnoreCase("wx_async_notify")) {
			rev = WXasyncNotifyImpl.asyncNotify(map);
		}
		return rev;
	}

	// 同步通知
	// ali有同步通知, wx没有
	@Override
	public String syncNotify(Map<String, String> map) throws ServiceException {
		log.debug("同步通知请求参数:{}", map);
		// 1.保存同步通知
		// 2.查找支付单中的同步通知地址,并返回
		String ziran_pay_type = map.get("ziran_pay_type");
		map.remove("ziran_pay_type");

		String return_url = null;
		// ali同步通知
		if (ziran_pay_type.equalsIgnoreCase("ali_sync_notify")) {
			return_url = AlisyncNotifyImpl.syncNotify(map);
		}
		return return_url;
	}

	@Override
	public void activeQuery() throws ServiceException {
		List<MergePaymentResDto> mergePaymentResDtoList = mergePaymentService.findNoPay();
		if (null != mergePaymentResDtoList) {
			for (MergePaymentResDto mergePaymentResDto : mergePaymentResDtoList) {
				try {
					ziranActiveQueryLogicService.activeQueryLogic(mergePaymentResDto);
				} catch (Exception e) {
					log.info("主动查询异常,对应合并支付单id:{},\r\n异常信息:{}", mergePaymentResDto.getMergePaymentId(), e.getMessage());
				}
				System.out.println("----------------------------------------------");
			}
		}
	}

	// 记录支付请求日志
	private void saveReqLog(PayReqDto dto, ResData<String> resData){
		try {
			CreatePaymentReqLogReqDto reqLog = new CreatePaymentReqLogReqDto();
			reqLog.setPayReq(JSON.toJSONString(dto));
			String orderIds = "";
			for (PayItemReqDto item : dto.getPayItemReqList()) {
				if ("".equals(orderIds)) {
					orderIds += item.getOrderNo();
				} else {
					orderIds += "," + item.getOrderNo();
				}
			}
			reqLog.setOrderIds(orderIds);
			if ("fail".equalsIgnoreCase(resData.getCode())) {
				reqLog.setLogNote(resData.getMsg());
			} else {
				reqLog.setPayRes(resData.getT());
			}
			paymentReqLogService.saveOne(reqLog);
		} catch (Exception e) {
			log.error(String.format("记录支付请求日志异常:%s \r\n对应请求:%s \r\n请求返回:%s", e.getMessage(), JSON.toJSONString(dto), JSON.toJSONString(resData)));
		}
	}
}
