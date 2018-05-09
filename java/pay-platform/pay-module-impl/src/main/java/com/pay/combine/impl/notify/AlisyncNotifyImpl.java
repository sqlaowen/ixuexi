package com.pay.combine.impl.notify;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import api.pay.single.dto.req.CreateAliReturnReqDto;
import api.pay.single.dto.res.PaymentResDto;
import api.pay.single.service.AliReturnService;
import api.pay.single.service.PaymentService;

//ali同步通知
public class AlisyncNotifyImpl {

  private static Logger log = LoggerFactory.getLogger(AlisyncNotifyImpl.class);
  
  private static AliReturnService aliReturnService;
  public void setAliReturnService(AliReturnService aliReturnService) {
    AlisyncNotifyImpl.aliReturnService = aliReturnService;
  }
  
  private static PaymentService paymentService;
  public void setPaymentService(PaymentService paymentService) {
    AlisyncNotifyImpl.paymentService = paymentService;
  }
  
  // ali同步天通知
  public static String syncNotify(Map<String, String> map) {
    //保存同步通知信息
    List<CreateAliReturnReqDto> aliReturnReqDtoList= bulidAliReturnReqDtoLit(map);
    for(CreateAliReturnReqDto reqDto:aliReturnReqDtoList){
      aliReturnService.saveOne(reqDto);
    }
    //
    String paymentId = "";
    if (map.containsKey("merge_trade_result")) {
//      String mergeTradeTesult = map.get("merge_trade_result");
//      if (StringUtils.isNotBlank(mergeTradeTesult)) {
//        Pattern p = Pattern.compile("&\\+&out_trade_no=(\\w+)&\\+&");
//        Matcher m = p.matcher(mergeTradeTesult);
//        if (m.find(0)) {
//          paymentId = m.group(1);
//        }
//      }
//      log.info("ali合并支付同步返回平台支付单id：" + paymentId);
    } else if (map.containsKey("out_trade_no")) {
      paymentId = map.get("out_trade_no");
      log.info("ali单笔支付同步返回平台支付单id：" + paymentId);
    }
    if(StringUtils.isBlank(paymentId)){
      log.error("ali同步通知异常,可能是非法请求,请求信息:{}...",map);
      return null;
    }
    //查询支付单
    PaymentResDto payment=paymentService.findById(paymentId);
    if(null==payment){
      log.error("ali同步通知未找到对应的支付单...支付单id:{},同步通知:{}",paymentId, map);
      return null;
    }
    //返回客户端url
    return payment.getReturnUrl();
  }
  
  /**
   * 构建List<CreateAliReturnReqDto>
   * @param map :Map<String, String>
   * @return List<CreateAliReturnReqDto>
   */
  public static List<CreateAliReturnReqDto> bulidAliReturnReqDtoLit(Map<String, String> map){
    
    List<CreateAliReturnReqDto> dtoList=new ArrayList<CreateAliReturnReqDto>();
    CreateAliReturnReqDto dto=null;
    
    //////////////////////////合并支付同步返回///////////////////////////////
    if(map.containsKey("merge_trade_result")){
//      for (String ostr : map.get("merge_trade_result").split("&-&")) {
//        dto=buildPartAliReturnReqDto(map);
//        
//        Integer totalFee =0;
//        for (String istr : ostr.split("&\\+&")) {
//          if (istr.split("=")[0].equals("out_trade_no") && StringUtils.isNotBlank(istr.split("=")[1])) {
//            dto.setPaymentId(istr.split("=")[1]);
//          }
//          if (istr.split("=")[0].equals("subject") && StringUtils.isNotBlank(istr.split("=")[1])) {
//            dto.setSubject(istr.split("=")[1]);
//          }
//          if (istr.split("=")[0].equals("trade_no") && StringUtils.isNotBlank(istr.split("=")[1])) {
//            dto.setTradeNo(istr.split("=")[1]);
//          }
//          if (istr.split("=")[0].equals("trade_status") && StringUtils.isNotBlank(istr.split("=")[1])) {
//            dto.setTradeStatus(istr.split("=")[1]);
//          }
//          if (istr.split("=")[0].equals("seller_email") && StringUtils.isNotBlank(istr.split("=")[1])) {
//            dto.setSellerEmail(istr.split("=")[1]);
//          }
//          if (istr.split("=")[0].equals("buyer_email") && StringUtils.isNotBlank(istr.split("=")[1])) {
//            dto.setBuyerEmail(istr.split("=")[1]);
//          }
//          if (istr.split("=")[0].equals("total_fee") && StringUtils.isNotBlank(istr.split("=")[1])) {
//            totalFee = (int) (Double.parseDouble(istr.split("=")[1]) * 100);
//            dto.setTotalFee(totalFee);
//          }
//          if (istr.split("=")[0].equals("body") && StringUtils.isNotBlank(istr.split("=")[1])) {
//            dto.setBody(istr.split("=")[1]);
//          }
//          if (istr.split("=")[0].equals("extra_common_param") && StringUtils.isNotBlank(istr.split("=")[1])) {
//            dto.setExtraCommonParam(istr.split("=")[1]);
//          }
//          if (istr.split("=")[0].equals("bank_seq_no") && StringUtils.isNotBlank(istr.split("=")[1])) {
//            dto.setBankSeqNo(map.get("bank_seq_no"));
//          }
//        }
//        dto.setReturnValue(map.toString());
//        dtoList.add(dto);
//      }
    } 
    //////////////////////////单笔支付同步返回///////////////////////////////
    else { 
      dto=buildPartAliReturnReqDto(map);
      
      if(StringUtils.isNotBlank(map.get("body"))){
        dto.setBody(map.get("body"));
      }
      if(StringUtils.isNotBlank(map.get("buyer_email"))){
        dto.setBuyerEmail(map.get("buyer_email"));
      }
      if(StringUtils.isNotBlank(map.get("buyer_id"))){
        dto.setBuyerId(map.get("buyer_id"));
      }
      if(StringUtils.isNotBlank(map.get("exterface"))){
        dto.setExterface(map.get("exterface"));
      }
      if(StringUtils.isNotBlank(map.get("extra_common_param"))){
        dto.setExtraCommonParam(map.get("extra_common_param"));
      }
      if(StringUtils.isNotBlank(map.get("notify_type"))){
        dto.setNotifyType(map.get("notify_type"));
      }
      if(StringUtils.isNotBlank(map.get("out_trade_no"))){
        dto.setMergePaymentId(map.get("out_trade_no"));
        dto.setOutTradeNo(map.get("out_trade_no"));
      }
      if(StringUtils.isNotBlank(map.get("payment_type"))){
        dto.setPaymentType(map.get("payment_type"));
      }
      if(StringUtils.isNotBlank(map.get("seller_email"))){
        dto.setSellerEmail(map.get("seller_email"));
      }
      if(StringUtils.isNotBlank(map.get("seller_id"))){
        dto.setSellerId(map.get("seller_id"));
      }
      if(StringUtils.isNotBlank(map.get("subject"))){
        dto.setSubject(map.get("subject"));
      }
      if(StringUtils.isNotBlank(map.get("total_fee"))){
        Integer totalFee=(int)(Double.parseDouble(map.get("total_fee"))*100);
        dto.setTotalFee(totalFee);
      }
      if(StringUtils.isNotBlank(map.get("trade_no"))){
        dto.setTradeNo(map.get("trade_no"));
      }
      if(StringUtils.isNotBlank(map.get("trade_status"))){
        dto.setTradeStatus(map.get("trade_status"));
      }
      if(StringUtils.isNotBlank(map.get("bank_seq_no"))){
        dto.setBankSeqNo(map.get("bank_seq_no"));
      }
      dto.setReturnValue(map.toString());
      
      dtoList.add(dto);
    }
    return dtoList;
  }
  
  /**
   * 构建单笔/合并支付相同返回部分
   * @param map :Map<String, String>
   * @return CreateAliReturnReqDto
   */
  private static CreateAliReturnReqDto buildPartAliReturnReqDto(Map<String, String> map){
    CreateAliReturnReqDto dto=new CreateAliReturnReqDto();
    if (StringUtils.isNotBlank(map.get("is_success"))) {
      dto.setIsSuccess(map.get("is_success"));
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
        dto.setNotifyTime(DateUtils.parseDate(map.get("notify_time"), new String[] {"yyyy-MM-dd HH:mm:ss","yyyy-MM-dd+HH:mm:ss"}));
      } catch (ParseException e) {
        log.error("ali同步通知通知notify_time转换异常,同步通知信息:{},异常信息:{}", map, e.getMessage());
        e.printStackTrace();
      }
    }
    return dto;
  }
}
