package com.zgxcw.service.infrastructure.service;

import java.util.List;

import com.zgxcw.framework.service.ServiceException;
import com.zgxcw.service.infrastructure.dto.request.page.PageRequest;
import com.zgxcw.service.infrastructure.dto.request.wxautores.CreateWXAutoResRequest;
import com.zgxcw.service.infrastructure.dto.request.wxautores.QueryWxAutoResRequest;
import com.zgxcw.service.infrastructure.dto.request.wxautores.UpdateWXAutoResRequest;
import com.zgxcw.service.infrastructure.dto.response.PageResponse;
import com.zgxcw.service.infrastructure.dto.response.WXAutoResResponse;

public interface WXAutoResService {

  /**
   * 根据id查询
   * 
   * @param resId :消息id
   * @return WXAutoResResponse
   * @throws ServiceException
   */
  WXAutoResResponse findById(String resId) throws ServiceException;

  /**
   * 查询要使用的回复消息
   * 
   * @param msgType: 消息类型
   * @param wxId :微信网关id
   * @return
   * @throws ServiceException
   */
  WXAutoResResponse findTheSet(Integer msgType,String wxId) throws ServiceException;

  /**
   * 根据回复/消息类型查询
   * 
   * @param resType :回复类型(可空)
   * @param msgType :消息类型(可空)
   * @param wxId :微信网关id(不可空)
   * @return
   * @throws ServiceException
   */
  List<WXAutoResResponse> findByResOrMsgType(String resType, String msgType, String wxId) throws ServiceException;

  /**
   * 保存
   * 
   * @param request :CreateWXAutoResRequest
   * @return
   * @throws ServiceException
   */
  String saveOne(CreateWXAutoResRequest request) throws ServiceException;

  /**
   * 修改
   * 
   * @param request :UpdateWXAutoResRequest
   * @return 受影响行数
   * @throws ServiceException
   */
  int editById(UpdateWXAutoResRequest request) throws ServiceException;

  /**
   * 根据Id删除
   * @param resId
   * @return
   * @throws ServiceException
   */
  int deleteById(String resId) throws  ServiceException;

  /**
   * 将该条设置为启用回复,其它设置为不启用回复
   * 
   * @param resId
   * @param msgType
   * @param wxId
   * @throws ServiceException
   */
  void editSetEnable(String resId,String msgType, String wxId) throws ServiceException;

  /**
   * 分页查询
   * @param request
   * @return
   * @throws ServiceException
   */
  PageResponse<WXAutoResResponse> getPageList(PageRequest<QueryWxAutoResRequest> request) throws ServiceException;

}
