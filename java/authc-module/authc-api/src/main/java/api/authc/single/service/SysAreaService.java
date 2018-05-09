package api.authc.single.service;

import api.authc.single.dto.PageReq;
import api.authc.single.dto.PageRes;
import api.authc.single.dto.req.FindSysAreaReqDto;
import api.authc.single.dto.res.SysAreaResDto;

public interface SysAreaService {

  /**
   * 分页查找
   * @param req
   * @return
   */
  PageRes<SysAreaResDto> findList(PageReq<FindSysAreaReqDto> req);
}
