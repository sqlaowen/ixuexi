package api.pay.single.service;

import api.pay.single.dto.req.CreateAliReturnReqDto;
import api.pay.utils.ServiceException;

public interface AliReturnService {

	/**
	 * 保存
	 * 
	 * @param dto
	 *            CreateAliReturnReqDto
	 * @return
	 * @throws ServiceException
	 */
	String saveOne(CreateAliReturnReqDto dto) throws ServiceException;
}
