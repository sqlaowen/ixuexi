package com.zgxcw.service.infrastructure.service;

import java.util.Collection;

import com.zgxcw.framework.service.ServiceException;
import com.zgxcw.service.infrastructure.dto.request.wxbankrefopenid.CreateWXBankRefOpenidRequest;
import com.zgxcw.service.infrastructure.dto.response.WXBankRefOpenidResponse;

public interface WXBankRefOpenidService {

  /**
   * 保存
   * 
   * @param createWXBankRefOpenidRequest
   * @return
   * @throws ServiceException
   */
  String saveOne(CreateWXBankRefOpenidRequest createWXBankRefOpenidRequest) throws ServiceException;

  /**
   * 根据id查
   * 
   * @param refId
   * @return
   * @throws ServiceException
   */
  WXBankRefOpenidResponse findById(String refId) throws ServiceException;

  /**
   * 根据银行卡号查
   * 
   * @param bankNo
   * @param wxId
   * @return
   * @throws ServiceException
   */
  Collection<WXBankRefOpenidResponse> findByBankNo(String bankNo, String wxId) throws ServiceException;
  
  /**
   * 根据openid查
   * 
   * @param openid
   * @param wxId
   * @return
   * @throws ServiceException
   */
  WXBankRefOpenidResponse findByOpenid(String openid, String wxId) throws ServiceException;

  /**
   * 绑定或解绑银行卡
   * bankName 银行名称
   * bankNo != null 绑定银行卡
   * bankNo == null 解绑银行卡
   * @param bankNo
   * @param openid
   * @param wxId
   * @return
   * @throws ServiceException
   */
  int editBankNo(String bankName,String bankNo, String openid, String wxId) throws ServiceException;
}
