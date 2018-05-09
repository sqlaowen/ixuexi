package com.pay.combine.validate;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.pay.consts.PaymentStateConst;

import api.pay.combine.dto.AliMergePayDto;
import api.pay.combine.dto.AliMergePayItemDto;
import api.pay.combine.dto.ResData;
import api.pay.single.dto.res.PaymentResDto;

public class AliMergePayDtoValidate {
  
  private static Logger log=LoggerFactory.getLogger(AliMergePayDtoValidate.class);
    
  /**
   * 重复请求支付,支付单一致性验证
   * @param aliPayDto
   * @param paymentResDto
   * @return
   */
  public static ResData<PaymentResDto> inParamChangeValidate(AliMergePayItemDto aliMergePayItemDto
      ,PaymentResDto paymentResDto,String returnUrl,String notifyUrl,String paymentSource){
    ResData<PaymentResDto> res=new ResData<PaymentResDto>();
    res.setCode("fail");
    
    String errMsg="";
    //判断支付单的状态
    if(PaymentStateConst.TRADE_SUCCESS==paymentResDto.getPaymentStateId()){
      errMsg=String.format("订单:%s已支付成功...", aliMergePayItemDto.getOrderId());
    } else if(PaymentStateConst.TRADE_FAIL==paymentResDto.getPaymentStateId()){
      errMsg=String.format("订单:%s已支付失败...",aliMergePayItemDto.getOrderId());
    } else if(PaymentStateConst.TRADE_TIMEOUT==paymentResDto.getPaymentStateId()){
      errMsg=String.format("订单:%s超时未支付...",aliMergePayItemDto.getOrderId());
    } else if(PaymentStateConst.TRADE_CLOSE==paymentResDto.getPaymentStateId()){
      errMsg=String.format("订单:%s已关闭...",aliMergePayItemDto.getOrderId());
    } else if(PaymentStateConst.TRADE_NOT_EXIST==paymentResDto.getPaymentStateId()){
      errMsg=String.format("订单:%s已关闭(TRADE_NOT_EXIST)...",aliMergePayItemDto.getOrderId());
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
      errMsg=String.format("支付单已过期,过期时间:%s,对应订单:%s",paymentResDto.getTimeOut(),aliMergePayItemDto.getOrderId());
      log.error(errMsg);
      errList.add(errMsg);
    }
    if(aliMergePayItemDto.getTotalFee()!=paymentResDto.getTotalFee()){
      errMsg=String.format("AliMergePayDto.AliMergePayItemDto.TotalFee异常,原TotalFee:%s,新TotalFee:%s,对应订单:%s",paymentResDto.getTotalFee(),aliMergePayItemDto.getTotalFee(),aliMergePayItemDto.getOrderId());
      log.error(errMsg);
      errList.add(errMsg);
    }
    if(!aliMergePayItemDto.getSpName().equals(paymentResDto.getSpName())){
      errMsg=String.format("AliMergePayDto.AliMergePayItemDto.SpName异常,原SpName:%s,新SpName:%s,对应订单:%s",paymentResDto.getSpName(),aliMergePayItemDto.getSpName(),aliMergePayItemDto.getOrderId());
      log.error(errMsg);
      errList.add(errMsg);
    }
    if((null == aliMergePayItemDto.getSpDetail() && null != paymentResDto.getSpDetail())
        || (null != aliMergePayItemDto.getSpDetail() && null == paymentResDto.getSpDetail())
        || (null != aliMergePayItemDto.getSpDetail() && null != paymentResDto.getSpDetail()
            && !aliMergePayItemDto.getSpDetail().equals(paymentResDto.getSpDetail()))){
      errMsg=String.format("AliMergePayDto.AliMergePayItemDto.SpDetail异常,原SpDetail:%s,新SpDetail:%s,对应订单:%s",paymentResDto.getSpDetail(),aliMergePayItemDto.getSpDetail(),aliMergePayItemDto.getOrderId());
      log.error(errMsg);
      errList.add(errMsg);
    }
    if ((null == aliMergePayItemDto.getExtraCommonParam() && null != paymentResDto.getExtraCommonParam())
        || (null != aliMergePayItemDto.getExtraCommonParam() && null == paymentResDto.getExtraCommonParam())
        || (null != aliMergePayItemDto.getExtraCommonParam() && null != paymentResDto.getExtraCommonParam()
            && !aliMergePayItemDto.getExtraCommonParam().equals(paymentResDto.getExtraCommonParam()))) {
      errMsg =String.format("AliMergePayDto.AliMergePayItemDto.ExtraCommonParam异常,原ExtraCommonParam:%s,新ExtraCommonParam:%s,对应订单:%s",paymentResDto.getExtraCommonParam(), aliMergePayItemDto.getExtraCommonParam(),aliMergePayItemDto.getOrderId());
      log.error(errMsg);
      errList.add(errMsg);
    }
//    if(aliPayDto.getTimeOut()!=paymentResDto.getTimeOut()){
//      errMsg=String.format("AliPayDto.TimeOut异常,原TimeOut:%s,新TimeOut:%s",paymentResDto.getTimeOut(),aliPayDto.getTimeOut());
//      log.error(errMsg);
//      errList.add(errMsg);
//    }
    if((null == returnUrl && null != paymentResDto.getReturnUrl())
        || (null != returnUrl && null == paymentResDto.getReturnUrl())
        || (null != returnUrl && null != paymentResDto.getReturnUrl()
            && !returnUrl.equals(paymentResDto.getReturnUrl()))){
      errMsg=String.format("AliPayDto.ReturnUrl异常,原ReturnUrl:%s,新ReturnUrl:%s",paymentResDto.getReturnUrl(),returnUrl);
      log.error(errMsg);
      errList.add(errMsg);
    }
    if((null == notifyUrl && null != paymentResDto.getNotifyUrl())
        || (null != notifyUrl && null == paymentResDto.getNotifyUrl())
        || (null != notifyUrl && null != paymentResDto.getNotifyUrl()
            && !notifyUrl.equals(paymentResDto.getNotifyUrl()))){
      errMsg=String.format("AliPayDto.NotifyUrl异常,原NotifyUrl:%s,新NotifyUrl:%s",paymentResDto.getNotifyUrl(),notifyUrl);
      log.error(errMsg);
      errList.add(errMsg);
    }
    if(!paymentSource.equals(paymentResDto.getPaymentSource())){
      errMsg=String.format("AliPayDto.PaymentSource异常,原PaymentSource:%s,新ExtraCommonParam:%s",paymentResDto.getPaymentSource(),paymentSource);
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
  public static ResData<String> inParamValidate(AliMergePayDto dto){
    ResData<String> res=new ResData<String>();
    //
    res.setCode("fail");
    
    String errMsg="";
    if(null==dto){
      errMsg="请求参数AliMergePayDto为空...";
      log.error(errMsg);
      res.setMsg(errMsg);
      return res;
    }
    //////////////////////////////////////////////////////////////
    List<String> errList=new ArrayList<String>();
    if (0 == dto.getItemList().size()) {
      errMsg = "请求参数AliPayDto.ItemList为空...";
      errList.add(errMsg);
      log.error(errMsg);
    } else {
      for (AliMergePayItemDto itemDto : dto.getItemList()) {
        String jsonItem=JSON.toJSONString(itemDto);
        if (null == itemDto.getOrderId() || "".equals(itemDto.getOrderId().trim())) {
          errMsg = String.format("请求参数AliMergePayItemDto.ItemList.OrderId为空,当前子项为:%s...",jsonItem);
          errList.add(errMsg);
          log.error(errMsg);
        }
        if (null == itemDto.getTotalFee()) {
          errMsg = String.format("请求参数AliMergePayItemDto.ItemList.TotalFee为空,当前子项为:%s...",jsonItem);
          errList.add(errMsg);
          log.error(errMsg);
        } else if (itemDto.getTotalFee() < 0) {
          errMsg = String.format("请求参数AliMergePayItemDto.ItemList.TotalFee小于0,当前子项为:%s...",jsonItem);
          errList.add(errMsg);
          log.error(errMsg);
        }
        if (null == itemDto.getSpName() || "".equals(itemDto.getSpName().trim())) {
          errMsg = String.format("请求参数AliMergePayItemDto.ItemList.SpName为空,当前子项为:%s...",jsonItem);
          errList.add(errMsg);
          log.error(errMsg);
        }
        if (null == itemDto.getTimeOut()) {
          errMsg = String.format("请求参数AliMergePayItemDto.ItemList.TimeOut为空,当前子项为:%s...",jsonItem);
          errList.add(errMsg);
          log.error(errMsg);
        } else if (itemDto.getTimeOut().getTime() < System.currentTimeMillis()) {
          errMsg = String.format("请求参数AliMergePayItemDto.ItemList.TimeOut已过期,当前子项为:%s...",jsonItem);
          errList.add(errMsg);
          log.error(errMsg);
        }
      }
    }
    if(null==dto.getPaymentSource()||"".equals(dto.getPaymentSource().trim())){
      errMsg="请求参数AliMergePayDto.PaymentSource为空...";
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
