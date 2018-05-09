package api.pay.combine.service;

import java.util.Map;

import api.pay.combine.dto.PayReqDto;
import api.pay.combine.dto.ResData;
import api.pay.utils.ServiceException;

public interface ZiranPayService {

	/**
	 * 支付 code:[SUCCESS:成功; FAIL:失败] 
	 * msg:提示信息 
	 * T:请求支付真实返回
	 * 
	 * @param dto
	 *            MergePayReqDto
	 * @return
	 * @throws ServiceException
	 */
	ResData<String> pay(PayReqDto dto) throws ServiceException;

	/**
	 * 异步通知
	 * 
	 * @param map
	 * @throws ServiceException
	 */
	Boolean asyncNotify(Map<String, String> map) throws ServiceException;

	/**
	 * 同步通知
	 * 
	 * @param map
	 * @return 要跳转到的url
	 * @throws ServiceException
	 */
	String syncNotify(Map<String, String> map) throws ServiceException;

	/**
	 * 主动查询
	 * 
	 * @throws ServiceException
	 */
	void activeQuery() throws ServiceException;
}
