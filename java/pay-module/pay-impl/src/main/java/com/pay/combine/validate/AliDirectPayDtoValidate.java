package com.pay.combine.validate;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.pay.consts.PaymentStateConst;

import api.pay.combine.dto.AliDirectPayDto;
import api.pay.combine.dto.ResData;
import api.pay.single.dto.res.PaymentResDto;

public class AliDirectPayDtoValidate {
  
  private static Logger log=LoggerFactory.getLogger(AliDirectPayDtoValidate.class);
  
  /**
   * 重复请求支付,支付单一致性验证
   * @param aliDirectPayDto
   * @param paymentResDto
   * @return
   */
  public static ResData<PaymentResDto> inParamChangeValidate(AliDirectPayDto aliDirectPayDto,PaymentResDto paymentResDto){
    ResData<PaymentResDto> res=new ResData<PaymentResDto>();
    res.setCode("fail");
    
    String errMsg="";
    //判断支付单的状态
    if(PaymentStateConst.TRADE_SUCCESS==paymentResDto.getPaymentStateId()){
      errMsg=String.format("订单:%s已支付成功...", aliDirectPayDto.getOrderId());
    } else if(PaymentStateConst.TRADE_FAIL==paymentResDto.getPaymentStateId()){
      errMsg=String.format("订单:%s已支付失败...",aliDirectPayDto.getOrderId());
    } else if(PaymentStateConst.TRADE_TIMEOUT==paymentResDto.getPaymentStateId()){
      errMsg=String.format("订单:%s超时未支付...",aliDirectPayDto.getOrderId());
    } else if(PaymentStateConst.TRADE_CLOSE==paymentResDto.getPaymentStateId()){
      errMsg=String.format("订单:%s已关闭...",aliDirectPayDto.getOrderId());
    } else if(PaymentStateConst.TRADE_NOT_EXIST==paymentResDto.getPaymentStateId()){
      errMsg=String.format("订单:%s已关闭(TRADE_NOT_EXIST)...",aliDirectPayDto.getOrderId());
    }
    if(!"".equals(errMsg)){
      log.error(errMsg);
      res.setMsg(errMsg+"\r\n");
      return res;
    }
    //////////////////////////////////////////////////////////////////////////////////////
    List<String> errList=new ArrayList<String>();
    //判断业务参数变化
    if(paymentResDto.getTimeOut().getTime()<System.currentTimeMillis()){
      errMsg=String.format("支付单:%s已过期,过期时间:%s,对应订单:%s",paymentResDto.getPaymentId(),paymentResDto.getTimeOut(),aliDirectPayDto.getOrderId());
      log.error(errMsg);
      errList.add(errMsg);
    }
    if(aliDirectPayDto.getTotalFee()!=paymentResDto.getTotalFee()){
      errMsg=String.format("AliDirectPayDto.TotalFee异常,原TotalFee:%s,新TotalFee:%s,对应订单:%s",paymentResDto.getTotalFee(),aliDirectPayDto.getTotalFee(),aliDirectPayDto.getOrderId());
      log.error(errMsg);
      errList.add(errMsg);
    }
    if(!aliDirectPayDto.getSpName().equals(paymentResDto.getSpName())){
      errMsg=String.format("AliDirectPayDto.SpName异常,原SpName:%s,新SpName:%s,对应订单:%s",paymentResDto.getSpName(),aliDirectPayDto.getSpName(),aliDirectPayDto.getOrderId());
      log.error(errMsg);
      errList.add(errMsg);
    }
    if((null == aliDirectPayDto.getSpDetail() && null != paymentResDto.getSpDetail())
        || (null != aliDirectPayDto.getSpDetail() && null == paymentResDto.getSpDetail())
        || (null != aliDirectPayDto.getSpDetail() && null != paymentResDto.getSpDetail()
            && !aliDirectPayDto.getSpDetail().equals(paymentResDto.getSpDetail()))){
      errMsg=String.format("AliDirectPayDto.SpDetail异常,原SpDetail:%s,新SpDetail:%s,对应订单:%s",paymentResDto.getSpDetail(),aliDirectPayDto.getSpDetail(),aliDirectPayDto.getOrderId());
      log.error(errMsg);
      errList.add(errMsg);
    }
//    if(aliDirectPayDto.getTimeOut()!=paymentResDto.getTimeOut()){
//      errMsg=String.format("AliDirectPayDto.TimeOut异常,原TimeOut:%s,新TimeOut:%s,对应订单:%s",paymentResDto.getTimeOut(),aliDirectPayDto.getTimeOut(),aliDirectPayDto.getOrderId());
//      log.error(errMsg);
//      errList.add(errMsg);
//    }
    if((null == aliDirectPayDto.getReturnUrl() && null != paymentResDto.getReturnUrl())
        || (null != aliDirectPayDto.getReturnUrl() && null == paymentResDto.getReturnUrl())
        || (null != aliDirectPayDto.getReturnUrl() && null != paymentResDto.getReturnUrl()
            && !aliDirectPayDto.getReturnUrl().equals(paymentResDto.getReturnUrl()))){
      errMsg=String.format("AliDirectPayDto.ReturnUrl异常,原ReturnUrl:%s,新ReturnUrl:%s,对应订单:%s",paymentResDto.getReturnUrl(),aliDirectPayDto.getReturnUrl(),aliDirectPayDto.getOrderId());
      log.error(errMsg);
      errList.add(errMsg);
    }
    if((null == aliDirectPayDto.getNotifyUrl() && null != paymentResDto.getNotifyUrl())
        || (null != aliDirectPayDto.getNotifyUrl() && null == paymentResDto.getNotifyUrl())
        || (null != aliDirectPayDto.getNotifyUrl() && null != paymentResDto.getNotifyUrl()
            && !aliDirectPayDto.getNotifyUrl().equals(paymentResDto.getNotifyUrl()))){
      errMsg=String.format("AliDirectPayDto.NotifyUrl异常,原NotifyUrl:%s,新NotifyUrl:%s,对应订单:%s",paymentResDto.getNotifyUrl(),aliDirectPayDto.getNotifyUrl(),aliDirectPayDto.getOrderId());
      log.error(errMsg);
      errList.add(errMsg);
    }
    if ((null == aliDirectPayDto.getExtraCommonParam() && null != paymentResDto.getExtraCommonParam())
        || (null != aliDirectPayDto.getExtraCommonParam() && null == paymentResDto.getExtraCommonParam())
        || (null != aliDirectPayDto.getExtraCommonParam() && null != paymentResDto.getExtraCommonParam()
            && !aliDirectPayDto.getExtraCommonParam().equals(paymentResDto.getExtraCommonParam()))) {
      errMsg =String.format("AliDirectPayDto.ExtraCommonParam异常,原ExtraCommonParam:%s,新ExtraCommonParam:%s,对应订单:%s",paymentResDto.getExtraCommonParam(), aliDirectPayDto.getExtraCommonParam(),aliDirectPayDto.getOrderId());
      log.error(errMsg);
      errList.add(errMsg);
    }
    if(!aliDirectPayDto.getPaymentSource().equals(paymentResDto.getPaymentSource())){
      errMsg=String.format("AliDirectPayDto.PaymentSource异常,原PaymentSource:%s,新ExtraCommonParam:%s,对应订单:%s",paymentResDto.getPaymentSource(),aliDirectPayDto.getPaymentSource(),aliDirectPayDto.getOrderId());
      log.error(errMsg);
      errList.add(errMsg);
    }
    if(errList.size()>0){
      res.setMsg(StringUtils.join(errList, "\r\n"));
      return res;
    }
    
    //未检测到异常
    res.setCode("success");
    res.setT(paymentResDto);
    return res;
  }
  
  /**
   * 验证请求支付输入参数
   * @param dto
   * @return
   */
  public static ResData<String> inParamValidate(AliDirectPayDto dto){
    ResData<String> res=new ResData<String>();
    //
    res.setCode("fail");
    
    String errMsg="";
    if(null==dto){
      errMsg="请求参数AliDirectPayDto为空...";
      log.error(errMsg);
      res.setMsg(errMsg);
      return res;
    }
    //////////////////////////////////////////////////////////////
    List<String> errList=new ArrayList<String>();
    
    if(null==dto.getOrderId()||"".equals(dto.getOrderId().trim())){
      errMsg=String.format("请求参数AliDirectPayDto.OrderId为空,对应请求:%s...",JSON.toJSONString(dto));
      errList.add(errMsg);
      log.error(errMsg);
    }
    if(null==dto.getTotalFee()){
      errMsg=String.format("请求参数AliDirectPayDto.TotalFee为空,对应请求:%s...",JSON.toJSONString(dto));
      errList.add(errMsg);
      log.error(errMsg);
    } else if(dto.getTotalFee()<0){
      errMsg=String.format("请求参数AliDirectPayDto.TotalFee小于0,对应请求:%s...",JSON.toJSONString(dto));
      errList.add(errMsg);
      log.error(errMsg);
    }
    if(null==dto.getSpName()||"".equals(dto.getSpName().trim())){
      errMsg=String.format("请求参数AliDirectPayDto.SpName为空,对应请求:%s...",JSON.toJSONString(dto));
      errList.add(errMsg);
      log.error(errMsg);
    }
    if(null==dto.getTimeOut()){
      errMsg=String.format("请求参数AliDirectPayDto.TimeOut为空,对应请求:%s...",JSON.toJSONString(dto));
      errList.add(errMsg);
      log.error(errMsg);
    } else if(dto.getTimeOut().getTime()<System.currentTimeMillis()){
      errMsg=String.format("请求参数AliDirectPayDto.TimeOut已过期,对应请求:%s...",JSON.toJSONString(dto));
      errList.add(errMsg);
      log.error(errMsg);
    }
    if(null==dto.getPaymentSource()||"".equals(dto.getPaymentSource().trim())){
      errMsg=String.format("请求参数AliDirectPayDto.PaymentSource为空,对应请求:%s...",JSON.toJSONString(dto));
      errList.add(errMsg);
      log.error(errMsg);
    }
    if(errList.size()>0){
      res.setMsg(StringUtils.join(errList, "\r\n"));
      return res;
    }
    //输入参数没有异常
    res.setCode("success");
    return res;
  }
}
