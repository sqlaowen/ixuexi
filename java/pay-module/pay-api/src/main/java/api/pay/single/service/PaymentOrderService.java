package api.pay.single.service;

import com.pay.ex.ServiceException;

import api.pay.single.dto.req.CreatePaymentOrderReqDto;

public interface PaymentOrderService {

    int saveOne(CreatePaymentOrderReqDto dto) throws ServiceException;
}