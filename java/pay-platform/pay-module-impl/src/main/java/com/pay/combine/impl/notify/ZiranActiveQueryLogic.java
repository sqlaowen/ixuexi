package com.pay.combine.impl.notify;

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
    
    String errMsg="";
    PaymentGatewayResDto paymentGatewayResDto = paymentGatewayService.findById(mergePaymentResDto.getGatewayId());
    if (null == paymentGatewayResDto) {
      errMsg=String.format("主动查询未找到支付网关,对应合并支付单id:%s,支付网关id:%s", mergePaymentResDto.getMergePaymentId(),mergePaymentResDto.getGatewayId());
      log.info(errMsg);
      throw new ServiceException(errMsg);
    }
    
    Map<String, String> map=new HashMap<>();
    ZiranNotifyLogicDto logicDto=null;    
    
    //去支付宝查询
    if (GatewayCodeConst.GATEWAY_ALIDIRECTPAY.equalsIgnoreCase(paymentGatewayResDto.getGatewayCode())
        || GatewayCodeConst.GATEWAY_ALIMOBILEPAY.equalsIgnoreCase(paymentGatewayResDto.getGatewayCode())) {
      map = ThirdTradeQuery.aliTradeQuery(paymentGatewayResDto, mergePaymentResDto.getMergePaymentId());
      logicDto=buildAliLogicDto(map);
    }
    //去微信查询
    else if (GatewayCodeConst.GATEWAY_WXNATIVEPAY.equalsIgnoreCase(paymentGatewayResDto.getGatewayCode())
        || GatewayCodeConst.GATEWAY_WXJSAPIPAY.equalsIgnoreCase(paymentGatewayResDto.getGatewayCode())
        || GatewayCodeConst.GATEWAY_WXAPPPAY.equalsIgnoreCase(paymentGatewayResDto.getGatewayCode())) {
      map = ThirdTradeQuery.wxTradeQuery(paymentGatewayResDto, mergePaymentResDto.getMergePaymentId());
      logicDto=buildWXLogicDto(map);      
    } else {
      errMsg=String.format("主动查询找到的支付网关异常,对应网关:%s", JSON.toJSONString(paymentGatewayResDto));
      log.info(errMsg);
      throw new ServiceException(errMsg);
    }
    if (null == logicDto) {
      return;
    }
    logicDto.setMergePaymentId(mergePaymentResDto.getMergePaymentId());
    logicDto.setGatewayId(paymentGatewayResDto.getGatewayId());
    
    ZiranNotifyLogic.notifyLogic(logicDto);
  }
//  
//  /**
//   * 支付宝交易查询
//   * 
//   * @param dto
//   * @param mergePayemntId
//   * @return
//   */
//  private Map<String, String> aliSingleTradeQuery(PaymentGatewayResDto dto,String mergePayemntId) throws ServiceException {
//    //把请求参数打包成数组
//    Map<String, String> sParaTemp = new HashMap<String, String>();
//    sParaTemp.put("service", "single_trade_query");
//    sParaTemp.put("partner", dto.getGatewayAccount());
//    sParaTemp.put("_input_charset", "utf-8");
//    sParaTemp.put("out_trade_no", mergePayemntId);
//
//    Map<String, String> map=new HashMap<>();
//    String xml =null;
//    //建立请求
//    try {
//      log.debug("ali交易查询请求:{}", sParaTemp);
//      xml=AlipaySubmit.sendHttpResquest(sParaTemp, dto.getQueryApi(), dto.getGatewayKey(),dto.getSignType(), "", "");
//      log.debug("ali交易查询响应:{}", xml);
//      map = CommonUtil.getMapFromXMLWithAttr(xml);
//    } catch (Exception e) {
//      log.error("ali交易查询请求异常,请求map:{},异常信息:{}",sParaTemp,e.getMessage());
//      return map;
//    }
//    return map;
//  }
//  
  //
  private ZiranNotifyLogicDto buildAliLogicDto(Map<String, String> map) throws ServiceException {
    
    String errMsg="";
    if(!"T".equalsIgnoreCase(map.get("is_success"))){
      errMsg=String.format("ali交易查询异常,异常提示:%s,对应返回:%s", map.get("error"), map);
      log.info(errMsg);
      throw new ServiceException(errMsg);
    }
    ZiranNotifyLogicDto logicDto=new ZiranNotifyLogicDto();
    logicDto.setPaymentType(1);
    if (map.get("trade_status").equalsIgnoreCase("TRADE_SUCCESS")//支付成功 
        || map.get("trade_status").equalsIgnoreCase("TRADE_FINISHED")) {//交易成功结束 
      logicDto.setPaymentStateId(PaymentStateConst.TRADE_SUCCESS);
    } else if (map.get("trade_status").equalsIgnoreCase("TRADE_CLOSED")) {// 交易中途关闭（已结束，未成功完成） 
      logicDto.setPaymentStateId(PaymentStateConst.TRADE_FAIL);
    } else {
      errMsg = String.format("ali交易查询trade_status:%s,不处理...", map.get("trade_status"));
      log.info(errMsg);
      throw new ServiceException(errMsg);
    }
    logicDto.setThirdTradeNo(map.get("out_trade_no"));
    if(StringUtils.isNotBlank(map.get("gmt_payment"))){
      try {
        Date tradeTime = DateUtils.parseDate(map.get("gmt_payment"), new String[] {"yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd+HH:mm:ss"});
        logicDto.setTradeTime(tradeTime);
      } catch (ParseException e) {
        log.error("ali交易查询结果gmt_payment异常,查询结果:{},异常信息:{}", map, e.getMessage());
      }
    }
    if(StringUtils.isNotBlank(map.get("gmt_close"))){
      try {
        Date tradeCloseTime = DateUtils.parseDate(map.get("gmt_close"), new String[] {"yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd+HH:mm:ss"});
        logicDto.setTradeCloseTime(tradeCloseTime);
      } catch (ParseException e) {
        log.error("ali交易查询结果gmt_close异常,查询结果:{},异常信息:{}", map, e.getMessage());
      }
    }
    
    return logicDto;
  }
  
//  /**
//   * 微信交易查询
//   * 
//   * @param dto
//   * @param mergePayemntId
//   * @return
//   */
//  private Map<String, String> wxOrderQuery(PaymentGatewayResDto dto, String mergePayemntId) throws ServiceException {
//    StringBuffer sb = new StringBuffer();
//    sb.append("<xml>");
//    sb.append(String.format("<appid><![CDATA[%s]]></appid>", dto.getGatewayAccount()));
//    sb.append(String.format("<mch_id><![CDATA[%s]]></mch_id>", dto.getGatewayUserName()));
//    sb.append(String.format("<out_trade_no><![CDATA[%s]]></out_trade_no>", mergePayemntId));
//    sb.append(String.format("<nonce_str><![CDATA[%s]]></nonce_str>", CommonUtil.getRandomStringByLength(32)));
//    String sign = Signature.getSign(CommonUtil.getMapFromXML(sb.toString() + "</xml>"), dto.getGatewayKey());
//    sb.append(String.format("<sign><![CDATA[%s]]></sign>", sign));
//    sb.append("</xml>");
//    
//    Map<String, String> map=new HashMap<>();
//    try {
//      log.debug("wx交易查询请求:{}", sb.toString());
//      String revXml = WXHttpsUtil.sendPost(dto.getQueryApi(), sb.toString(), false, null, null);
//      log.debug("wx交易查询响应:{}", revXml);
//      map= CommonUtil.getMapFromXML(revXml);
//    } catch (Exception e) {
//      log.error("wx交易查询请求异常,请求xml,异常信息:{}",sb.toString(),e.getMessage());
//      e.printStackTrace();
//    }
//    return map;
//  }
  
  //
  private ZiranNotifyLogicDto buildWXLogicDto(Map<String, String> map) throws ServiceException {
    
    String errMsg="";
    if("FAIL".equalsIgnoreCase(map.get("return_code"))){
      errMsg=String.format("wx交易查询异常,异常提示:%s,对应返回:%s", map.get("return_msg"), map);
      log.info(errMsg);
      throw new ServiceException(errMsg);
    }
    if ("FAIL".equalsIgnoreCase(map.get("result_code"))) {
      errMsg=String.format("wx交易查询结果异常,对应提示码:%s,提示描述:%s,返回:%s", map.get("err_code"), map.get("err_code_des"), map);
      log.info(errMsg);
      throw new ServiceException(errMsg);
    }
    ZiranNotifyLogicDto logicDto=new ZiranNotifyLogicDto();
    logicDto.setPaymentType(2);
    if (map.get("trade_state").equalsIgnoreCase("SUCCESS")) {
      logicDto.setPaymentStateId(PaymentStateConst.TRADE_SUCCESS);
    } else if (map.get("trade_state").equalsIgnoreCase("CLOSED")// 已关闭
        || map.get("trade_state").equalsIgnoreCase("REVOKED")// 已撤销（刷卡支付）
        || map.get("trade_state").equalsIgnoreCase("PAYERROR")) {// 支付失败
      logicDto.setPaymentStateId(PaymentStateConst.TRADE_FAIL);
    } else if (map.get("trade_state").equalsIgnoreCase("USERPAYING")// 用户支付中
        || map.get("trade_state").equalsIgnoreCase("NOTPAY")// 未支付
        || map.get("trade_state").equalsIgnoreCase("REFUND")) {// 转入退款
      
      errMsg=String.format("wx交易查询结果trade_state:%s,不处理...", map.get("trade_state"));
      log.info(errMsg);
      throw new ServiceException(errMsg);
    }
    logicDto.setThirdTradeNo(map.get("out_trade_no"));
    if(StringUtils.isNotBlank(map.get("time_end"))){
      try {
        Date tradeTime = DateUtils.parseDate(map.get("time_end"), new String[] {"yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd+HH:mm:ss"});
        logicDto.setTradeTime(tradeTime);
      } catch (ParseException e) {
        log.error("wx交易查询结果time_end异常,查询结果:{},异常信息:{}", map, e.getMessage());
      }
    }
    if(StringUtils.isNotBlank(map.get("bank_type"))){
      logicDto.setBankCode(map.get("bank_type"));
    }
    return logicDto;
  }
}
