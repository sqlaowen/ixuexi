package api.pay.single.service;

import api.pay.single.dto.req.CreatePaymentReqLogReqDto;
import api.pay.utils.ServiceException;

public interface PaymentReqLogService {

	/**
	 * 保存
	 * 
	 * @param createPaymentReqLogReqDto
	 * @return
	 * @throws ServiceException
	 */
	String saveOne(CreatePaymentReqLogReqDto createPaymentReqLogReqDto) throws ServiceException;
}
