package com.pay.combine.impl.notify;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.pay.consts.PaymentStateConst;
import com.taobao.metamorphosis.client.extension.spring.MessageBuilder;
import com.taobao.metamorphosis.client.extension.spring.MetaqTemplate;
import com.taobao.metamorphosis.client.producer.SendResult;

import api.pay.combine.dto.MQResDto;
import api.pay.single.dto.req.CreateMergePaymentLogReqDto;
import api.pay.single.dto.req.CreatePaymentLogReqDto;
import api.pay.single.dto.req.CreatePaymentRepeatReqDto;
import api.pay.single.dto.req.mergepayment.EditMergePaymentReqDto;
import api.pay.single.dto.req.payment.EditPaymentReqDto;
import api.pay.single.dto.res.PaymentResDto;
import api.pay.single.service.MergePaymentLogService;
import api.pay.single.service.MergePaymentService;
import api.pay.single.service.PaymentLogService;
import api.pay.single.service.PaymentRepeatService;
import api.pay.single.service.PaymentService;
import api.pay.utils.ServiceException;

public class ZiranNotifyLogic {

	private static Logger log = LoggerFactory.getLogger(ZiranNotifyLogic.class);

	private static MergePaymentService mergePaymentService;
	public void setMergePaymentService(MergePaymentService mergePaymentService) {
		ZiranNotifyLogic.mergePaymentService = mergePaymentService;
	}

	private static PaymentService paymentService;
	public void setPaymentService(PaymentService paymentService) {
		ZiranNotifyLogic.paymentService = paymentService;
	}

	private static PaymentLogService paymentLogService;
	public void setPaymentLogService(PaymentLogService paymentLogService) {
		ZiranNotifyLogic.paymentLogService = paymentLogService;
	}

	private static PaymentRepeatService paymentRepeatService;
	public void setPaymentRepeatService(PaymentRepeatService paymentRepeatService) {
		ZiranNotifyLogic.paymentRepeatService = paymentRepeatService;
	}
	
	private static MergePaymentLogService mergePaymentLogService;
	public void setMergePaymentLogService(MergePaymentLogService mergePaymentLogService) {
		ZiranNotifyLogic.mergePaymentLogService = mergePaymentLogService;
	}
	
	private static MetaqTemplate metaqTemplate;
	public void setMetaqTemplate(MetaqTemplate metaqTemplate) {
		ZiranNotifyLogic.metaqTemplate = metaqTemplate;
	}

	//异步通知逻辑
	public static void notifyLogic(ZiranNotifyLogicDto dto) throws ServiceException {
		
		// 2.更新合并支付单
		log.debug("2.更新合并支付单 & 记录支付单状态日志...");
		EditMergePaymentReqDto editMergePaymentReqDto = new EditMergePaymentReqDto();
		editMergePaymentReqDto.setMergePaymentId(dto.getMergePaymentId());
		editMergePaymentReqDto.setPaymentStateId(dto.getPaymentStateId());
		editMergePaymentReqDto.setThirdTradeNo(dto.getThirdTradeNo());
		editMergePaymentReqDto.setBuyerName(dto.getBuyerName());
		editMergePaymentReqDto.setTradeCloseTime(dto.getTradeCloseTime());
		editMergePaymentReqDto.setTradeTime(dto.getTradeTime());
		mergePaymentService.editById(editMergePaymentReqDto);
		
		//2.记录支付单状态日志
		CreateMergePaymentLogReqDto createMergePaymentLogReqDto=new CreateMergePaymentLogReqDto();
		createMergePaymentLogReqDto.setMergePaymentId(dto.getMergePaymentId());
		createMergePaymentLogReqDto.setPaymentStateId(dto.getPaymentStateId());
		createMergePaymentLogReqDto.setPaymentNote(dto.getComments());
		mergePaymentLogService.saveOne(createMergePaymentLogReqDto);

		List<PaymentResDto> paymentResDtoList =paymentService.findByMergePaymentId(dto.getMergePaymentId());
		if (null == paymentResDtoList || 0 == paymentResDtoList.size()) {
			log.error("异步通知未找到对应的支付单,对应合并支付单id:{}", dto.getMergePaymentId());
			throw new ServiceException("异步通知未找到对应的支付单,对应合并支付单id:" + dto.getMergePaymentId());
		}
		for (PaymentResDto payment : paymentResDtoList) {
			
			String orderId=paymentService.findOrderIdByPaymentId(payment.getPaymentId(), payment.getMerchantId());
			if (PaymentStateConst.TRADE_CREATE == payment.getPaymentStateId()) { // 交易创建

				log.debug("3.更新支付单 & 记录支付单状态日志...");
				// 3.更新支付单
				EditPaymentReqDto editPaymentReqDto = new EditPaymentReqDto();
				editPaymentReqDto.setPaymentId(payment.getPaymentId());
				if (!StringUtils.equalsIgnoreCase(payment.getGatewayId(), dto.getGatewayId())) {
					editPaymentReqDto.setGatewayId(dto.getGatewayId());
				}
				editPaymentReqDto.setPaymentStateId(dto.getPaymentStateId());
				editPaymentReqDto.setThirdTradeNo(dto.getThirdTradeNo());
				editPaymentReqDto.setTradeCloseTime(dto.getTradeCloseTime());
				editPaymentReqDto.setTradeTime(dto.getTradeTime());
				if (!StringUtils.equalsIgnoreCase(dto.getBankCode(), payment.getBankCode())) {
					editPaymentReqDto.setBankCode(dto.getBankCode() == null ? "" : dto.getBankCode());
				}
				editPaymentReqDto.setBuyerName(dto.getBuyerName());
				paymentService.editById(editPaymentReqDto);

				// 3.记录支付单状态日志
				CreatePaymentLogReqDto createPaymentLogReqDto = new CreatePaymentLogReqDto();
				createPaymentLogReqDto.setPaymentId(payment.getPaymentId());
				createPaymentLogReqDto.setPaymentStateId(dto.getPaymentStateId());
				createPaymentLogReqDto.setPaymentNote(dto.getComments());
				paymentLogService.saveOne(createPaymentLogReqDto);

				// 4.放入队列
				log.debug("4.放入队列...");
				MQResDto mq=new MQResDto();
				mq.setMergePaymentId(dto.getMergePaymentId());
				mq.setPaymentStateId(dto.getPaymentStateId());
				mq.setThirdTradeNo(dto.getThirdTradeNo());
				mq.setTradeCloseTime(dto.getTradeCloseTime());
				mq.setTradeTime(dto.getTradeTime());
				mq.setBankCode(dto.getBankCode());
				mq.setBuyerName(dto.getBuyerName());
				mq.setTotalFee(dto.getTotalFee());
				mq.setOrderId(orderId);
				mq.setExtraCommonParam(payment.getExtraCommonParam());
				mq.setMerchantId(payment.getMerchantId());
				
				SendResult sendResult =null;
				try {
					sendResult = metaqTemplate.send(MessageBuilder.withTopic(dto.getPaymentSource()).withBody(mq));
					if(!sendResult.isSuccess()){
						log.error("支付结果发送队列失败, 对应支付结果:{}, 失败提示:{}", JSON.toJSONString(mq), sendResult.getErrorMessage());
					}
				} catch (InterruptedException e) {
					log.error("支付结果发送队列失败, 对应支付结果:{}, 失败提示:{}", JSON.toJSONString(mq), e.getMessage());
				}
			}
			// 5.如果已支付成功 && 第三方流水号不一致,则为重复支付,记录到重复支付记录中
			else if (PaymentStateConst.TRADE_SUCCESS == payment.getPaymentStateId()
					&& !payment.getThirdTradeNo().equalsIgnoreCase(dto.getThirdTradeNo())) {
				log.debug("5.已支付成功 && 第三方流水号不一致,则为重复支付,记录到重复支付记录中...");
				CreatePaymentRepeatReqDto createPaymentRepeatReqDto = new CreatePaymentRepeatReqDto();
				createPaymentRepeatReqDto.setGatewayId(dto.getGatewayId());
				createPaymentRepeatReqDto.setMerchantId(payment.getMerchantId());
				createPaymentRepeatReqDto.setMergePaymentId(dto.getMergePaymentId());
				createPaymentRepeatReqDto.setPaymentId(payment.getPaymentId());
				createPaymentRepeatReqDto.setThirdTradeNo(dto.getThirdTradeNo());
				createPaymentRepeatReqDto.setTotalFee(payment.getTotalFee());
				createPaymentRepeatReqDto.setTradeTime(dto.getTradeTime());
				createPaymentRepeatReqDto.setBuyerName(dto.getBuyerName());
				createPaymentRepeatReqDto.setOrderId(orderId);
				paymentRepeatService.saveOne(createPaymentRepeatReqDto);
			} else {
				log.info(String.format("支付单状态:%s,支付单id:%s, 合并支付单id:%s, 订单id:%s,  第三方流水号:%s,非交易创建状态, 不处理业务...",
						payment.getPaymentStateId(), payment.getPaymentId(), dto.getMergePaymentId(), orderId, dto.getThirdTradeNo()));
			}
		}
	}
}
