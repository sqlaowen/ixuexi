package com.pay.combine.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pay.combine.ali.AlipaySubmit;
import com.pay.combine.wx.Signature;
import com.pay.combine.wx.WXHttpsUtil;
import com.pay.utils.CommonUtil;

import api.pay.single.dto.res.PaymentGatewayResDto;
import api.pay.utils.ServiceException;

public class ThirdTradeQuery {

  private static Logger log = LoggerFactory.getLogger(ThirdTradeQuery.class);
  
  /**
   * 支付宝交易查询
   * 
   * @param dto
   * @param mergePaymentId
   * @return
   */
  public static Map<String, String> aliTradeQuery(PaymentGatewayResDto dto,String mergePaymentId) throws ServiceException {
    //把请求参数打包成数组
    Map<String, String> sParaTemp = new HashMap<String, String>();
    sParaTemp.put("service", "single_trade_query");
    sParaTemp.put("partner", dto.getGatewayAccount());
    sParaTemp.put("_input_charset", "utf-8");
    sParaTemp.put("out_trade_no", mergePaymentId);

    Map<String, String> map=new HashMap<>();
    String xml =null;
    //建立请求
    try {
      log.debug("ali交易查询请求参数:{}", sParaTemp);
      xml=AlipaySubmit.sendHttpResquest(sParaTemp, dto.getQueryApi(), dto.getGatewayKey(),dto.getSignType(), "", "");
      log.debug("ali交易查询响应xml:{}", xml);
      map = CommonUtil.getMapFromXMLWithAttr(xml);
    } catch (Exception e) {
      log.error("ali交易查询请求异常,请求map:{},异常信息:{}",sParaTemp,e.getMessage());
      return map;
    }
    return map;
  }
  
  /**
   * 微信交易查询
   * 
   * @param paymentGatewayResDto
   * @param mergePaymentId
   * @return
   */
  public static Map<String, String> wxTradeQuery(PaymentGatewayResDto paymentGatewayResDto,String mergePaymentId) throws ServiceException {
    
    StringBuffer sb = new StringBuffer();
    sb.append("<xml>");
    sb.append(String.format("<appid><![CDATA[%s]]></appid>", paymentGatewayResDto.getGatewayAccount()));
    sb.append(String.format("<mch_id><![CDATA[%s]]></mch_id>", paymentGatewayResDto.getGatewayUserName()));
    sb.append(String.format("<out_trade_no><![CDATA[%s]]></out_trade_no>", mergePaymentId));
    sb.append(String.format("<nonce_str><![CDATA[%s]]></nonce_str>", CommonUtil.getRandomStringByLength(32)));
    String sign = Signature.getSign(CommonUtil.getMapFromXML(sb.toString() + "</xml>"), paymentGatewayResDto.getGatewayKey());
    sb.append(String.format("<sign><![CDATA[%s]]></sign>", sign));
    sb.append("</xml>");
    
    Map<String, String> map=new HashMap<>();
    try {
      log.debug("wx交易查询请求参数:{}", sb.toString());
      String revXml = WXHttpsUtil.sendPost(paymentGatewayResDto.getQueryApi(), sb.toString(), false, null, null);
      log.debug("wx交易查询响应xml:{}", revXml);
      map= CommonUtil.getMapFromXML(revXml);
    } catch (Exception e) {
      log.error("wx交易查询请求异常,请求xml,异常信息:{}",sb.toString(),e.getMessage());
      e.printStackTrace();
    }
    return map;
  }
}
