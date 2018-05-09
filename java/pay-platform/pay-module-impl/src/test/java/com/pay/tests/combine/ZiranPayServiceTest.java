package com.pay.tests.combine;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.pay.combine.impl.ThirdTradeQuery;
import com.pay.combine.wx.Signature;
import com.pay.consts.GatewayCodeConst;
import com.pay.consts.PaymentSourceConst;
import com.pay.utils.CommonUtil;

import api.pay.combine.dto.PayItemReqDto;
import api.pay.combine.dto.PayReqDto;
import api.pay.combine.dto.ResData;
import api.pay.combine.service.ZiranPayService;
import api.pay.single.dto.res.MergePaymentResDto;
import api.pay.single.dto.res.PaymentGatewayResDto;
import api.pay.single.service.MergePaymentService;
import api.pay.single.service.PaymentGatewayService;
import api.pay.utils.IPUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/notenv/applicationContext.xml")
public class ZiranPayServiceTest {

  @Autowired
  private ZiranPayService ziranPayService;
  
  //ali支付
  @Test
  public void test01(){
    PayReqDto dto=new PayReqDto();
    dto.setMerchantId("zr001");
    dto.setGatewayCode(GatewayCodeConst.GATEWAY_ALIDIRECTPAY);
    dto.setReturnUrl("http://zgxcw.ngrok.natapp.cn/pay-web/ali/sync");
    dto.setNotifyUrl("http://zgxcw.ngrok.natapp.cn/pay-web/ali/async");
    dto.setFromIp(IPUtil.getLocalAddress().getHostAddress());
    dto.setPaymentSource(PaymentSourceConst.PAYSOURCE_ORDER);
    //dto.setBankCode("CMB");
    dto.setVersion("1.0");
    
    List<PayItemReqDto> payItemReqList=new ArrayList<>();
    
    PayItemReqDto itemDto=new PayItemReqDto();
    itemDto.setOrderNo("test20160403019");
    itemDto.setTotalFee(1);
    itemDto.setSpName("spming");
    itemDto.setSpDetail("spDetail");
    itemDto.setTimeOut(DateUtils.addDays(new Date(), 2));
    itemDto.setExtraCommonParam("{\"id\":\"xxx\"}");
    payItemReqList.add(itemDto);
    
//    itemDto=new PayItemReqDto();
//    itemDto.setOrderNo("test20160402002");
//    itemDto.setTotalFee(1);
//    itemDto.setSpName("spming2");
//    itemDto.setSpDetail("spDetail");
//    itemDto.setTimeOut(DateUtils.addDays(new Date(), 1));
//    //itemDto.setExtraCommonParam("{\"id\":\"xxx\"}");
//    payItemReqList.add(itemDto);
    
    dto.setPayItemReqList(payItemReqList);
    
//    PayReqDto dto2=new PayReqDto();
//    BeanUtils.copyProperties(dto, dto2);
//    System.out.println(dto2);
    ResData<String> res=ziranPayService.pay(dto);
    System.out.println(JSON.toJSONString(res));
    System.out.println(res.getT());
  }
  
  //wx支付
  @Test
  public void test02(){
    PayReqDto dto=new PayReqDto();
    dto.setMerchantId("zr001");
    dto.setGatewayCode(GatewayCodeConst.GATEWAY_WXMCHPAY);
    //dto.setReturnUrl("http://www.baidu.com");
    dto.setNotifyUrl("http://101.36.73.229:11007/paynotify/wx/async");
    dto.setFromIp(IPUtil.getLocalAddress().getHostAddress());
    dto.setPaymentSource(PaymentSourceConst.PAYSOURCE_ORDER);
    //dto.setBankCode("CMB");
    dto.setVersion("1.0");
    dto.setOpenid("oaVwYwwqPUFvPhFbhyUjZkUY8AsI");
    
    List<PayItemReqDto> payItemReqList=new ArrayList<>();
    
    PayItemReqDto itemDto=new PayItemReqDto();
    itemDto.setOrderNo("test20160401102");
    itemDto.setTotalFee(1);
    itemDto.setSpName("spming");
    //itemDto.setSpDetail("spDetail");
    itemDto.setTimeOut(DateUtils.addDays(new Date(), 2));
    itemDto.setExtraCommonParam("{\"id\":\"xxx\"}");
    payItemReqList.add(itemDto);
    
//    itemDto=new PayItemReqDto();
//    itemDto.setOrderNo("test20160401055");
//    itemDto.setTotalFee(1);
//    itemDto.setSpName("spming2");
//    //itemDto.setSpDetail("spDetail");
//    itemDto.setTimeOut(DateUtils.addDays(new Date(), 1));
//    //itemDto.setExtraCommonParam("{\"id\":\"xxx\"}");
//    payItemReqList.add(itemDto);
    
    dto.setPayItemReqList(payItemReqList);
    
    ResData<String> res=ziranPayService.pay(dto);
    System.out.println(JSON.toJSONString(res));
    System.out.println(res.getT());
  }
  
  //ali异步通知
  @Test
  public void test03(){
    Map<String, String> map=new HashMap<>();
    map.put("buyer_id","2088402820936615");
    map.put("trade_no","2016041800001000610089936585");
    map.put("use_coupon","N");
    map.put("notify_time","2016-04-18 10:42:34");
    map.put("subject","合并支付");
    map.put("sign_type","MD5");
    map.put("is_total_fee_adjust","N");
    map.put("notify_type","trade_status_sync");
    map.put("out_trade_no","78b2de19706c4ab9b29f99d41fa6b591");
    map.put("gmt_payment","2016-04-18 10:42:33");
    map.put("trade_status","TRADE_SUCCESS");
    map.put("discount","0.00");
    map.put("sign","7ddbd180c31d00a8e9e0937e3219b973");
    map.put("buyer_email","wenshiwei8888@163.com");
    map.put("gmt_create","2016-04-18 10:42:19");
    map.put("price","0.01");
    map.put("total_fee","0.01");
    map.put("quantity","1");
    map.put("seller_id","2088211554961671");
    map.put("notify_id","2057e0b8fe4088824ef2bb04704866885f");
    map.put("seller_email","zhang9192@vip.qq.com");
    map.put("payment_type","1");
    
    //必须有这个, 标明是微信异步通知
    map.put("ziran_pay_type","ali_async_notify");
    ziranPayService.asyncNotify(map);
  }
  
//ali移动支付异步通知
  @Test
  public void test04(){
    Map<String, String> map=new HashMap<>();
    map.put("buyer_id","2088402820936615");
    map.put("trade_no","2015122921001004610003643722");
    map.put("body","元旦01");
    map.put("notify_time","2015-12-29 18:48:17");
    map.put("use_coupon","N");
    map.put("subject","元旦");
    map.put("sign_type","RSA");
    map.put("is_total_fee_adjust","N");
    map.put("notify_type","trade_status_sync");
    map.put("out_trade_no","1678eb28e5bb43c091837ba41efdedad");
    map.put("trade_status","TRADE_SUCCESS");
    map.put("gmt_payment","2015-12-29 18:48:16");
    map.put("discount","0.00");
    map.put("sign","MCeKYooxJRlVRuEXmogR2ewaKX3XG/Nv9SneXg7nZU/6lWziDKXvrV7Lcay6TE6Iao58ZX3YF4DnPEX5iupMGeDE6x1tF/rSP1vd6kNCdAoU0nyhUWcRPbx7f+aYV/G8L+nK9pO416Yo4y76yiH9hDxXqfkpKPdI+vGhAOGyF/k=");
    map.put("buyer_email","wenshiwei8888@163.com");
    map.put("gmt_create","2015-12-29 18:48:16");
    map.put("price","0.01");
    map.put("total_fee","0.01");
    map.put("quantity","1");
    map.put("seller_id","2088211554961671");
    map.put("seller_email","zhang9192@vip.qq.com");
    map.put("notify_id","df3f9defa92b657d66f17b92c5f8c23kpg");
    map.put("payment_type","1");
    
    //必须有这个, 标明是微信异步通知
    map.put("ziran_pay_type","ali_async_notify");
    ziranPayService.asyncNotify(map);
  }
  
  //微信异步通知
  @Test
  public void test05(){
    Map<String, String> map=new HashMap<>();
    map.put("appid","wxe21ad38684c7166d");
    map.put("bank_type","CFT");
    map.put("cash_fee","1");
    map.put("fee_type","CNY");
    map.put("is_subscribe","Y");
    map.put("mch_id","1322370501");
    map.put("nonce_str","goxla72g1a9k08mpbn8sarg9somys1yr");
    map.put("openid","oaVwYwwqPUFvPhFbhyUjZkUY8AsI");
    map.put("out_trade_no","b66ed9220169494c949b6bf119cc9836");
    map.put("result_code","SUCCESS");
    map.put("return_code","SUCCESS");
    map.put("sign","31D54C6185BD313EE89B4E46B8664B84");
    map.put("time_end","20160321161910");
    map.put("total_fee","1");
    map.put("trade_type","NATIVE");
    map.put("transaction_id","4001642001201603214161369254");
    
    //必须有这个, 标明是微信异步通知
    map.put("ziran_pay_type","wx_async_notify");
    ziranPayService.asyncNotify(map);
  }
  
  //微信jsapi支付H5对象参数
  @Test
  public void test06(){
    StringBuffer sb=new StringBuffer();
    sb.append("<xml>");
    sb.append(String.format("<appid><![CDATA[%s]]></appid>", "wxe21ad38684c7166d"));
    sb.append(String.format("<timeStamp><![CDATA[%s]]></timeStamp>", 1460465688));
    sb.append(String.format("<nonce_str><![CDATA[%s]]></nonce_str>", "79qr02g5461lgmfj4ggnze8prmormppe"));
    sb.append(String.format("<package><![CDATA[%s]]></package>", "prepay_id=wx20160412093437ee0ca2002a0686254687"));
    sb.append(String.format("<signType><![CDATA[%s]]></signType>", "MD5"));
    
    String sign=Signature.getSign(CommonUtil.getMapFromXML(sb.toString()+"</xml>"),"CxukqT0OWlXXXrdKFkwfIKXnlFZ11qzC");
    sb.append(String.format("<sign><![CDATA[%s]]></sign>", sign));
    sb.append("</xml>");
    System.out.println(sb.toString());
  }
  
  //主动查询
  @Test
  public void test07(){
    ziranPayService.activeQuery();
  }
  
  ////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////
  
  @Autowired
  private PaymentGatewayService paymentGatewayService;
  @Autowired
  private MergePaymentService mergePaymentService;
  
  //第三方交易查询
  @Test
  public void test90() {
    
    String mergePaymentId="0993d80cd46d462b9078524858f976fc";
    MergePaymentResDto mergePaymentResDto=mergePaymentService.findById(mergePaymentId);
    if(null==mergePaymentResDto){
      System.out.println("===> mergePaymentId:" + mergePaymentId + "未找到合并支付单...");
      return;
    }
    PaymentGatewayResDto paymentGatewayResDto=paymentGatewayService.findById(mergePaymentResDto.getGatewayId());
    if (null == paymentGatewayResDto) {
      System.out.println("===> gatewayId:" + mergePaymentResDto.getGatewayId() + "未找到支付网关...");
      return;
    }
    
  //去支付宝查询
    if (GatewayCodeConst.GATEWAY_ALIDIRECTPAY.equalsIgnoreCase(paymentGatewayResDto.getGatewayCode())
        || GatewayCodeConst.GATEWAY_ALIMOBILEPAY.equalsIgnoreCase(paymentGatewayResDto.getGatewayCode())) {
      ThirdTradeQuery.aliTradeQuery(paymentGatewayResDto, mergePaymentId);
    }
    //去微信查询
    else if (GatewayCodeConst.GATEWAY_WXNATIVEPAY.equalsIgnoreCase(paymentGatewayResDto.getGatewayCode())
        || GatewayCodeConst.GATEWAY_WXJSAPIPAY.equalsIgnoreCase(paymentGatewayResDto.getGatewayCode())
        || GatewayCodeConst.GATEWAY_WXAPPPAY.equalsIgnoreCase(paymentGatewayResDto.getGatewayCode())) {
      ThirdTradeQuery.wxTradeQuery(paymentGatewayResDto, mergePaymentId);
    } else {
      System.out.println("支付网关编码:" + paymentGatewayResDto.getGatewayCode() + "不存在...");
    }
    
  }
//  
//  //微信交易查询
//  @Test
//  public void test91(){
//    String mergePaymentId="02ac7c5541a34abd9af57e2315dd5943";
//    MergePaymentResDto mergePaymentResDto=mergePaymentService.findById(mergePaymentId);
//    if(null==mergePaymentResDto){
//      System.out.println("===> mergePaymentId:" + mergePaymentId + "未找到合并支付单...");
//      return;
//    }
//    PaymentGatewayResDto paymentGatewayResDto=paymentGatewayService.findById(mergePaymentResDto.getGatewayId());
//    if (null == paymentGatewayResDto) {
//      System.out.println("===> gatewayId:" + mergePaymentResDto.getGatewayId() + "未找到支付网关...");
//      return;
//    }
//    
//    ThirdTradeQuery.wxTradeQuery(paymentGatewayResDto, mergePaymentId);
//    
//  }
}
