package com.pay.combine.impl.pay;

import java.text.SimpleDateFormat;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pay.combine.wx.Signature;
import com.pay.combine.wx.WXHttpsUtil;
import com.pay.consts.GatewayCodeConst;
import com.pay.utils.CommonUtil;

import api.pay.combine.dto.PayReqDto;
import api.pay.combine.dto.ResData;
import api.pay.single.dto.res.MergePaymentResDto;
import api.pay.single.dto.res.PaymentGatewayResDto;
import api.pay.single.service.MergePaymentService;
import api.pay.single.service.PaymentGatewayService;
import api.pay.utils.IPUtil;
import api.pay.utils.ServiceException;

public class WXPayImpl {

  private static Logger log = LoggerFactory.getLogger(WXPayImpl.class);

  private static PaymentGatewayService paymentGatewayService;
  public void setPaymentGatewayService(PaymentGatewayService paymentGatewayService) {
    WXPayImpl.paymentGatewayService = paymentGatewayService;
  }
  
  private static MergePaymentService mergePaymentService;
  public void setMergePaymentService(MergePaymentService mergePaymentService) {
    WXPayImpl.mergePaymentService = mergePaymentService;
  }
  
  /**
   * 微信支付方式
   * @param dto
   * @return
   * @throws ServiceException
   */
  public static ResData<String> wxPay(PayReqDto dto) throws ServiceException {
    ResData<String> resData = new ResData<String>();
    resData.setCode("fail");
    String errMsg = "";

    PaymentGatewayResDto gateway = paymentGatewayService.findByCodeAndMerchantId(dto.getMerchantId(), dto.getGatewayCode());
    if (null == gateway) {
      errMsg = String.format("根据merchantId:{}和gatewayCode:{}没有找到相对应的支付网关...", dto.getMerchantId(),dto.getGatewayCode());
      resData.setMsg(errMsg);
      log.error(errMsg);
      return resData;
    }
    if (gateway.getGatewayState() == 1) {
      errMsg = String.format("根据merchantId:{}和gatewayCode:{}找到相对应的支付网关处理禁用状态...", dto.getMerchantId(),dto.getGatewayCode());
      resData.setMsg(errMsg);
      log.error(errMsg);
      return resData;
    }
    //
    resData = ZiranPayLogic.payLogic(dto, gateway);
    if(resData.getCode().equalsIgnoreCase("fail")){
      return resData;
    }
    MergePaymentResDto mergePayResDto= mergePaymentService.findById(resData.getT());
    resData.setT(null);//清除T内容
    resData.setCode("fail");//初始化设置
    
    StringBuffer sb=new StringBuffer();
    sb.append("<xml>");
    sb.append(String.format("<appid><![CDATA[%s]]></appid>", gateway.getGatewayAccount()));
    sb.append(String.format("<mch_id><![CDATA[%s]]></mch_id>", gateway.getGatewayUserName()));
    sb.append(String.format("<nonce_str><![CDATA[%s]]></nonce_str>", CommonUtil.getRandomStringByLength(32)));
    sb.append(String.format("<body><![CDATA[%s]]></body>", "合并支付"));
    sb.append(String.format("<out_trade_no><![CDATA[%s]]></out_trade_no>", mergePayResDto.getMergePaymentId()));
    sb.append(String.format("<total_fee><![CDATA[%s]]></total_fee>", mergePayResDto.getTotalFee()));
    sb.append(String.format("<spbill_create_ip><![CDATA[%s]]></spbill_create_ip>", IPUtil.getLocalAddress().getHostAddress()));
    sb.append(String.format("<time_expire><![CDATA[%s]]></time_expire>", new SimpleDateFormat("yyyyMMddHHmmss").format(mergePayResDto.getTimeOut())));
    sb.append(String.format("<notify_url><![CDATA[%s]]></notify_url>", gateway.getNotifyUrl()));
    if (dto.getGatewayCode().equalsIgnoreCase(GatewayCodeConst.GATEWAY_WXAPPPAY)) {
      sb.append(String.format("<trade_type><![CDATA[%s]]></trade_type>", "APP"));
    } else if (dto.getGatewayCode().equalsIgnoreCase(GatewayCodeConst.GATEWAY_WXJSAPIPAY)) {
      sb.append(String.format("<trade_type><![CDATA[%s]]></trade_type>", "JSAPI"));
      // trade_type=JSAPI，此参数必传, 微信H5支付
      sb.append(String.format("<openid><![CDATA[%s]]></openid>", dto.getOpenid()));
    } else if (dto.getGatewayCode().equalsIgnoreCase(GatewayCodeConst.GATEWAY_WXNATIVEPAY)) {
      sb.append(String.format("<trade_type><![CDATA[%s]]></trade_type>", "NATIVE"));
    }
    String sign=Signature.getSign(CommonUtil.getMapFromXML(sb.toString()+"</xml>"), gateway.getGatewayKey());
    sb.append(String.format("<sign><![CDATA[%s]]></sign>", sign));
    sb.append("</xml>");
    
    String revT = "";
    try {
      String revXml = WXHttpsUtil.sendPost(gateway.getPayApi(), sb.toString(), true, null, null);
      Map<String, String> revMap = CommonUtil.getMapFromXML(revXml);
      if(revMap.get("return_code").equalsIgnoreCase("fail")){
        resData.setMsg(revMap.get("return_msg"));
        return resData;
      }
      if(revMap.get("result_code").equalsIgnoreCase("fail")){
        resData.setMsg(revMap.get("err_code_des"));
        return resData;
      }
      if (revMap.get("trade_type").equalsIgnoreCase("JSAPI")
          || revMap.get("trade_type").equalsIgnoreCase("APP")) {
        revT = revMap.get("prepay_id");
      } else if (revMap.get("trade_type").equalsIgnoreCase("NATIVE")) {
        revT = revMap.get("code_url");
      } else {
        errMsg=String.format("微信支付响应[交易类型]不正确,对应请求响应:%s", revXml);
        log.error(errMsg);
        resData.setMsg(errMsg);
        return resData;
      }
    } catch (Exception e) {
      errMsg = String.format("微信%s支付请求异常:%s", dto.getGatewayCode(), e.getMessage());
      log.error(errMsg);
      resData.setMsg(errMsg);
      return resData;
    }
    resData.setCode("success");
    resData.setT(revT);
    return resData;
  }
  
  /**
   * 微信支付工具--企业付款
   * @param dto
   * @return
   * @throws ServiceException
   */
  public static ResData<String> wxPayToolsForMchPay(PayReqDto dto) throws ServiceException {
    ResData<String> resData = new ResData<String>();
    resData.setCode("fail");
    String errMsg = "";

    PaymentGatewayResDto gateway = paymentGatewayService.findByCodeAndMerchantId(dto.getMerchantId(), dto.getGatewayCode());
    if (null == gateway) {
      errMsg = String.format("根据merchantId:{}和gatewayCode:{}没有找到相对应的支付网关...", dto.getMerchantId(),dto.getGatewayCode());
      resData.setMsg(errMsg);
      log.info(errMsg);
      return resData;
    }
    if (gateway.getGatewayState() == 1) {
      errMsg = String.format("根据merchantId:{}和gatewayCode:{}找到相对应的支付网关处理禁用状态...", dto.getMerchantId(),dto.getGatewayCode());
      resData.setMsg(errMsg);
      log.info(errMsg);
      return resData;
    }
    //
    resData = ZiranPayLogic.payLogic(dto, gateway);
    if(resData.getCode().equalsIgnoreCase("fail")){
      return resData;
    }
    MergePaymentResDto mergePayResDto= mergePaymentService.findById(resData.getT());
    resData.setT(null);//清除T内容
    resData.setCode("fail");//初始化设置
    
    StringBuffer sb=new StringBuffer();
    sb.append("<xml>");
    sb.append(String.format("<mch_appid><![CDATA[%s]]></mch_appid>", gateway.getGatewayAccount()));
    sb.append(String.format("<mchid><![CDATA[%s]]></mchid>", gateway.getGatewayUserName()));
    sb.append(String.format("<nonce_str><![CDATA[%s]]></nonce_str>", CommonUtil.getRandomStringByLength(32)));
    sb.append(String.format("<partner_trade_no><![CDATA[%s]]></partner_trade_no>", mergePayResDto.getMergePaymentId()));
    sb.append(String.format("<openid><![CDATA[%s]]></openid>", dto.getOpenid()));
    sb.append("<check_name><![CDATA[NO_CHECK]]></check_name>");
    sb.append(String.format("<amount><![CDATA[%s]]></amount>", mergePayResDto.getTotalFee()));
    sb.append(String.format("<spbill_create_ip><![CDATA[%s]]></spbill_create_ip>", IPUtil.getLocalAddress().getHostAddress()));
    sb.append("<desc><![CDATA[销售分红]]></desc>");
    
    String sign=Signature.getSign(CommonUtil.getMapFromXML(sb.toString()+"</xml>"), gateway.getGatewayKey());
    sb.append(String.format("<sign><![CDATA[%s]]></sign>", sign));
    sb.append("</xml>");
    
    String revT = "";
    try {
      String revXml = WXHttpsUtil.sendPost(gateway.getPayApi(), sb.toString(), false, null, null);
      Map<String, String> revMap = CommonUtil.getMapFromXML(revXml);
      if(revMap.get("return_code").equalsIgnoreCase("fail")){
        resData.setMsg(revMap.get("return_msg"));
        return resData;
      }
      if(revMap.get("result_code").equalsIgnoreCase("fail")){
        resData.setMsg(revMap.get("err_code_des"));
        return resData;
      }
//      if (revMap.get("trade_type").equalsIgnoreCase("JSAPI")
//          || revMap.get("trade_type").equalsIgnoreCase("APP")) {
//        revT = revMap.get("prepay_id");
//      } else if (revMap.get("trade_type").equalsIgnoreCase("NATIVE")) {
//        revT = revMap.get("code_url");
//      } else {
//        errMsg=String.format("微信支付响应[交易类型]不正确,对应请求响应:%s", revXml);
//        log.error(errMsg);
//        resData.setMsg(errMsg);
//        return resData;
//      }
      revT="微信企业付款功能";
    } catch (Exception e) {
      errMsg = String.format("微信%s支付请求异常:%s", dto.getGatewayCode(), e.getMessage());
      log.error(errMsg);
      resData.setMsg(errMsg);
      return resData;
    }
    resData.setCode("success");
    resData.setT(revT);
    return resData;
  }
  
}
