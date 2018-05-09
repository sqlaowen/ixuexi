package api.pay.single.service;

import api.pay.single.dto.req.CreatePaymentCloseReqDto;
import api.pay.utils.ServiceException;

public interface PaymentCloseService {

  /**
   * 保存
   * 
   * @param dto CreatePaymentCloseReqDto
   * @return
   * @throws ServiceException
   */
  String saveOne(CreatePaymentCloseReqDto dto) throws ServiceException;
}
