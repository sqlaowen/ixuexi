package com.pay.combine.impl.notify;

import java.text.ParseException;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pay.combine.ali.AlipayNotify;
import com.pay.consts.PaymentStateConst;

import api.pay.single.dto.req.CreateAliNotifyReqDto;
import api.pay.single.dto.res.MergePaymentResDto;
import api.pay.single.dto.res.PaymentGatewayResDto;
import api.pay.single.service.AliNotifyService;
import api.pay.single.service.MergePaymentService;
import api.pay.single.service.PaymentGatewayService;

// ali异步通知
public class AliasyncNotifyImpl {

  private static Logger log = LoggerFactory.getLogger(AliasyncNotifyImpl.class);
  
  private static MergePaymentService mergePaymentService;
  public void setMergePaymentService(MergePaymentService mergePaymentService) {
    AliasyncNotifyImpl.mergePaymentService = mergePaymentService;
  }
  
  private static PaymentGatewayService paymentGatewayService;
  public void setPaymentGatewayService(PaymentGatewayService paymentGatewayService) {
    AliasyncNotifyImpl.paymentGatewayService = paymentGatewayService;
  }
  
  private static AliNotifyService aliNotifyService;
  public void setAliNotifyService(AliNotifyService aliNotifyService) {
    AliasyncNotifyImpl.aliNotifyService = aliNotifyService;
  }
  
  public static Boolean asyncNotify(Map<String, String> map) {

    // 如果notify_id为空，可能不是来自支付宝的通知，结束
    if (StringUtils.isBlank(map.get("notify_id"))) {
      log.error("ali异步通知notify_id为空，可能遭篡改...,异步通知为:{}", map);
      return false;
    }
    String mergePaymentId = map.get("out_trade_no");
    MergePaymentResDto mergePaymentResDto= mergePaymentService.findById(mergePaymentId);
    if (null == mergePaymentResDto) {
      log.error("ali异步通知未找到相应合并支付单,对应mergePaymentId:{},通知:{}", mergePaymentId, map);
      return false;
    }
    PaymentGatewayResDto paymentGatewayResDto = paymentGatewayService.findById(mergePaymentResDto.getGatewayId());
    if (null == paymentGatewayResDto) {
      log.error("ali异步通知未找到相应支付网关,对应mergePaymentId:{},通知:{}", mergePaymentId, map);
      return false;
    }
    
    //验签
    //sign_type [PC支付:MD5, 移动支付:RSA]
    if (map.get("sign_type").equalsIgnoreCase("MD5")) {
      if (!AlipayNotify.verify(map, map.get("sign_type"), paymentGatewayResDto.getGatewayKey(), paymentGatewayResDto.getVerifyApi(), paymentGatewayResDto.getGatewayAccount())) {
        log.error("ali异步通知验签不通过,可能遭篡改...,异步通知:{}", map);
        return false;
      }
    } else if (map.get("sign_type").equalsIgnoreCase("RSA")) {
      if (!AlipayNotify.verify(map, map.get("sign_type"), paymentGatewayResDto.getPublicKey(), paymentGatewayResDto.getVerifyApi(), paymentGatewayResDto.getGatewayAccount())) {
        log.error("ali异步通知验签不通过,可能遭篡改...,异步通知:{}", map);
        return false;
      }
    }

    CreateAliNotifyReqDto createAliNotifyReqDto = bulidAliNotifyReqDto(map);
    String tradeStatus=map.get("trade_status").toUpperCase();
    
    // 1.保存异步通知
    log.debug("1.保存异步通知...");
    aliNotifyService.saveOne(createAliNotifyReqDto);

    if ("TRADE_FINISHED".equalsIgnoreCase(tradeStatus)) { // 退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知;
      // 只做消息记录，不做业务处理
      // ...
      return true;
    } else if ("WAIT_BUYER_PAY".equals(tradeStatus)){ //移动支付待支付消息推送
      // 只做消息记录，不做业务处理
      // ...
      return true;
    } else if ("TRADE_SUCCESS".equalsIgnoreCase(tradeStatus)) { // 付款完成后，支付宝系统发送该交易状态通知;
      ZiranNotifyLogicDto ziranNotifyLogicDto=new ZiranNotifyLogicDto();
      ziranNotifyLogicDto.setBankCode(mergePaymentResDto.getBankCode());
      ziranNotifyLogicDto.setGatewayId(mergePaymentResDto.getGatewayId());
      ziranNotifyLogicDto.setMergePaymentId(mergePaymentId);
      ziranNotifyLogicDto.setPaymentStateId(PaymentStateConst.TRADE_SUCCESS);
      ziranNotifyLogicDto.setPaymentType(1);
      ziranNotifyLogicDto.setThirdTradeNo(createAliNotifyReqDto.getTradeNo());
      ziranNotifyLogicDto.setTradeCloseTime(createAliNotifyReqDto.getGmtClose());
      ziranNotifyLogicDto.setTradeTime(createAliNotifyReqDto.getGmtPayment());
      return ZiranNotifyLogic.notifyLogic(ziranNotifyLogicDto);
    } else {
      log.error("ali异步通知trade_status状态异常,对应通知:{}", map);
      return false;
    }
  }
  
  /**
   * 构建CreateAliNotifyReqDto
   * 
   * @param map :Map<String, String>
   * @return CreateAliNotifyReqDto
   */
  private static CreateAliNotifyReqDto bulidAliNotifyReqDto(Map<String, String> map) {

    CreateAliNotifyReqDto dto = new CreateAliNotifyReqDto();

    if (StringUtils.isNotBlank(map.get("out_trade_no"))) {
      dto.setMergePaymentId(map.get("out_trade_no"));
      dto.setOutTradeNo(map.get("out_trade_no"));
    }
    if (StringUtils.isNotBlank(map.get("sign"))) {
      dto.setSign(map.get("sign"));
    }
    if (StringUtils.isNotBlank(map.get("sign_type"))) {
      dto.setSignType(map.get("sign_type"));
    }
    if (StringUtils.isNotBlank(map.get("notify_id"))) {
      dto.setNotifyId(map.get("notify_id"));
    }
    if (StringUtils.isNotBlank(map.get("notify_time"))) {
      try {
        dto.setNotifyTime(DateUtils.parseDate(map.get("notify_time"),new String[] {"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd+HH:mm:ss", "yyyyMMddHHmmss"}));
      } catch (ParseException e) {
        log.error("ali异步通知通知notify_time异常,异步通知信息:{},异常信息:{}", map, e.getMessage());
        e.printStackTrace();
      }
    }
    if (StringUtils.isNotBlank(map.get("notify_type"))) {
      dto.setNotifyType(map.get("notify_type"));
    }
    if (StringUtils.isNotBlank(map.get("subject"))) {
      dto.setSubject(map.get("subject"));
    }
    if (StringUtils.isNotBlank(map.get("payment_type"))) {
      dto.setPaymentType(map.get("payment_type"));
    }
    if (StringUtils.isNotBlank(map.get("trade_no"))) {
      dto.setTradeNo(map.get("trade_no"));
    }
    if (StringUtils.isNotBlank(map.get("trade_status"))) {
      dto.setTradeStatus(map.get("trade_status").toUpperCase());
    }
    if (StringUtils.isNotBlank(map.get("gmt_create"))) {
      try {
        dto.setGmtCreate(DateUtils.parseDate(map.get("gmt_create"),new String[] {"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd+HH:mm:ss", "yyyyMMddHHmmss"}));
      } catch (ParseException e) {
        log.error("ali异步通知通知gmt_create转换异常,异步通知信息:{},异常信息:{}", map, e.getMessage());
        e.printStackTrace();
      }
    }
    if (StringUtils.isNotBlank(map.get("gmt_payment"))) {
      try {
        dto.setGmtPayment(DateUtils.parseDate(map.get("gmt_payment"),new String[] {"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd+HH:mm:ss", "yyyyMMddHHmmss"}));
      } catch (ParseException e) {
        log.error("ali异步通知通知gmt_payment转换异常,异步通知信息:{},异常信息:{}", map, e.getMessage());
        e.printStackTrace();
      }
    }
    if (StringUtils.isNotBlank(map.get("gmt_close"))) {
      try {
        dto.setGmtClose(DateUtils.parseDate(map.get("gmt_close"),new String[] {"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd+HH:mm:ss", "yyyyMMddHHmmss"}));
      } catch (ParseException e) {
        log.error("ali异步通知通知gmt_close转换异常,异步通知信息:{},异常信息:{}", map, e.getMessage());
        e.printStackTrace();
      }
    }
    if (StringUtils.isNotBlank(map.get("refund_status"))) {
      dto.setTradeStatus(map.get("refund_status"));
    }
    if (StringUtils.isNotBlank(map.get("gmt_refund"))) {
      try {
        dto.setGmtRefund(DateUtils.parseDate(map.get("gmt_refund"),new String[] {"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd+HH:mm:ss", "yyyyMMddHHmmss"}));
      } catch (ParseException e) {
        log.error("ali异步通知通知gmt_refund转换异常,异步通知信息:{},异常信息:{}", map, e.getMessage());
        e.printStackTrace();
      }
    }
    if (StringUtils.isNotBlank(map.get("buyer_email"))) {
      dto.setBuyerEmail(map.get("buyer_email"));
    }
    if (StringUtils.isNotBlank(map.get("buyer_id"))) {
      dto.setBuyerId(map.get("buyer_id"));
    }
    if (StringUtils.isNotBlank(map.get("seller_email"))) {
      dto.setSellerEmail(map.get("seller_email"));
    }
    if (StringUtils.isNotBlank(map.get("seller_id"))) {
      dto.setSellerId(map.get("seller_id"));
    }
    if (StringUtils.isNotBlank(map.get("price"))) {
      Integer price = (int) (Double.parseDouble(map.get("price")) * 100);
      dto.setPrice(price);
    }
    if (StringUtils.isNotBlank(map.get("total_fee"))) {
      Integer totalFee = (int) (Double.parseDouble(map.get("total_fee")) * 100);
      dto.setTotalFee(totalFee);
    }
    if (StringUtils.isNotBlank(map.get("quantity"))) {
      dto.setQuantity(Integer.parseInt(map.get("quantity")));
    }
    if (StringUtils.isNotBlank(map.get("body"))) {
      dto.setBody(map.get("body"));
    }
    if (StringUtils.isNotBlank(map.get("discount"))) {
      dto.setDiscount(String.format("%#.2f", Double.parseDouble(map.get("discount"))));
    }
    if (StringUtils.isNotBlank(map.get("is_total_fee_adjust"))) {
      dto.setIsTotalFeeAdjust(map.get("is_total_fee_adjust"));
    }
    if (StringUtils.isNotBlank(map.get("use_coupon"))) {
      dto.setUseCoupon(map.get("use_coupon"));
    }
    if (StringUtils.isNotBlank(map.get("error_code"))) {
      dto.setErrorCode(map.get("error_code"));
    }
    if (StringUtils.isNotBlank(map.get("bank_seq_no"))) {
      dto.setBankSeqNo(map.get("bank_seq_no"));
    }
    if (StringUtils.isNotBlank(map.get("extra_common_param"))) {
      dto.setExtraCommonParam(map.get("extra_common_param"));
    }
    if (StringUtils.isNotBlank(map.get("out_channel_type"))) {
      dto.setOutChannelType(map.get("out_channel_type"));
    }
    if (StringUtils.isNotBlank(map.get("out_channel_amount"))) {
      dto.setOutChannelAmount(map.get("out_channel_amount"));
    }
    if (StringUtils.isNotBlank(map.get("out_channel_inst"))) {
      dto.setOutChannelInst(map.get("out_channel_inst"));
    }

    dto.setNotifyValue(map.toString());
    return dto;
  }
}
