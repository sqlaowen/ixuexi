package com.pay.combine.impl.notify;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pay.consts.PaymentStateConst;

import api.pay.single.dto.req.CreatePaymentLogReqDto;
import api.pay.single.dto.req.CreatePaymentRepeatReqDto;
import api.pay.single.dto.req.mergepayment.EditMergePaymentReqDto;
import api.pay.single.dto.req.payment.EditPaymentReqDto;
import api.pay.single.dto.res.MergePaymentResDto;
import api.pay.single.dto.res.PaymentResDto;
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

  public static Boolean notifyLogic(ZiranNotifyLogicDto dto) throws ServiceException {
    
    MergePaymentResDto mergePaymentResDto= mergePaymentService.findById(dto.getMergePaymentId());
    if (PaymentStateConst.TRADE_CREATE == mergePaymentResDto.getPaymentStateId() // 交易创建
        || PaymentStateConst.TRADE_NOT_EXIST == mergePaymentResDto.getPaymentStateId()) { // 关闭订单(支付单),后有可能再付款,此时应该处理支付单状态
      return logic(dto);
    } else {
      log.info("合并支付单状态:{},合并支付单号:{},非交易创建状态...", mergePaymentResDto.getPaymentStateId(), dto.getMergePaymentId());
    }
    return true;
  }

  //
  private static Boolean logic(ZiranNotifyLogicDto dto) throws ServiceException {
    // 2.更新合并支付单
    log.debug("2.更新合并支付单...");
    EditMergePaymentReqDto editMergePaymentReqDto = new EditMergePaymentReqDto();
    editMergePaymentReqDto.setMergePaymentId(dto.getMergePaymentId());
    editMergePaymentReqDto.setPaymentStateId(dto.getPaymentStateId());
    editMergePaymentReqDto.setThirdTradeNo(dto.getThirdTradeNo());
    if (null != dto.getTradeCloseTime()) {
      editMergePaymentReqDto.setTradeCloseTime(dto.getTradeCloseTime());
    }
    if (null != dto.getTradeTime()) {
      editMergePaymentReqDto.setTradeTime(dto.getTradeTime());
    }
    mergePaymentService.editById(editMergePaymentReqDto);
 
    List<PaymentResDto> paymentResDtoList = paymentService.findByMergePayemntId(dto.getMergePaymentId());
    if (null == paymentResDtoList) {
      log.error("异步通知未找到对应的支付单,对应合并支付单id:{}", dto.getMergePaymentId());
      throw new ServiceException("异步通知未找到对应的支付单,对应合并支付单id:" + dto.getMergePaymentId());
    }
    for (PaymentResDto payment : paymentResDtoList) {
      if (PaymentStateConst.TRADE_CREATE == payment.getPaymentStateId() // 交易创建
          || PaymentStateConst.TRADE_NOT_EXIST == payment.getPaymentStateId()) { // 关闭订单(支付单),后有可能再付款,此时应该处理支付单状态
        
        // 3.更新支付单
        log.debug("3.更新支付单...");
        EditPaymentReqDto editPaymentReqDto = new EditPaymentReqDto();
        editPaymentReqDto.setPaymentId(payment.getPaymentId());
        editPaymentReqDto.setMergePaymentId(dto.getMergePaymentId());
        if (payment.getGatewayId().equalsIgnoreCase(dto.getGatewayId())) {
          editPaymentReqDto.setGatewayId(dto.getGatewayId());
        }
        editPaymentReqDto.setPaymentStateId(dto.getPaymentStateId());
        editPaymentReqDto.setThirdTradeNo(dto.getThirdTradeNo());
        if (null != dto.getTradeCloseTime()) {
          editPaymentReqDto.setTradeCloseTime(dto.getTradeCloseTime());
        }
        if (null != dto.getTradeTime()) {
          editPaymentReqDto.setTradeTime(dto.getTradeTime());
        }
        if (null != dto.getBankCode() && !dto.getBankCode().equalsIgnoreCase(payment.getBankCode())) {
          if (StringUtils.isBlank(dto.getBankCode())) {
            editPaymentReqDto.setBankCode("");
          } else {
            editPaymentReqDto.setBankCode(dto.getBankCode());
          }
        }
        paymentService.editById(editPaymentReqDto);
 
        // 4.记录支付单状态日志
        log.debug("4.记录支付单状态日志...");
        CreatePaymentLogReqDto createPaymentLogReqDto = new CreatePaymentLogReqDto();
        createPaymentLogReqDto.setPaymentId(payment.getPaymentId());
        createPaymentLogReqDto.setPaymentStateId(dto.getPaymentStateId());
        paymentLogService.saveOne(createPaymentLogReqDto);
        
 //        // 5.放入队列
 //        log.debug("5.放入队列...");
      } 
      //5.如果已支付成功 && 第三方流水号不一致,则为重复支付,记录到重复支付记录中
      else if (PaymentStateConst.TRADE_SUCCESS == payment.getPaymentStateId()
          && !payment.getThirdTradeNo().equalsIgnoreCase(dto.getThirdTradeNo())) {
        log.debug("5.已支付成功 && 第三方流水号不一致,则为重复支付,记录到重复支付记录中...");
        CreatePaymentRepeatReqDto createPaymentRepeatReqDto=new CreatePaymentRepeatReqDto();
        createPaymentRepeatReqDto.setGatewayId(dto.getGatewayId());
        createPaymentRepeatReqDto.setMerchantId(payment.getMerchantId());
        createPaymentRepeatReqDto.setMergePaymentId(dto.getMergePaymentId());
        createPaymentRepeatReqDto.setPaymentId(payment.getPaymentId());
        createPaymentRepeatReqDto.setPaymentType(dto.getPaymentType());
        createPaymentRepeatReqDto.setThirdTradeNo(dto.getThirdTradeNo());
        createPaymentRepeatReqDto.setTotalFee(payment.getTotalFee());
        createPaymentRepeatReqDto.setTradeTime(dto.getTradeTime());
        paymentRepeatService.saveOne(createPaymentRepeatReqDto);
      } else {
        log.info(String.format("支付单状态:%s,支付单号:%s,第三方流水号:%s,非交易创建状态...", payment.getPaymentStateId(), payment.getPaymentId(), dto.getThirdTradeNo()));
      }
    }
    return true;
  }
}
