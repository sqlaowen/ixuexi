package api.pay.single.service;

import com.pay.ex.ServiceException;

import api.pay.single.dto.req.CreateAliNotifyReqDto;

public interface AliNotifyService {

    int saveOne(CreateAliNotifyReqDto dto) throws ServiceException;
}