package api.pay.single.service;

import api.pay.single.dto.req.CreateAliNotifyReqDto;
import api.pay.utils.ServiceException;

public interface AliNotifyService {

  /**
   * 保存
   * 
   * @param dto CreateAliNotifyReqDto
   * @return
   * @throws ServiceException
   */
  String saveOne(CreateAliNotifyReqDto dto) throws ServiceException;
}
