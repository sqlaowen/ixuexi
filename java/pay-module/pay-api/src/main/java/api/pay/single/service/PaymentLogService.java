package api.pay.single.service;

import com.pay.ex.ServiceException;

import api.pay.single.dto.req.CreatePaymentLogReqDto;

public interface PaymentLogService {

  int saveOne(CreatePaymentLogReqDto dto) throws ServiceException;
}
