package com.pay.single.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pay.single.bean.Payment;

public interface PaymentMapper {

  int saveOne(Payment record);
  
  Payment findById(String paymentId);
  
  //根据订单id查询支付单
  Payment findByOrderId(@Param("orderId") String orderId);

  //根据订单id或支付单id查询支付单 
  Payment findByPaymentIdOrderId(@Param("orderId") String orderId,@Param("paymentId") String paymentId);
  
  // 根据合并支付单id查询
  List<Payment> findByMergePayemntId(String mergePaymentId);
  
  int editById(Payment record);
}
