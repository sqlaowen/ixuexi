package com.zgxcw.service.combine.service;

import com.zgxcw.framework.service.ServiceException;

/**
 * 被动消息回复
 * @author wensw
 *
 */
public interface SendPassiveMsgService {

  /**
   * 自动回复(关注回复,输入回复,事件回复)
   * 
   * @param reqXml :微信转发来的xml请求
   * @param wxId :微信网关id
   * @return
   * @throws ServiceException
   */
  String autoAnswer(String reqXml,String wxId) throws ServiceException;

}
