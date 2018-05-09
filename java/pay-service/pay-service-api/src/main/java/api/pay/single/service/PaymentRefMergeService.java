package api.pay.single.service;

import api.pay.single.dto.req.CreatePaymentRefMergeReqDto;
import api.pay.utils.ServiceException;

public interface PaymentRefMergeService {

	/**
	 * 保存
	 * 
	 * @param dto
	 *            CreatePaymentRefMergeReqDto
	 * @return
	 * @throws ServiceException
	 */
	String saveOne(CreatePaymentRefMergeReqDto dto) throws ServiceException;
}
