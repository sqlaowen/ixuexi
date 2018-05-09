package com.pay.combine.ali;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import api.pay.single.dto.res.PaymentResDto;

public class AliBuildForm {

  private static Logger log = LoggerFactory.getLogger(AliBuildForm.class);

  /**
   * 构建网关支付form表单
   * 接口名: create_direct_pay_by_user
   * 
   * @return String
   */
  public static String fnDirectPayForm(PaymentResDto dto) {
    // 把请求参数打包成数组
    Map<String, String> sParaTemp = new HashMap<String, String>();
    sParaTemp.put("service", "create_direct_pay_by_user"); // 接口名
    sParaTemp.put("partner", AlipayConfig.partner); // partnerId
    sParaTemp.put("seller_email", AlipayConfig.seller_email); // 卖家支付宝账号
    sParaTemp.put("_input_charset", AlipayConfig.input_charset); // 编码方式
    sParaTemp.put("notify_url", dto.getNotifyUrl()); // 异步url
    sParaTemp.put("return_url", dto.getReturnUrl()); // 同步url
    sParaTemp.put("out_trade_no", dto.getPaymentId()); // 商户唯一支付单id
    sParaTemp.put("subject", dto.getSpName()); // 商品名
    sParaTemp.put("payment_type", "1");// 支付类型(1:商品购买)
    sParaTemp.put("total_fee", String.format("%#.2f", dto.getTotalFee() / 100.0)); // 总金额
    sParaTemp.put("body", dto.getSpDetail()); // 商品详情
    sParaTemp.put("extra_common_param", dto.getExtraCommonParam()); // 公用回传参数
    // 计算过期时间 按照分钟计算 取值范围：1m～15d。
    double mins = (dto.getTimeOut().getTime() - System.currentTimeMillis()) / 1000.0 / 60.0;
    sParaTemp.put("it_b_pay", (int) Math.ceil(mins) + "m"); // 过期时间

//    sParaTemp.put("paymethod", "bankPay");
//    sParaTemp.put("defaultbank", "ICBCB2C");
//    sParaTemp.put("body", "诸葛天下（北京）信息技术股份有限公司");
    
    
    log.debug("ali网关支付请求:\r\n{}", sParaTemp);
    String html = AlipaySubmit.buildRequest(sParaTemp, "post", "提交");
    log.debug("ali网关支付响应:\r\n{}", html);
    return html;
  }
  
  /**
   * 构建合并支付form表单
   * 接口名: trade_merge_pay_by_user
   * 
   * @param dtoList
   * @param notifyUrl
   * @param returnUrl
   * @return
   */
  public static String fnMergePayForm(List<PaymentResDto> dtoList,String notifyUrl,String returnUrl){
    // 把请求参数打包成数组
    Map<String, String> sParaTemp = new HashMap<String, String>();
    sParaTemp.put("service", "trade_merge_pay_by_user"); // 接口名
    sParaTemp.put("partner", AlipayConfig.partner); // partnerId
    sParaTemp.put("_input_charset", AlipayConfig.input_charset); // 编码方式
    sParaTemp.put("notify_url", notifyUrl); // 异步url
    sParaTemp.put("return_url", returnUrl); // 同步url
    sParaTemp.put("merge_trade_info",getMergeTradeInfo(dtoList));

    log.debug("ali合并支付请求:\r\n{}", sParaTemp);
    String html = AlipaySubmit.buildRequest(sParaTemp, "post", "提交");
    log.debug("ali合并支付响应:\r\n{}", html);
    return html;
  }
  
  /**
   * 单笔交易查询:single_trade_query
   * 
   * @param paymentId :支付单流水号
   * @return String
   */
  public static String fnSingleTradeQuery(String paymentId) {
    //把请求参数打包成数组
    Map<String, String> sParaTemp = new HashMap<String, String>();
    sParaTemp.put("service", "single_trade_query");
    sParaTemp.put("partner", AlipayConfig.partner);
    sParaTemp.put("_input_charset", AlipayConfig.input_charset);
    sParaTemp.put("out_trade_no", paymentId);

    String html =null;
    //建立请求
    try {
      log.debug("ali交易查询请求:\r\n{}", sParaTemp);
      html=AlipaySubmit.buildRequest("", "", sParaTemp);
      log.debug("ali交易查询响应:\r\n{}", html);
    } catch (Exception e) {
      log.error("ali交易查询请求:{},异常:{}",sParaTemp,e.getMessage());
      e.printStackTrace();
    }
    return html;
  }
  
  /**
   * 交易关闭:close_trade
   * 
   * @param paymentId :支付单流水号
   * @return String
   */
  public static String fnCloseTrade(String paymentId){
    Map<String, String> sParaTemp = new HashMap<String, String>();
    sParaTemp.put("service", "close_trade");
    sParaTemp.put("partner", AlipayConfig.partner);
    sParaTemp.put("_input_charset", AlipayConfig.input_charset);
    sParaTemp.put("out_order_no", paymentId);
    
    //建立请求
    String html=null;
    try {
      log.debug("ali交易关闭请求:\r\n{}", sParaTemp);
      html=AlipaySubmit.buildRequest("", "", sParaTemp);
      log.debug("ali交易关闭响应:\r\n{}", html);
    } catch (Exception e) {
      log.error("ali交易关闭请求:{},异常:{}",sParaTemp,e.getMessage());
      e.printStackTrace();
    }
    return html;
  }
  
  //合并支付信息
  private static String getMergeTradeInfo(List<PaymentResDto> dtoList) {
    
    StringBuilder sb = new StringBuilder();
    Map<String, String> para =null;
    for (PaymentResDto dto : dtoList) {
      para = new HashMap<String, String>();
      para.put("partner", AlipayConfig.partner);
      para.put("out_trade_no", dto.getPaymentId());
      para.put("subject", dto.getSpName());
      para.put("seller_email", AlipayConfig.seller_email);
      para.put("total_fee", String.format("%#.2f", dto.getTotalFee() / 100.0));
      para.put("extra_common_param", dto.getExtraCommonParam());
      // 计算过期时间 按照分钟计算 取值范围：1m～15d。
      if(null != dto.getTimeOut()){
        double mins = (dto.getTimeOut().getTime() - System.currentTimeMillis()) / 1000.0 / 60.0;
        para.put("it_b_pay", (int) Math.ceil(mins) + "m");
      }
      //生成要请求给支付宝的参数数组
      para = AlipaySubmit.buildRequestPara(para);
      
      if (sb.length() != 0) {
        sb.append("&-&");
      }
      List<String> keys = new ArrayList<String>(para.keySet());
      for (int i = 0; i < keys.size(); i++) {
        String name = keys.get(i);
        String value = para.get(name);
        if (i == 0)
          sb.append(name + "=" + value);
        else {
          sb.append("&+&" + name + "=" + value);
        }
      }
    }
    return sb.toString();
  }

}
