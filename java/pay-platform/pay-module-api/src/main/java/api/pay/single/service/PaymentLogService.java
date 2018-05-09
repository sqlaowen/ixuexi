package api.pay.single.service;

import api.pay.single.dto.req.CreatePaymentLogReqDto;
import api.pay.utils.ServiceException;

public interface PaymentLogService {

  /**
   * 保存
   * 
   * @param dto CreatePaymentLogReqDto
   * @return
   * @throws ServiceException
   */
  String saveOne(CreatePaymentLogReqDto dto) throws ServiceException;
}
