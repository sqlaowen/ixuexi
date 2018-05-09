package com.pay.combine.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.alibaba.fastjson.JSON;
import com.pay.combine.ali.AliBuildForm;
import com.pay.combine.validate.AliDirectPayDtoValidate;
import com.pay.combine.validate.AliMergePayDtoValidate;
import com.pay.consts.PaymentStateConst;

import api.pay.combine.dto.AliDirectPayDto;
import api.pay.combine.dto.AliMergePayDto;
import api.pay.combine.dto.AliMergePayItemDto;
import api.pay.combine.dto.ResData;
import api.pay.combine.service.PayService;
import api.pay.single.dto.req.CreatePaymentCloseReqDto;
import api.pay.single.dto.req.CreatePaymentLogReqDto;
import api.pay.single.dto.req.CreatePaymentOrderReqDto;
import api.pay.single.dto.req.payment.CreatePaymentReqDto;
import api.pay.single.dto.req.payment.EditPaymentReqDto;
import api.pay.single.dto.res.PaymentResDto;
import api.pay.single.service.PaymentCloseService;
import api.pay.single.service.PaymentLogService;
import api.pay.single.service.PaymentOrderService;
import api.pay.single.service.PaymentService;

public class PayServiceImpl implements PayService {

  private Logger log = LoggerFactory.getLogger(this.getClass());
  private PaymentService paymentService;

  public void setPaymentService(PaymentService paymentService) {
    this.paymentService = paymentService;
  }

  private PaymentLogService paymentLogService;

  public void setPaymentLogService(PaymentLogService paymentLogService) {
    this.paymentLogService = paymentLogService;
  }

  private PaymentOrderService paymentOrderService;

  public void setPaymentOrderService(PaymentOrderService paymentOrderService) {
    this.paymentOrderService = paymentOrderService;
  }
  
  private PaymentCloseService paymentCloseService;

  public void setPaymentCloseService(PaymentCloseService paymentCloseService) {
    this.paymentCloseService = paymentCloseService;
  }

  /**
   * 纯网关支付接口:create_direct_pay_by_user
   * code:[success:成功;  fail:失败]
   * msg:失败提示
   * T:成功时返回的form表单
   * 
   * @param aliDirectPayDto :AliDirectPayDto
   * @return ResData<String>
   */
  @Override
  public ResData<String> aliDirectPay(AliDirectPayDto aliDirectPayDto) {
    log.info("网关支付请求参数:{}",JSON.toJSONString(aliDirectPayDto));
    //验证输入参数有效性
    ResData<String> resData = AliDirectPayDtoValidate.inParamValidate(aliDirectPayDto);
    if ("fail".equals(resData.getCode())){ 
      return resData;
    }
    
    //支付单业务处理
    ResData<PaymentResDto> resPaymentResDto = fnDirectPayLogic(aliDirectPayDto);
    if ("fail".equals(resPaymentResDto.getCode())) {
      resData.setCode("fail");
      resData.setMsg(resPaymentResDto.getMsg());
      resData.setT(null);
      return resData;
    }
    
    //生成ali网关支付form表单
    String html = AliBuildForm.fnDirectPayForm(resPaymentResDto.getT());
    resData.setCode("success");
    resData.setT(html);
    return resData;
  }
  
  /**
   * 合并支付接口:trade_merge_pay_by_user 
   * code:[success:成功;  fail:失败]
   * msg:失败提示
   * T:成功时返回的form表单
   * 
   * @param aliMergePayDto :AliMergePayDto
   * @return ResData<String>
   */
  @Override
  public ResData<String> aliMergePay(AliMergePayDto aliMergePayDto) {
    log.info("合并支付请求参数:{}",JSON.toJSONString(aliMergePayDto));
    
    if(null !=aliMergePayDto && 1==aliMergePayDto.getItemList().size()){
      log.info("请求参数AliMergePayDto.ItemList=1,转到网关支付...");
      AliDirectPayDto aliDirectPayDto=new AliDirectPayDto();
      BeanUtils.copyProperties(aliMergePayDto, aliDirectPayDto);
      BeanUtils.copyProperties(aliMergePayDto.getItemList().get(0), aliDirectPayDto);
      return aliDirectPay(aliDirectPayDto);
    } else {
      //验证输入参数有效性
      ResData<String> resData = AliMergePayDtoValidate.inParamValidate(aliMergePayDto);
      if ("fail".equals(resData.getCode())){ 
        return resData;
      }
      
      //支付单业务处理
      List<ResData<PaymentResDto>> listResPaymentResDto = fnMergePayLogic(aliMergePayDto);
      String errMsgList="";
      for(ResData<PaymentResDto> _res:listResPaymentResDto){
        if ("fail".equals(_res.getCode())) {
          errMsgList+=_res.getMsg()+"\r\n";
        }
      }
      if(!"".equals(errMsgList)){
        resData.setCode("fail");
        resData.setMsg(errMsgList);
        resData.setT(null);
        return resData;
      }
      
      List<PaymentResDto> listPaymentResDto=new ArrayList<PaymentResDto>();
      PaymentResDto paymentResDto=null;
      for(ResData<PaymentResDto> res:listResPaymentResDto){
        paymentResDto=new PaymentResDto();
        BeanUtils.copyProperties(res.getT(), paymentResDto);
        listPaymentResDto.add(paymentResDto);
      }
      
      log.debug("请求合并支付form支付单为:{}",JSON.toJSONString(listPaymentResDto));
      //生成ali合并支付form表单
      String html =AliBuildForm.fnMergePayForm(listPaymentResDto, aliMergePayDto.getNotifyUrl(), aliMergePayDto.getReturnUrl());
      resData.setCode("success");
      resData.setT(html);
      return resData;
    }
  }

  /**
   * 单笔交易查询接口:single_trade_query
   * 
   * @param orderId :商户订单号
   * @param paymentId :平台支付单流水号
   * @return String
   */
  @Override
  public String aliTradeQuery(String orderId, String paymentId) {
    log.info("单笔查询输入参数[orderId:{},paymentId:]",orderId,paymentId);
    
    if((null==orderId||"".equals(orderId.trim()))
        &&(null==paymentId||"".equals(paymentId.trim()))){
      log.error("单笔查询输入参数:orderId和tradeId至少提供一个");
      return "";
    }
    PaymentResDto paymentResDto = paymentService.findByPaymentIdOrderId(orderId, paymentId);
    if(null==paymentResDto){
      log.error("单笔查询输入参数[orderId:{},paymentId:{}]对应支付单不存在...",orderId,paymentId);
      return "";
    }
    return AliBuildForm.fnSingleTradeQuery(paymentResDto.getPaymentId());
  }

  /**
   * 交易关闭接口:close_trade
   * code:[T:成功;  F:失败]
   * msg:失败提示
   * 
   * @param orderId :商户订单号
   * @param paymentId :平台支付单流水号
   * @return ResData<String>
   */
  @Override
  public ResData<String> aliCloseTrade(String orderId, String paymentId) {
    log.info("关闭订单输入参数[orderId:{},paymentId:]",orderId,paymentId);
    
    ResData<String> res=new ResData<>();
    res.setCode("F");
    
    CreatePaymentCloseReqDto createPaymentCloseReqDto=new CreatePaymentCloseReqDto();
    createPaymentCloseReqDto.setOrderId(orderId);
    createPaymentCloseReqDto.setPaymentId(paymentId);//初始值,可能入参为空
    createPaymentCloseReqDto.setCloseCode("F");//初始值F:失败
    
    String errMsg="";
    //验证输入参数
    if((null==orderId||"".equals(orderId.trim()))
        &&(null==paymentId||"".equals(paymentId.trim()))){
      errMsg="输入参数:orderId和tradeId至少提供一个";
      log.error(errMsg);
      res.setMsg(errMsg);
      
      return res;
    }
    //验证是否请求过支付
    PaymentResDto paymentResDto = paymentService.findByPaymentIdOrderId(orderId, paymentId);
    if(null==paymentResDto){
      errMsg=String.format("交易关闭输入参数[orderId:%s,paymentId:%s]对应支付单不存在...", orderId,paymentId);
      log.error(errMsg);
      res.setMsg(errMsg);
      //保存关闭订单(支付单)记录
      createPaymentCloseReqDto.setCloseNote(errMsg);
      paymentCloseService.saveOne(createPaymentCloseReqDto);
      
      return res;
    }
    createPaymentCloseReqDto.setPaymentId(paymentResDto.getPaymentId());//修改初始值
    ///////////////////////////////////////////////////////////////////////////
    if(PaymentStateConst.TRADE_SUCCESS==paymentResDto.getPaymentStateId()){
      errMsg=String.format("交易关闭输入参数[orderId:%s,paymentId:%s]对应支付单已经支付成功",orderId,paymentId);
    } else if(PaymentStateConst.TRADE_FAIL==paymentResDto.getPaymentStateId()){
      errMsg=String.format("交易关闭输入参数[orderId:%s,paymentId:%s]对应支付单已经支付失败",orderId,paymentId);
    } else if(PaymentStateConst.TRADE_TIMEOUT==paymentResDto.getPaymentStateId()){
      errMsg=String.format("交易关闭输入参数[orderId:%s,paymentId:%s]对应支付单超时未支付",orderId,paymentId);
    } else if(PaymentStateConst.TRADE_CLOSE==paymentResDto.getPaymentStateId()){
      errMsg=String.format("交易关闭输入参数[orderId:%s,paymentId:%s]对应支付单已关闭",orderId,paymentId);
    } else if(PaymentStateConst.TRADE_NOT_EXIST==paymentResDto.getPaymentStateId()){
      errMsg=String.format("交易关闭输入参数[orderId:%s,paymentId:%s]对应支付单已关闭(TRADE_NOT_EXIST)",orderId,paymentId);
    }
    if(!"".equals(errMsg)){
      log.error(errMsg);
      res.setMsg(errMsg);
      //保存关闭订单(支付单)记录
      createPaymentCloseReqDto.setCloseNote(errMsg);
      paymentCloseService.saveOne(createPaymentCloseReqDto);
      
      return res;
    }
    ////////////////////////////////////////////////////////////////////////////
    String xml=AliBuildForm.fnCloseTrade(paymentResDto.getPaymentId());
    if(null == xml || "".equals(xml.trim())){
      errMsg=String.format("交易关闭输入参数[orderId:%s,paymentId:%s]请求ali关闭交易异常",orderId,paymentId);
      log.error(errMsg);
      res.setMsg(errMsg);
      //保存关闭订单(支付单)记录
      createPaymentCloseReqDto.setCloseNote(errMsg);
      paymentCloseService.saveOne(createPaymentCloseReqDto);
      
      return res;
    }
    Document doc = null;
    try {
      doc = DocumentHelper.parseText(xml);
    } catch (DocumentException e) {
      errMsg=String.format("解析交易关闭异常,请求参数[orderId:%s,paymentId:%s],返回xml:%s,异常信息:%s",orderId,paymentId,xml,e.getMessage());
      log.error(errMsg);
      res.setMsg(errMsg);
      //保存关闭订单(支付单)记录
      createPaymentCloseReqDto.setCloseNote(errMsg);
      paymentCloseService.saveOne(createPaymentCloseReqDto);
      
      return res;
    }
    Element root = doc.getRootElement();
    String isSuccess = root.element("is_success").getTextTrim().toUpperCase();
    res.setCode(isSuccess);
    //
    createPaymentCloseReqDto.setCloseCode(isSuccess);
    if (null != root.element("error")) {
      res.setMsg(root.element("error").getTextTrim().toUpperCase());
    }
    if("T".equals(isSuccess) //关闭交易成功
        ||("F".equals(isSuccess)&&"TRADE_NOT_EXIST".equals(res.getMsg()))){ //交易关闭失败 且 失败提示为"TRADE_NOT_EXIST"
      //1.更新支付单状态
      EditPaymentReqDto editPaymentReqDto=new EditPaymentReqDto();
      editPaymentReqDto.setPaymentId(paymentResDto.getPaymentId());
      editPaymentReqDto.setOrderCloseTime(new Date());
      //2.记录支付单状态日志
      CreatePaymentLogReqDto cratePaymentLogReqDto=new CreatePaymentLogReqDto();
      cratePaymentLogReqDto.setPaymentId(paymentResDto.getPaymentId());
      
      if("T".equals(isSuccess)){
        editPaymentReqDto.setPaymentStateId(PaymentStateConst.TRADE_CLOSE);
        cratePaymentLogReqDto.setPaymentStateId(PaymentStateConst.TRADE_CLOSE);
      } else{
        editPaymentReqDto.setPaymentStateId(PaymentStateConst.TRADE_NOT_EXIST);
        cratePaymentLogReqDto.setPaymentStateId(PaymentStateConst.TRADE_NOT_EXIST);
      }
      paymentService.editById(editPaymentReqDto);
      paymentLogService.saveOne(cratePaymentLogReqDto);
      
      //
      createPaymentCloseReqDto.setCloseCode("T");
    }
    //保存关闭订单(支付单)记录
    createPaymentCloseReqDto.setCloseNote(res.getMsg());
    paymentCloseService.saveOne(createPaymentCloseReqDto);
    
    return res;
  }

  
  /**
   * 网关支付业务逻辑
   * @param aliDirectPayDto
   * @return ResData<PaymentResDto>
   */
  private ResData<PaymentResDto> fnDirectPayLogic(AliDirectPayDto aliDirectPayDto) {
    ResData<PaymentResDto> res=null;
    
    PaymentResDto paymentResDto = paymentService.findByOrderId(aliDirectPayDto.getOrderId());
    // 如果订单已经请求过支付
    if (null != paymentResDto) {
      log.debug("订单号:{}已存在,对应支付单号:{}",aliDirectPayDto.getOrderId(),paymentResDto.getPaymentId());
      return AliDirectPayDtoValidate.inParamChangeValidate(aliDirectPayDto, paymentResDto);
    } else {
      // 保存支付单
      log.debug("1.保存支付单...");
      CreatePaymentReqDto paymentReqDto = new CreatePaymentReqDto();
      BeanUtils.copyProperties(aliDirectPayDto, paymentReqDto);
      String paymentId = paymentService.saveOne(paymentReqDto);

      // 保存订单ref支付单
      log.debug("2.保存订单ref支付单...");
      CreatePaymentOrderReqDto paymentOrderReqDto = new CreatePaymentOrderReqDto();
      paymentOrderReqDto.setOrderId(aliDirectPayDto.getOrderId());
      paymentOrderReqDto.setPaymentId(paymentId);
      paymentOrderService.saveOne(paymentOrderReqDto);

      // 保存支付单日志
      log.debug("3.保存支付单日志...");
      CreatePaymentLogReqDto payLogReqDto = new CreatePaymentLogReqDto();
      payLogReqDto.setPaymentId(paymentId);
      payLogReqDto.setPaymentStateId(PaymentStateConst.TRADE_CREATE);
      paymentLogService.saveOne(payLogReqDto);
      
      /////////////////////////////////////////////////////////////////
      // 返回PaymentResDto
      paymentResDto = new PaymentResDto();
      BeanUtils.copyProperties(aliDirectPayDto, paymentResDto);
      paymentResDto.setPaymentId(paymentId);
      
      res=new ResData<PaymentResDto>();
      res.setCode("success");
      res.setT(paymentResDto);
      return res;
    }
  }
  
  /**
   * 合并支付业务逻辑
   * @param aliMergePayDto
   * @return List<ResData<PaymentResDto>>
   */
  private List<ResData<PaymentResDto>> fnMergePayLogic(AliMergePayDto aliMergePayDto) {

    List<ResData<PaymentResDto>> resList = new ArrayList<ResData<PaymentResDto>>();
    ResData<PaymentResDto> res = null;
    PaymentResDto paymentResDto = null;
    
    for (AliMergePayItemDto itemDto : aliMergePayDto.getItemList()) {
      paymentResDto = paymentService.findByOrderId(itemDto.getOrderId());
      if(null != paymentResDto){
        log.debug("订单号:{}已存在,对应支付单号:{}",itemDto.getOrderId(),paymentResDto.getPaymentId());
        res=AliMergePayDtoValidate.inParamChangeValidate(itemDto, paymentResDto,
            aliMergePayDto.getReturnUrl(),aliMergePayDto.getNotifyUrl(),aliMergePayDto.getPaymentSource());
        resList.add(res);
      } else{
        // 保存支付单
        log.debug("1.保存支付单...");
        CreatePaymentReqDto paymentReqDto = new CreatePaymentReqDto();
        BeanUtils.copyProperties(aliMergePayDto, paymentReqDto);
        BeanUtils.copyProperties(itemDto, paymentReqDto);
        String paymentId = paymentService.saveOne(paymentReqDto);
  
        // 保存订单ref支付单
        log.debug("2.保存订单ref支付单...");
        CreatePaymentOrderReqDto paymentOrderReqDto = new CreatePaymentOrderReqDto();
        paymentOrderReqDto.setOrderId(itemDto.getOrderId());
        paymentOrderReqDto.setPaymentId(paymentId);
        paymentOrderService.saveOne(paymentOrderReqDto);
  
        // 保存支付单日志
        log.debug("3.保存支付单日志...");
        CreatePaymentLogReqDto payLogReqDto = new CreatePaymentLogReqDto();
        payLogReqDto.setPaymentId(paymentId);
        payLogReqDto.setPaymentStateId(PaymentStateConst.TRADE_CREATE);
        paymentLogService.saveOne(payLogReqDto);
  
        /////////////////////////////////////////////////////////////////
        paymentResDto = new PaymentResDto();
        BeanUtils.copyProperties(aliMergePayDto, paymentResDto);
        BeanUtils.copyProperties(itemDto, paymentResDto);
        paymentResDto.setPaymentId(paymentId);
  
        res = new ResData<PaymentResDto>();
        res.setCode("success");
        res.setT(paymentResDto);
        resList.add(res);
      }
    }
    return resList;
  }

}
