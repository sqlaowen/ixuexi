package api.pay.single.service;

import com.pay.ex.ServiceException;

import api.pay.single.dto.req.payment.CreatePaymentReqDto;
import api.pay.single.dto.req.payment.EditPaymentReqDto;
import api.pay.single.dto.res.PaymentResDto;

public interface PaymentService {

  String saveOne(CreatePaymentReqDto dto) throws ServiceException;
  
  PaymentResDto findById(String paymentId) throws ServiceException;
  
  //根据订单id查询支付单
  PaymentResDto findByOrderId(String orderId) throws ServiceException;

  //根据订单id或支付单id查询支付单 
  PaymentResDto findByPaymentIdOrderId(String orderId,String paymentId) throws ServiceException;
  
  int editById(EditPaymentReqDto dto) throws ServiceException;
}
