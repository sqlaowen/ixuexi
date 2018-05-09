package com.pay.single.dao;

import java.util.List;

import com.pay.single.bean.MergePayment;

public interface MergePaymentMapper {

  int saveOne(MergePayment record);

  int editById(MergePayment record);

  //根据id查找
  MergePayment findById(String mergePaymentId);

  //根据paymentId查找
  List<MergePayment> findByPaymentId(String paymentId);
  
  //主动查询
  List<MergePayment> findNoPay();
}
