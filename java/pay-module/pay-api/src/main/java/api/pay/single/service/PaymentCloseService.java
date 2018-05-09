package api.pay.single.service;

import com.pay.ex.ServiceException;

import api.pay.single.dto.req.CreatePaymentCloseReqDto;

public interface PaymentCloseService {

    int saveOne(CreatePaymentCloseReqDto dto) throws ServiceException;
}