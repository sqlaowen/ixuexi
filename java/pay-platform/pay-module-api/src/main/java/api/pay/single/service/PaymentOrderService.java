package api.pay.single.service;

import api.pay.single.dto.req.CreatePaymentOrderReqDto;
import api.pay.utils.ServiceException;

public interface PaymentOrderService {

  /**
   * 保存
   * 
   * @param dto CreatePaymentOrderReqDto
   * @return
   * @throws ServiceException
   */
  String saveOne(CreatePaymentOrderReqDto dto) throws ServiceException;
}
