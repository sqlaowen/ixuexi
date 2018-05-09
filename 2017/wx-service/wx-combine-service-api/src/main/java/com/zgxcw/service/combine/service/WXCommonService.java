package com.zgxcw.service.combine.service;

import com.zgxcw.framework.service.ServiceException;
import com.zgxcw.service.combine.dto.request.SendBankMsgTempleteDto;
import com.zgxcw.service.combine.dto.request.SendBankMsgTempleteRequest;
import com.zgxcw.service.combine.dto.request.SendMsgTempleteRequest;
import com.zgxcw.service.infrastructure.dto.response.WXResponse;

public interface WXCommonService {

  /**
   * 获取accessToken
   * @param wxId :微信网关id
   * @return
   * @throws ServiceException
   */
  String getAccessToken(String wxId) throws ServiceException;
  
  /**
   * 获取所有模板消息
   * @param wxId
   * @throws ServiceException
   */
  void getAllTemplete(String wxId) throws ServiceException;
  
  /**
   * 发送模板消息
   * @param msgRequest
   * @return
   * @throws ServiceException
   */
  WXResponse<String> sendTempMsg(SendMsgTempleteRequest msgRequest) throws ServiceException;
  
  /**
   * 发送银行卡模板消息
   * @param msgRequest
   * @return
   * @throws ServiceException
   */
  WXResponse<String> sendBankTempMsg(SendBankMsgTempleteRequest msgRequest) throws ServiceException;
  
  /**
   * 发送银行卡模板消息到队列
   * @param msgDto
   * @return
   * @throws ServiceException
   */
  WXResponse<String> sendBankTempMsg2MQ(SendBankMsgTempleteDto msgDto) throws ServiceException;
}
