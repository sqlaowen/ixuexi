package api.authc.single.service;

import api.authc.single.dto.PageReq;
import api.authc.single.dto.PageRes;
import api.authc.single.dto.req.user.CreateSysUserReqDto;
import api.authc.single.dto.req.user.FindSysUserReqDto;
import api.authc.single.dto.req.user.UpdateSysUserReqDto;
import api.authc.single.dto.res.SysUserResDto;

public interface SysUserService {

  /**
   * 根据id查询
   * 
   * @param roleId
   * @return
   */
  SysUserResDto findById(String roleId);

  /**
   * 根据userAccount查询
   * 
   * @param userAccount
   * @return
   */
  SysUserResDto findByUserAccount(String userAccount);

  /**
   * 分页查询
   * 
   * @param req
   * @return
   */
  PageRes<SysUserResDto> findList(PageReq<FindSysUserReqDto> req);

  /**
   * 保存
   * 
   * @param record
   * @return
   */
  String save(CreateSysUserReqDto record);

  /**
   * 更新
   * 
   * @param record
   * @return
   */
  int editById(UpdateSysUserReqDto record);
}
