package com.pay.combine.impl;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pay.combine.ali.AlipayNotify;
import com.pay.combine.validate.BuildAliNotifyReqDto;
import com.pay.combine.validate.BuildAliReturnReqDto;
import com.pay.consts.PaymentStateConst;

import api.pay.combine.service.PayNotifyService;
import api.pay.single.dto.req.CreateAliNotifyReqDto;
import api.pay.single.dto.req.CreateAliReturnReqDto;
import api.pay.single.dto.req.CreatePaymentLogReqDto;
import api.pay.single.dto.req.payment.EditPaymentReqDto;
import api.pay.single.dto.res.PaymentResDto;
import api.pay.single.service.AliNotifyService;
import api.pay.single.service.AliReturnService;
import api.pay.single.service.PaymentLogService;
import api.pay.single.service.PaymentService;

public class PayNotifyServiceImpl implements PayNotifyService {

  private Logger log=LoggerFactory.getLogger(getClass());
  private AliReturnService aliReturnService;
  public void setAliReturnService(AliReturnService aliReturnService) {
    this.aliReturnService = aliReturnService;
  }
  
  private PaymentService paymentService;
  public void setPaymentService(PaymentService paymentService) {
    this.paymentService = paymentService;
  }
  
  private AliNotifyService aliNotifyService;
  public void setAliNotifyService(AliNotifyService aliNotifyService) {
    this.aliNotifyService = aliNotifyService;
  }
  
  private PaymentLogService paymentLogService;
  public void setPaymentLogService(PaymentLogService paymentLogService) {
    this.paymentLogService = paymentLogService;
  }

  /**
   * ali同步通知
   * @param map Map<String, String>
   * @return String 返回即将跳转到的url
   */
  @Override
  public String aliReturn(Map<String, String> map) {
    log.info("ali同步通知信息:{}",map);
    
    if(null==map){
      log.error("ali同步通知异常,可能是非法请求...");
      return null;
    }
    //保存同步信息
    List<CreateAliReturnReqDto> aliReturnReqDtoList= BuildAliReturnReqDto.bulidAliReturnReqDtoLit(map);
    for(CreateAliReturnReqDto reqDto:aliReturnReqDtoList){
      aliReturnService.saveOne(reqDto);
    }
    //
    String paymentId="";
    if (map.containsKey("merge_trade_result")) {
      String mergeTradeTesult = map.get("merge_trade_result");
      if (StringUtils.isNotBlank(mergeTradeTesult)) {
        Pattern p = Pattern.compile("&\\+&out_trade_no=(\\w+)&\\+&");
        Matcher m = p.matcher(mergeTradeTesult);
        if (m.find(0)) {
          paymentId = m.group(1);
        }
      }
      log.info("ali合并支付同步返回平台流水：" + paymentId);
    } else if (map.containsKey("out_trade_no")) {
      paymentId = map.get("out_trade_no");
      log.info("ali单笔支付同步返回平台流水：" + paymentId);
    }
    if(StringUtils.isBlank(paymentId)){
      log.error("ali同步通知异常,可能是非法请求,请求信息:{}...",map);
      return null;
    }
    //查询支付单
    PaymentResDto payment=paymentService.findById(paymentId);
    if(null==payment){
      log.error("ali同步通知未找到对应的支付单...支付单id:{},同步通知:{}",paymentId, map);
      return null;
    }
    //返回客户端url
    return payment.getReturnUrl();
  }


  /**
   * ali异步通知
   * @param map Map<String, String>
   * 
   * @return boolean true:成功,false:失败
   */
  @Override
  public boolean aliNotify(Map<String, String> map) {
    log.info("ali异步通知信息:{}", map);

    if (null == map) {
      log.error("ali异步通知异常,可能是非法请求...");
      return false;
    }
    // 如果notify_id为空，可能不是来自支付宝的通知，结束
    if (StringUtils.isBlank(map.get("notify_id"))) {
      log.error("ali异步通知notify_id为空，可能遭篡改...,异步通知为:{}", map);
      return false;
    }

    if (AlipayNotify.verify(map)) {// 验签成功

      CreateAliNotifyReqDto createAliNotifyReqDto = BuildAliNotifyReqDto.bulidAliNotifyReqDto(map);
      
      String paymentId = map.get("out_trade_no");
      String tradeStatus=map.get("trade_status").toUpperCase();
      // 1.保存异步通知
      aliNotifyService.saveOne(createAliNotifyReqDto);

      if ("TRADE_FINISHED".equals(tradeStatus)) { // 退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知;
        // 只做消息记录，不做业务处理
        // ...
      } else if ("TRADE_SUCCESS".equals(tradeStatus)) { // 付款完成后，支付宝系统发送该交易状态通知;
        PaymentResDto payment = paymentService.findById(paymentId);
        if (null == payment) {
          log.error("ali异步通知未找到对应的支付单...支付单id:{},异步通知:{}",paymentId, map);
          return false;
        }
        if (PaymentStateConst.TRADE_CREATE == payment.getPaymentStateId() // 交易创建
            || PaymentStateConst.TRADE_NOT_EXIST == payment.getPaymentStateId()) { // 关闭订单(支付单),后有可能再付款,此时应该处理支付单状态
          // 2.更新支付单
          EditPaymentReqDto editPaymentReqDto = new EditPaymentReqDto();
          editPaymentReqDto.setPaymentId(paymentId);
          editPaymentReqDto.setPaymentStateId(PaymentStateConst.TRADE_SUCCESS);
          editPaymentReqDto.setThirdTradeNo(createAliNotifyReqDto.getTradeNo());
          if (null != createAliNotifyReqDto.getGmtClose()) {
            editPaymentReqDto.setTradeCloseTime(createAliNotifyReqDto.getGmtClose());
          }
          if (null != createAliNotifyReqDto.getGmtPayment()) {
            editPaymentReqDto.setTradeTime(createAliNotifyReqDto.getGmtPayment());
          }

          paymentService.editById(editPaymentReqDto);
          // 3.记录支付单状态日志
          CreatePaymentLogReqDto createPaymentLogReqDto = new CreatePaymentLogReqDto();
          createPaymentLogReqDto.setPaymentId(paymentId);
          createPaymentLogReqDto.setPaymentStateId(PaymentStateConst.TRADE_SUCCESS);

          paymentLogService.saveOne(createPaymentLogReqDto);
        }
      }

      return true;
    } else {
      log.error("ali异步通知验签不通过,可能遭篡改...,异步通知:{}", map);
      return false;
    }
  }

}
