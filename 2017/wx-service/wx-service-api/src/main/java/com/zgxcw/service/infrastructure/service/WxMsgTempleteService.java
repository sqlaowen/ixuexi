package com.zgxcw.service.infrastructure.service;

import com.zgxcw.framework.service.ServiceException;
import com.zgxcw.service.infrastructure.dto.request.WxMsgTemplete.CreateWxMsgTempleteRequest;
import com.zgxcw.service.infrastructure.dto.response.WxMsgTempleteResponse;

/**
 * Created by huolh on 2016/7/26.
 */
public interface WxMsgTempleteService {
    /**
     * 根据id查询
     *
     * @param tempId :tempId
     * @return WxMsgTempleteResponse
     * @throws ServiceException
     */
    WxMsgTempleteResponse findById(String tempId) throws ServiceException;

    /**
     * 保存
     *
     * @param request :CreateWxMsgTempleteRequest
     * @return
     * @throws ServiceException
     */
    String saveOne(CreateWxMsgTempleteRequest request) throws ServiceException;
}
