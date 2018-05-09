package com.pay.combine.impl.pay;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.pay.combine.validate.PayReqDtoValidate;
import com.pay.consts.PaymentStateConst;

import api.pay.combine.dto.PayItemReqDto;
import api.pay.combine.dto.PayReqDto;
import api.pay.combine.dto.ResData;
import api.pay.single.dto.req.CreatePaymentLogReqDto;
import api.pay.single.dto.req.CreatePaymentOrderReqDto;
import api.pay.single.dto.req.CreatePaymentRefMergeReqDto;
import api.pay.single.dto.req.mergepayment.CreateMergePaymentReqDto;
import api.pay.single.dto.req.payment.CreatePaymentReqDto;
import api.pay.single.dto.req.payment.EditPaymentReqDto;
import api.pay.single.dto.res.PaymentGatewayResDto;
import api.pay.single.dto.res.PaymentResDto;
import api.pay.single.service.MergePaymentService;
import api.pay.single.service.PaymentLogService;
import api.pay.single.service.PaymentOrderService;
import api.pay.single.service.PaymentRefMergeService;
import api.pay.single.service.PaymentService;

public class ZiranPayLogic {


  private static Logger log = LoggerFactory.getLogger(ZiranPayLogic.class);

  private static PaymentService paymentService;
  public void setPaymentService(PaymentService paymentService) {
    ZiranPayLogic.paymentService = paymentService;
  }

  private static PaymentLogService paymentLogService;
  public void setPaymentLogService(PaymentLogService paymentLogService) {
    ZiranPayLogic.paymentLogService = paymentLogService;
  }

  private static PaymentOrderService paymentOrderService;
  public void setPaymentOrderService(PaymentOrderService paymentOrderService) {
    ZiranPayLogic.paymentOrderService = paymentOrderService;
  }
  
  private static MergePaymentService mergePaymentService;
  public void setMergePaymentService(MergePaymentService mergePaymentService) {
    ZiranPayLogic.mergePaymentService = mergePaymentService;
  }
  
  private static PaymentRefMergeService paymentRefMergeService;
  public void setPaymentRefMergeService(PaymentRefMergeService paymentRefMergeService) {
    ZiranPayLogic.paymentRefMergeService = paymentRefMergeService;
  }
  
  /**
   * 支付业务逻辑
   * @param dto
   * @param gateway
   * @return 返回合并支付单id
   */
  public static ResData<String> payLogic(PayReqDto dto,PaymentGatewayResDto gateway){
    
    ResData<String> _res=new ResData<>();
    _res.setCode("fail");
    
    List<PaymentResDto> paymentResDtoList = new ArrayList<PaymentResDto>();
    PaymentResDto paymentResDto = null;
    int i = 0;//确定 重复请求支付,支付单一致性验证 是哪一个子项
    int totalFee = 0;// 总金额(分)
    Long timeOut = 4070880000000l;// 超时时间(毫秒) 2099-01-01 00:00:00
    
    for (PayItemReqDto itemDto : dto.getPayItemReqList()) {
      //
      totalFee += itemDto.getTotalFee();
      if (timeOut > itemDto.getTimeOut().getTime()) {
        timeOut = itemDto.getTimeOut().getTime();
      }
      i++;
      
      //
      paymentResDto = paymentService.findByOrderId(itemDto.getOrderNo());
      if (null != paymentResDto) {
        log.debug("订单号:{}已存在,对应支付单:{}", itemDto.getOrderNo(), JSON.toJSONString(paymentResDto));
        _res = PayReqDtoValidate.inParamChangeValidate(itemDto, paymentResDto, dto, --i);
        if(_res.getCode().equals("fail")){
          return _res;
        }

        // 更新网关
        EditPaymentReqDto editPaymentReqDto = new EditPaymentReqDto();
        editPaymentReqDto.setPaymentId(paymentResDto.getPaymentId());
        //
        if(!StringUtils.equalsIgnoreCase(paymentResDto.getBankCode(), dto.getBankCode())){
          editPaymentReqDto.setBankCode(dto.getBankCode());
          paymentResDto.setBankCode(dto.getBankCode());
        }
        if(!StringUtils.equalsIgnoreCase(paymentResDto.getFromIp(),dto.getFromIp())){
          editPaymentReqDto.setFromIp(dto.getFromIp());
          paymentResDto.setFromIp(dto.getFromIp());
        }
        if (!StringUtils.equalsIgnoreCase(paymentResDto.getGatewayId(),gateway.getGatewayId())) {
          editPaymentReqDto.setGatewayId(gateway.getGatewayId());
          paymentResDto.setGatewayId(gateway.getGatewayId());
        }
        paymentService.editById(editPaymentReqDto);
        
        //
        paymentResDtoList.add(paymentResDto);
      } else {
        // 保存支付单
        log.debug("1.保存支付单...");
        CreatePaymentReqDto createPaymentReqDto = new CreatePaymentReqDto();
        createPaymentReqDto.setMerchantId(dto.getMerchantId());
        createPaymentReqDto.setBankCode(dto.getBankCode());
        createPaymentReqDto.setExtraCommonParam(itemDto.getExtraCommonParam());
        createPaymentReqDto.setFromIp(dto.getFromIp());
        createPaymentReqDto.setNotifyUrl(dto.getNotifyUrl());
        createPaymentReqDto.setPaymentSource(dto.getPaymentSource());
        createPaymentReqDto.setReturnUrl(dto.getReturnUrl());
        createPaymentReqDto.setSpDetail(itemDto.getSpDetail());
        createPaymentReqDto.setSpName(itemDto.getSpName());
        createPaymentReqDto.setTimeOut(itemDto.getTimeOut());
        createPaymentReqDto.setTotalFee(itemDto.getTotalFee());
        createPaymentReqDto.setGatewayId(gateway.getGatewayId());
        String paymentId = paymentService.saveOne(createPaymentReqDto);
        
        // 保存订单ref支付单
        log.debug("2.保存订单ref支付单...");
        CreatePaymentOrderReqDto createPaymentOrderReqDto = new CreatePaymentOrderReqDto();
        createPaymentOrderReqDto.setOrderId(itemDto.getOrderNo());
        createPaymentOrderReqDto.setPaymentId(paymentId);
        paymentOrderService.saveOne(createPaymentOrderReqDto);

        // 保存支付单状态日志
        log.debug("3.保存支付单状态日志...");
        CreatePaymentLogReqDto createPaymentLogReqDto = new CreatePaymentLogReqDto();
        createPaymentLogReqDto.setPaymentId(paymentId);
        createPaymentLogReqDto.setPaymentStateId(PaymentStateConst.TRADE_CREATE);
        paymentLogService.saveOne(createPaymentLogReqDto);
        
        /////////////////////////////////////////////////////////////////
        // 支付单
        paymentResDto = new PaymentResDto();
        paymentResDto.setMerchantId(dto.getMerchantId());
        paymentResDto.setBankCode(dto.getBankCode());
        paymentResDto.setExtraCommonParam(itemDto.getExtraCommonParam());
        paymentResDto.setFromIp(dto.getFromIp());
        paymentResDto.setNotifyUrl(dto.getNotifyUrl());
        paymentResDto.setPaymentSource(dto.getPaymentSource());
        paymentResDto.setReturnUrl(dto.getReturnUrl());
        paymentResDto.setSpDetail(itemDto.getSpDetail());
        paymentResDto.setSpName(itemDto.getSpName());
        paymentResDto.setTimeOut(itemDto.getTimeOut());
        paymentResDto.setTotalFee(itemDto.getTotalFee());
        paymentResDto.setPaymentId(paymentId);
        paymentResDto.setPaymentStateId(PaymentStateConst.TRADE_CREATE);
        paymentResDto.setGatewayId(gateway.getGatewayId());

        paymentResDtoList.add(paymentResDto);
      }
    }
    
    // 保存合并支付单
    log.debug("4.保存合并支付单...");
    CreateMergePaymentReqDto createMergePaymentReqDto = new CreateMergePaymentReqDto();
    createMergePaymentReqDto.setMerchantId(dto.getMerchantId());
    createMergePaymentReqDto.setGatewayId(gateway.getGatewayId());
    createMergePaymentReqDto.setTotalFee(totalFee);
    createMergePaymentReqDto.setReturnUrl(dto.getReturnUrl());
    createMergePaymentReqDto.setNotifyUrl(dto.getNotifyUrl());
    createMergePaymentReqDto.setBankCode(dto.getBankCode());
    createMergePaymentReqDto.setTimeOut(new Date(timeOut));
    String mergePaymentId=mergePaymentService.saveOne(createMergePaymentReqDto);
    
    // 保存合并支付单ref支付单
    log.debug("5.保存合并支付单ref支付单...");
    CreatePaymentRefMergeReqDto createPaymentRefMergeReqDto = null;
    for (PaymentResDto pdto : paymentResDtoList) {
      createPaymentRefMergeReqDto = new CreatePaymentRefMergeReqDto();
      createPaymentRefMergeReqDto.setMergePaymentId(mergePaymentId);
      createPaymentRefMergeReqDto.setPaymentId(pdto.getPaymentId());
      paymentRefMergeService.saveOne(createPaymentRefMergeReqDto);
    }
    
    _res.setCode("success");
    _res.setT(mergePaymentId);
    return _res;
  }
}
