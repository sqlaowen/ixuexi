package com.pay.single.dao;

import com.pay.single.bean.PaymentState;

public interface PaymentStateMapper {

  PaymentState findById(Integer paymentStateId);
}
