package api.pay.single.service;

import java.util.List;

import api.pay.single.dto.req.payment.CreatePaymentReqDto;
import api.pay.single.dto.req.payment.EditPaymentReqDto;
import api.pay.single.dto.res.PaymentResDto;
import api.pay.utils.ServiceException;

public interface PaymentService {

	/**
	 * 保存
	 * 
	 * @param dto
	 *            CreatePaymentReqDto
	 * @return
	 * @throws ServiceException
	 */
	String saveOne(CreatePaymentReqDto dto) throws ServiceException;

	/**
	 * 根据支付单id查询支付单
	 * 
	 * @param paymentId
	 * @return
	 * @throws ServiceException
	 */
	PaymentResDto findById(String paymentId) throws ServiceException;

	/**
	 * 根据订单id查询支付单
	 * 
	 * @param orderId
	 * @return
	 * @throws ServiceException
	 */
	PaymentResDto findByOrderId(String orderId) throws ServiceException;

	/**
	 * 根据订单id或支付单id查询支付单
	 * 
	 * @param orderId
	 * @param paymentId
	 * @return
	 * @throws ServiceException
	 */
	PaymentResDto findByPaymentIdOrderId(String orderId, String paymentId) throws ServiceException;

	/**
	 * 根据合并支付单查询支付单
	 * 
	 * @param mergePaymentId
	 * @return
	 * @throws ServiceException
	 */
	List<PaymentResDto> findByMergePaymentId(String mergePaymentId) throws ServiceException;
	
	/**
	 * 通过支付单id,商户号查询订单id
	 * 
	 * @param paymentId
	 * @param merchantId
	 * @return
	 * @throws ServiceException
	 */
	String findOrderIdByPaymentId(String paymentId, String merchantId) throws ServiceException;
	
	/**
	 * 修改
	 * 
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	int editById(EditPaymentReqDto dto) throws ServiceException;
}
