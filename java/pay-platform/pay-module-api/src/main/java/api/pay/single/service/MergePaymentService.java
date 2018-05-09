package api.pay.single.service;

import java.util.List;

import api.pay.single.dto.req.mergepayment.CreateMergePaymentReqDto;
import api.pay.single.dto.req.mergepayment.EditMergePaymentReqDto;
import api.pay.single.dto.res.MergePaymentResDto;
import api.pay.utils.ServiceException;

public interface MergePaymentService {

  /**
   * 保存
   * 
   * @param dto CreateMergePaymentReqDto
   * @return
   * @throws ServiceException
   */
  String saveOne(CreateMergePaymentReqDto dto) throws ServiceException;

  /**
   * 修改
   * 
   * @param dto EditMergePaymentReqDto
   * @return
   * @throws ServiceException
   */
  int editById(EditMergePaymentReqDto dto) throws ServiceException;

  /**
   * 根据id查找
   * 
   * @param mergePaymentId
   * @return
   * @throws ServiceException
   */
  MergePaymentResDto findById(String mergePaymentId) throws ServiceException;

  /**
   * 根据paymentId查找
   * 
   * @param paymentId
   * @return
   * @throws ServiceException
   */
  List<MergePaymentResDto> findByPaymentId(String paymentId) throws ServiceException;
  
  /**
   * 主动查询
   * 
   * @return
   * @throws ServiceException
   */
  List<MergePaymentResDto> findNoPay() throws ServiceException;
}
