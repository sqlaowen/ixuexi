package com.pay.combine.validate;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.pay.consts.GatewayCodeConst;
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
    res.setCode("fail");

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
      errMsg = String.format("请求参数PayReqDto.merchantId为空,对应请求:%s...", reqJson);
      errList.add(errMsg);
      log.error(errMsg);
    }
    if (StringUtils.isBlank(dto.getGatewayCode())) {
      errMsg = String.format("请求参数PayReqDto.gatewayCode为空,对应请求:%s...", reqJson);
      errList.add(errMsg);
      log.error(errMsg);
    } else {
//      if (dto.getGatewayCode().equalsIgnoreCase(GatewayCodeConst.GATEWAY_ALIDIRECTPAY)
//          //|| dto.getGatewayCode().equalsIgnoreCase(GatewayCodeConst.GATEWAY_ALIMERGEPAY)
//          || dto.getGatewayCode().equalsIgnoreCase(GatewayCodeConst.GATEWAY_ALIMOBILEPAY)
//          || dto.getGatewayCode().equalsIgnoreCase(GatewayCodeConst.GATEWAY_WXAPPPAY)
//          || dto.getGatewayCode().equalsIgnoreCase(GatewayCodeConst.GATEWAY_WXJSAPIPAY)
//          || dto.getGatewayCode().equalsIgnoreCase(GatewayCodeConst.GATEWAY_WXNATIVEPAY)) {
//        // 支付网关编码正确...
//      } else {
//        errMsg = String.format("请求参数PayReqDto.gatewayCode不正确,请参照[com.pay.consts.GatewayCodeConst],对应请求:%s...",reqJson);
//        errList.add(errMsg);
//        log.error(errMsg);
//      }
      //return url
      if (dto.getGatewayCode().equalsIgnoreCase(GatewayCodeConst.GATEWAY_ALIDIRECTPAY)
          //|| dto.getGatewayCode().equalsIgnoreCase(GatewayCodeConst.GATEWAY_ALIMERGEPAY)
          ) {
        if (StringUtils.isBlank((dto.getReturnUrl()))) {
          errMsg = String.format("请求参数PayReqDto.returnUrl为空,对应请求:%s...", reqJson);
          errList.add(errMsg);
          log.error(errMsg);
        }
      }
    }
    if (StringUtils.isBlank((dto.getNotifyUrl()))) {
      errMsg = String.format("请求参数PayReqDto.notifyUrl为空,对应请求:%s...", reqJson);
      errList.add(errMsg);
      log.error(errMsg);
    }
    if (StringUtils.isBlank((dto.getFromIp()))) {
      errMsg = String.format("请求参数PayReqDto.fromIp为空,对应请求:%s...", reqJson);
      errList.add(errMsg);
      log.error(errMsg);
    }
    if (StringUtils.isBlank(dto.getPaymentSource())) {
      errMsg = String.format("请求参数PayReqDto.paymentSource为空,对应请求:%s...", reqJson);
      errList.add(errMsg);
      log.error(errMsg);
    } else {
//      if (dto.getPaymentSource().equalsIgnoreCase(PaymentSourceConst.PAYSOURCE_ORDER)) {
//        // 支付单来源正确...
//      } else {
//        errMsg = String.format("请求参数PayReqDto.paymentSource不正确,请参照[com.pay.consts.PaymentSourceConst],对应请求:%s...",reqJson);
//        errList.add(errMsg);
//        log.error(errMsg);
//      }
    }
    if (StringUtils.isBlank(dto.getVersion())) {
      errMsg = String.format("请求参数PayReqDto.version为空,对应请求:%s...", reqJson);
      errList.add(errMsg);
      log.error(errMsg);
    } else if (!dto.getVersion().equals("1.0")) {
      errMsg = String.format("请求参数PayReqDto.version版本号应该为 1.0,对应请求:%s...", reqJson);
      errList.add(errMsg);
      log.error(errMsg);
    }
    
    //支付子项验证
    if (null == dto.getPayItemReqList() || dto.getPayItemReqList().size() == 0) {
      errMsg = "请求参数PayReqDto.payItemReqList为空...";
      errList.add(errMsg);
      log.error(errMsg);
    } else {
      int i=0;
      for (PayItemReqDto item : dto.getPayItemReqList()) {
        if (StringUtils.isBlank(item.getOrderNo())) {
          errMsg = String.format("请求参数PayReqDto.payItemReqList[%s].orderNo为空,对应请求:%s...", i, reqJson);
          errList.add(errMsg);
          log.error(errMsg);
        }
        if (null == item.getTotalFee()) {
          errMsg = String.format("请求参数PayReqDto.payItemReqList[%s].totalFee为空,对应请求:%s...", i, reqJson);
          errList.add(errMsg);
          log.error(errMsg);
        } else if (item.getTotalFee() < 0) {
          errMsg = String.format("请求参数PayReqDto.payItemReqList[%s].totalFee小于0,对应请求:%s...", i, reqJson);
          errList.add(errMsg);
          log.error(errMsg);
        }
        if (StringUtils.isBlank((item.getSpName()))) {
          errMsg = String.format("请求参数PayReqDto.payItemReqList[%s].spName为空,对应请求:%s...", i, reqJson);
          errList.add(errMsg);
          log.error(errMsg);
        }
//        if (dto.getGatewayCode().equalsIgnoreCase(GatewayCodeConst.GATEWAY_ALIMOBILEPAY)) {
//          if (StringUtils.isBlank((item.getSpDetail()))) {
//            errMsg = String.format("请求参数PayReqDto.payItemReqList[%s].spDetail为空,对应请求:%s...", i, reqJson);
//            errList.add(errMsg);
//            log.error(errMsg);
//          }
//        }
        if (null == item.getTimeOut()) {
          errMsg = String.format("请求参数PayReqDto.payItemReqList[%s].timeOut为空,对应请求:%s...", i, reqJson);
          errList.add(errMsg);
          log.error(errMsg);
        } else if (item.getTimeOut().getTime() < System.currentTimeMillis()) {
          errMsg = String.format("请求参数PayReqDto.payItemReqList[%s].timeOut已过期,对应请求:%s...", i, reqJson);
          errList.add(errMsg);
          log.error(errMsg);
        }
        i++;
      }
    }
    
    //
    if (errList.size() > 0) {
      res.setMsg(StringUtils.join(errList, "\r\n"));
      return res;
    }

    // 输入参数没有异常
    res.setCode("success");
    return res;
  }
    
  /**
   * 重复请求支付,支付单一致性验证
   * @param payItemReqDto
   * @param paymentResDto
   * @param payReqDto
   * @param i
   * @return
   */
  public static ResData<String> inParamChangeValidate(PayItemReqDto payItemReqDto,PaymentResDto paymentResDto,PayReqDto payReqDto,Integer i){
    
    ResData<String> res=new ResData<String>();
    res.setCode("fail");
    
    String errMsg="";
    String orderNo = payItemReqDto.getOrderNo();
    //判断支付单的状态
    if (PaymentStateConst.TRADE_SUCCESS == paymentResDto.getPaymentStateId()) {
      errMsg = String.format("订单号:%s已支付成功...", orderNo);
    } else if (PaymentStateConst.TRADE_FAIL == paymentResDto.getPaymentStateId()) {
      errMsg = String.format("订单号:%s已支付失败...", orderNo);
    } else if (PaymentStateConst.TRADE_TIMEOUT == paymentResDto.getPaymentStateId()) {
      errMsg = String.format("订单号:%s超时未支付...", orderNo);
    } else if (PaymentStateConst.TRADE_CLOSE == paymentResDto.getPaymentStateId()) {
      errMsg = String.format("订单号:%s已关闭...", orderNo);
    } else if (PaymentStateConst.TRADE_NOT_EXIST == paymentResDto.getPaymentStateId()) {
      errMsg = String.format("订单号:%s已关闭(TRADE_NOT_EXIST)...", orderNo);
    }
    if (!"".equals(errMsg)) {
      log.error(errMsg);
      res.setMsg(errMsg);
      return res;
    }
    //////////////////////////////////////////////////////////////////////////////////////
    List<String> errList=new ArrayList<String>();
    //判断业务参数变化
    if (paymentResDto.getTimeOut().getTime() < System.currentTimeMillis()) {
      errMsg=String.format("支付单已过期,过期时间:%s,对应订单:%s",paymentResDto.getTimeOut(),orderNo);
      log.error(errMsg);
      errList.add(errMsg);
    }
    if(!StringUtils.equalsIgnoreCase(paymentResDto.getMerchantId(), payReqDto.getMerchantId())){
      errMsg=String.format("payReqDto.merchantId异常,原merchantId:%s,新merchantId:%s",paymentResDto.getMerchantId(),payReqDto.getMerchantId());
      log.error(errMsg);
      errList.add(errMsg);
    }
    //return url
    if (payReqDto.getGatewayCode().equalsIgnoreCase(GatewayCodeConst.GATEWAY_ALIDIRECTPAY)
        //|| payReqDto.getGatewayCode().equalsIgnoreCase(GatewayCodeConst.GATEWAY_ALIMERGEPAY)
        ) {
      if ((null != payReqDto.getReturnUrl() && null != paymentResDto.getReturnUrl()
          && !payReqDto.getReturnUrl().equalsIgnoreCase(paymentResDto.getReturnUrl()))) {
        errMsg=String.format("payReqDto.returnUrl异常,原returnUrl:%s,新returnUrl:%s",paymentResDto.getReturnUrl(),payReqDto.getReturnUrl());
        log.error(errMsg);
        errList.add(errMsg);
      }
    }
    if(!payReqDto.getNotifyUrl().equalsIgnoreCase(paymentResDto.getNotifyUrl())){
      errMsg=String.format("payReqDto.notifyUrl异常,原notifyUrl:%s,新notifyUrl:%s",paymentResDto.getNotifyUrl(),payReqDto.getNotifyUrl());
      log.error(errMsg);
      errList.add(errMsg);
    }
    if(!payReqDto.getPaymentSource().equalsIgnoreCase(paymentResDto.getPaymentSource())){
      errMsg=String.format("payReqDto.paymentSource异常,原paymentSource:%s,新paymentSource:%s",paymentResDto.getPaymentSource(),payReqDto.getPaymentSource());
      log.error(errMsg);
      errList.add(errMsg);
    }
//    if ((null == payReqDto.getBankCode() && null != paymentResDto.getBankCode())
//        || (null != payReqDto.getBankCode() && null == paymentResDto.getBankCode())
//        || (null != payReqDto.getBankCode() && null != paymentResDto.getBankCode()
//            && !payReqDto.getBankCode().equals(paymentResDto.getBankCode()))) {
//      errMsg =String.format("payReqDto.bankCode异常,原bankCode:%s,新bankCode:%s",paymentResDto.getBankCode(), payReqDto.getBankCode());
//      log.error(errMsg);
//      errList.add(errMsg);
//    }
    
//    if(!payReqDto.getFromIp().equals(paymentResDto.getFromIp())){
//      errMsg=String.format("payReqDto.fromIp异常,原fromIp:%s,新fromIp:%s",paymentResDto.getFromIp(),payReqDto.getFromIp());
//      log.error(errMsg);
//      errList.add(errMsg);
//    }
    
    //子项验证
    if (payItemReqDto.getTotalFee() != paymentResDto.getTotalFee()) {
      errMsg=String.format("payReqDto.payItemReqDto[%s].totalFee异常,原totalFee:%s,新totalFee:%s,对应订单:%s",i,paymentResDto.getTotalFee(),payItemReqDto.getTotalFee(),orderNo);
      log.error(errMsg);
      errList.add(errMsg);
    }
    if (!payItemReqDto.getSpName().equalsIgnoreCase(paymentResDto.getSpName())) {
      errMsg=String.format("payReqDto.payItemReqDto[%s].spName异常,原spName:%s,新spName:%s,对应订单:%s",i,paymentResDto.getSpName(),payItemReqDto.getSpName(),orderNo);
      log.error(errMsg);
      errList.add(errMsg);
    }
//    if (payReqDto.getGatewayCode().equalsIgnoreCase(GatewayCodeConst.GATEWAY_ALIMOBILEPAY)) {
//      if ((null != payItemReqDto.getSpDetail() && null != paymentResDto.getSpDetail()
//          && !payItemReqDto.getSpDetail().equals(paymentResDto.getSpDetail()))) {
//        errMsg=String.format("payReqDto.payItemReqDto[%s].spDetail异常,原spDetail:%s,新spDetail:%s,对应订单:%s",i,paymentResDto.getSpDetail(),payItemReqDto.getSpDetail(),orderNo);
//        log.error(errMsg);
//        errList.add(errMsg);
//      }
//    }
    if ((null == payItemReqDto.getExtraCommonParam() && null != paymentResDto.getExtraCommonParam())
        || (null != payItemReqDto.getExtraCommonParam() && null == paymentResDto.getExtraCommonParam())
        || (null != payItemReqDto.getExtraCommonParam() && null != paymentResDto.getExtraCommonParam()
            && !payItemReqDto.getExtraCommonParam().equals(paymentResDto.getExtraCommonParam()))) {
      errMsg =String.format("payReqDto.payItemReqDto[%s].extraCommonParam异常,原extraCommonParam:%s,新extraCommonParam:%s,对应订单:%s",i,paymentResDto.getExtraCommonParam(), payItemReqDto.getExtraCommonParam(),orderNo);
      log.error(errMsg);
      errList.add(errMsg);
    }
    
    //
    if(errList.size()>0){
      res.setMsg(StringUtils.join(errList, "\r\n"));
      return res;
    }
    
    //未检测到异常
    res.setCode("success");
    return res;
  }
}
