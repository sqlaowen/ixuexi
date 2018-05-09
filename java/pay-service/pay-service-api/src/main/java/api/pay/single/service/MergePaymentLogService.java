package api.pay.single.service;

import api.pay.single.dto.req.CreateMergePaymentLogReqDto;
import api.pay.utils.ServiceException;

public interface MergePaymentLogService {

	/**
	 * 保存
	 * 
	 * @param dto
	 *            CreateMergePaymentLogReqDto
	 * @return
	 * @throws ServiceException
	 */
	String saveOne(CreateMergePaymentLogReqDto dto) throws ServiceException;
}
