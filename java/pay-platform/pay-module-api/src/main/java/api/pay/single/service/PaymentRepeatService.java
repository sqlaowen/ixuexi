package api.pay.single.service;

import api.pay.single.dto.req.CreatePaymentRepeatReqDto;
import api.pay.utils.ServiceException;

public interface PaymentRepeatService {

  /**
   * 保存
   * 
   * @param dto CreatePaymentRepeatReqDto
   * @return
   * @throws ServiceException
   */
  String saveOne(CreatePaymentRepeatReqDto dto) throws ServiceException;
}
