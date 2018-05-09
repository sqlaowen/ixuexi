package api.pay.single.service;

import api.pay.single.dto.req.CreateWXNotifyReqDto;
import api.pay.utils.ServiceException;

public interface WXNotifyService {

  /**
   * 保存
   * 
   * @param dto CreateWXNotifyReqDto
   * @return
   * @throws ServiceException
   */
  String saveOne(CreateWXNotifyReqDto dto) throws ServiceException;

}
