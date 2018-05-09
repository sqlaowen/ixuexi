package com.zgxcw.service.infrastructure.service;

import java.util.Collection;

import com.zgxcw.framework.service.ServiceException;
import com.zgxcw.service.infrastructure.dto.request.wxuserrefopenid.CreateWXUserRefOpenidRequest;
import com.zgxcw.service.infrastructure.dto.response.WXUserRefOpenidResponse;

public interface WXUserRefOpenidService {

  /**
   * 保存
   * 
   * @param createWXUserRefOpenidRequest
   * @return
   * @throws ServiceException
   */
  String saveOne(CreateWXUserRefOpenidRequest createWXUserRefOpenidRequest) throws ServiceException;

  /**
   * 根据id查询
   * 
   * @param refId 
   * @return
   * @throws ServiceException
   */
  WXUserRefOpenidResponse findById(String refId) throws ServiceException;

  /**
   * <!-- 根据用户id 或 企业id里查询 -->
   * 
   * @param wxId 网关id
   * @param qiyeId 企业id
   * @return
   * @throws ServiceException
   */
  Collection<WXUserRefOpenidResponse> findByAccountId(String wxId, String qiyeId) throws ServiceException;
}
