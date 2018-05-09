package api.pay.single.service;

import com.pay.ex.ServiceException;

import api.pay.single.dto.req.CreateAliReturnReqDto;

public interface AliReturnService {

    int saveOne(CreateAliReturnReqDto dto) throws ServiceException;
}