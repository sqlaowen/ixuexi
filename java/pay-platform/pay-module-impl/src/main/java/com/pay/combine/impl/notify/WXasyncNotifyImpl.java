package com.pay.combine.impl.notify;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pay.combine.wx.Signature;
import com.pay.consts.PaymentStateConst;

import api.pay.single.dto.req.CreateWXCouponReqDto;
import api.pay.single.dto.req.CreateWXNotifyReqDto;
import api.pay.single.dto.res.MergePaymentResDto;
import api.pay.single.dto.res.PaymentGatewayResDto;
import api.pay.single.service.MergePaymentService;
import api.pay.single.service.PaymentGatewayService;
import api.pay.single.service.WXNotifyService;
import api.pay.utils.ServiceException;

public class WXasyncNotifyImpl {

  private static Logger log = LoggerFactory.getLogger(WXasyncNotifyImpl.class);
  
  private static MergePaymentService mergePaymentService;
  public void setMergePaymentService(MergePaymentService mergePaymentService) {
    WXasyncNotifyImpl.mergePaymentService = mergePaymentService;
  }
  
  private static PaymentGatewayService paymentGatewayService;
  public void setPaymentGatewayService(PaymentGatewayService paymentGatewayService) {
    WXasyncNotifyImpl.paymentGatewayService = paymentGatewayService;
  }
  
  private static WXNotifyService wxNotifyService;
  public void setWxNotifyService(WXNotifyService wxNotifyService) {
    WXasyncNotifyImpl.wxNotifyService = wxNotifyService;
  }

  public static Boolean asyncNotify(Map<String, String> map) throws ServiceException {
    if(map.get("return_code").equalsIgnoreCase("FAIL")){
      log.error("wx异步通知异常,异常提示:{},对应返回:{}",map.get("return_msg"), map);
      return false;
    }
    
    String wxsign = map.get("sign");
    if (StringUtils.isBlank(wxsign)) {
      log.error("wx异步通知notify_id为空，可能遭篡改...,异步通知为:{}", map);
      return false;
    }
    String mergePaymentId = map.get("out_trade_no");
    MergePaymentResDto mergePaymentResDto= mergePaymentService.findById(mergePaymentId);
    if (null == mergePaymentResDto) {
      log.error("wx异步通知未找到相应合并支付单,对应mergePaymentId:{},通知:{}", mergePaymentId, map);
      return false;
    }
    PaymentGatewayResDto paymentGatewayResDto = paymentGatewayService.findById(mergePaymentResDto.getGatewayId());
    if (null == paymentGatewayResDto) {
      log.error("wx异步通知未找到相应支付网关,对应mergePaymentId:{},通知:{}", mergePaymentId, map);
      return false;
    }
    
    //验签
    map.remove("sign");
    String calcSign = Signature.getSign(map, paymentGatewayResDto.getGatewayKey());
    map.put("sign", wxsign);
    if (!calcSign.equalsIgnoreCase(wxsign)) {
      log.error("wx异步通知验签不通过,可能遭篡改...,异步通知:{}", map);
      return false;
    }
    
    CreateWXNotifyReqDto createWXNotifyReqDto=bulidWXNotifyReqDto(map);
    
    // 1.保存异步通知
    log.debug("1.保存异步通知...");
    wxNotifyService.saveOne(createWXNotifyReqDto);
    
    //构建异步通知逻辑dto
    ZiranNotifyLogicDto ziranNotifyLogicDto=new ZiranNotifyLogicDto();
    ziranNotifyLogicDto.setBankCode(mergePaymentResDto.getBankCode());
    ziranNotifyLogicDto.setGatewayId(mergePaymentResDto.getGatewayId());
    ziranNotifyLogicDto.setMergePaymentId(mergePaymentId);
    if (map.get("result_code").equalsIgnoreCase("SUCCESS")) {
      ziranNotifyLogicDto.setPaymentStateId(PaymentStateConst.TRADE_SUCCESS);
    } else if (map.get("result_code").equalsIgnoreCase("FAIL")) {
      log.error(String.format("wx异步通知-支付失败,对应提示码:%s,提示描述:%s,返回:%s", map.get("err_code"), map.get("err_code_desc"), map));
      ziranNotifyLogicDto.setPaymentStateId(PaymentStateConst.TRADE_FAIL);
    }
    ziranNotifyLogicDto.setPaymentType(2);
    ziranNotifyLogicDto.setThirdTradeNo(createWXNotifyReqDto.getTransactionId());
    // ziranNotifyLogicDto.setTradeCloseTime();//微信没有这个字段返回
    try {
      Date tradeTime = DateUtils.parseDate(map.get("time_end"), new String[] {"yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd+HH:mm:ss"});
      ziranNotifyLogicDto.setTradeTime(tradeTime);
    } catch (ParseException e) {
      log.error("wx异步通知通知time_end异常,异步通知信息:{},异常信息:{}", map, e.getMessage());
      e.printStackTrace();
    }
    return ZiranNotifyLogic.notifyLogic(ziranNotifyLogicDto);
  }

  // 创建微信异步通知对象
  private static CreateWXNotifyReqDto bulidWXNotifyReqDto(Map<String, String> map) {
    CreateWXNotifyReqDto createWXNotifyReqDto = new CreateWXNotifyReqDto();
    if (StringUtils.isNotBlank(map.get("return_code"))) {
      createWXNotifyReqDto.setReturnCode(map.get("return_code"));
    }
    if (StringUtils.isNotBlank(map.get("return_msg"))) {
      createWXNotifyReqDto.setReturnMsg(map.get("return_msg"));
    }
    if (StringUtils.isNotBlank(map.get("appid"))) {
      createWXNotifyReqDto.setAppid(map.get("appid"));
    }
    if (StringUtils.isNotBlank(map.get("mch_id"))) {
      createWXNotifyReqDto.setMchId(map.get("mch_id"));
    }
    if (StringUtils.isNotBlank(map.get("nonce_str"))) {
      createWXNotifyReqDto.setNonceStr(map.get("nonce_str"));
    }
    if (StringUtils.isNotBlank(map.get("sign"))) {
      createWXNotifyReqDto.setSign(map.get("sign"));
    }
    if (StringUtils.isNotBlank(map.get("result_code"))) {
      createWXNotifyReqDto.setResultCode(map.get("result_code"));
    }
    if (StringUtils.isNotBlank(map.get("err_code"))) {
      createWXNotifyReqDto.setErrCode(map.get("err_code"));
    }
    if (StringUtils.isNotBlank(map.get("err_code_des"))) {
      createWXNotifyReqDto.setErrCodeDes(map.get("err_code_des"));
    }
    if (StringUtils.isNotBlank(map.get("device_info"))) {
      createWXNotifyReqDto.setDeviceInfo(map.get("device_info"));
    }
    if (StringUtils.isNotBlank(map.get("openid"))) {
      createWXNotifyReqDto.setOpenid(map.get("openid"));
    }
    if (StringUtils.isNotBlank(map.get("is_subscribe"))) {
      createWXNotifyReqDto.setIsSubscribe(map.get("is_subscribe"));
    }
    if (StringUtils.isNotBlank(map.get("trade_type"))) {
      createWXNotifyReqDto.setTradeType(map.get("trade_type"));
    }
    if (StringUtils.isNotBlank(map.get("bank_type"))) {
      createWXNotifyReqDto.setBankType(map.get("bank_type"));
    }
    if (StringUtils.isNotBlank(map.get("total_fee"))) {
      createWXNotifyReqDto.setTotalFee(Integer.valueOf(map.get("total_fee")));
    }
    if (StringUtils.isNotBlank(map.get("fee_type"))) {
      createWXNotifyReqDto.setFeeType(map.get("fee_type"));
    }
    if (StringUtils.isNotBlank(map.get("cash_fee"))) {
      createWXNotifyReqDto.setCashFee(Integer.valueOf(map.get("cash_fee")));
    }
    if (StringUtils.isNotBlank(map.get("cash_fee_type"))) {
      createWXNotifyReqDto.setCashFeeType(map.get("cash_fee_type"));
    }
    if (StringUtils.isNotBlank(map.get("coupon_fee"))) {
      createWXNotifyReqDto.setCouponFee(Integer.valueOf(map.get("coupon_fee")));
    }
    if (StringUtils.isNotBlank(map.get("coupon_count"))) {
      createWXNotifyReqDto.setCouponCount(Integer.valueOf(map.get("coupon_count")));
      Collection<CreateWXCouponReqDto> couponList = new ArrayList<CreateWXCouponReqDto>();
      CreateWXCouponReqDto coupon = null;
      for (int i = 0; i < createWXNotifyReqDto.getCouponCount(); i++) {
        coupon = new CreateWXCouponReqDto();
        String k1 = "coupon_id_" + i;
        String k2 = "coupon_fee_" + i;
        if (StringUtils.isNotBlank(map.get(k1))) {
          coupon.setCouponId(map.get(k1));
        }
        if (StringUtils.isNotBlank(k2)) {
          coupon.setCouponFee(Integer.valueOf(map.get(k2)));
        }
        couponList.add(coupon);
      }
      createWXNotifyReqDto.setWxCouponList(couponList);
    }
    if (StringUtils.isNotBlank(map.get("transaction_id"))) {
      createWXNotifyReqDto.setTransactionId(map.get("transaction_id"));
    }
    if (StringUtils.isNotBlank(map.get("out_trade_no"))) {
      createWXNotifyReqDto.setOutTradeNo(map.get("out_trade_no"));
      createWXNotifyReqDto.setMergePaymentId(map.get("out_trade_no"));
    }
    if (StringUtils.isNotBlank(map.get("attach"))) {
      createWXNotifyReqDto.setAttach(map.get("attach"));
    }
    if (StringUtils.isNotBlank(map.get("time_end"))) {
      createWXNotifyReqDto.setTimeEnd(map.get("time_end"));
    }
    return createWXNotifyReqDto;
  }
}
