package api.pay.single.service;

import api.pay.single.dto.res.PaymentGatewayResDto;
import api.pay.utils.ServiceException;

public interface PaymentGatewayService {

	/**
	 * 根据id查询
	 * 
	 * @param gatewayId
	 * @return
	 * @throws ServiceException
	 */
	PaymentGatewayResDto findById(String gatewayId) throws ServiceException;

	/**
	 * 根据网关编码和商户id查询
	 * 
	 * @param merchantId
	 * @param gatewayCode
	 *            com.pay.consts.GatewayCodeConst
	 * @return
	 * @throws ServiceException
	 */
	PaymentGatewayResDto findByCodeAndMerchantId(String merchantId, String gatewayCode) throws ServiceException;
}
