package api.authc.single.service;

import java.util.List;

import api.authc.single.dto.PageReq;
import api.authc.single.dto.PageRes;
import api.authc.single.dto.req.role.CreateSysRoleReqDto;
import api.authc.single.dto.req.role.FindSysRoleReqDto;
import api.authc.single.dto.req.role.UpdateSysRoleReqDto;
import api.authc.single.dto.res.SysRoleResDto;

public interface SysRoleService {

  /**
   * 根据id查询
   * 
   * @param roleId
   * @return
   */
  SysRoleResDto findById(String roleId);

  /**
   * 分页查询
   * 
   * @param req
   * @return
   */
  PageRes<SysRoleResDto> findList(PageReq<FindSysRoleReqDto> req);

  /**
   * 通过用户id查询角色list
   * 
   * @param userId
   * @return
   */
  List<SysRoleResDto> findListByUserId(String userId);

  /**
   * 保存
   * 
   * @param record
   * @return
   */
  String save(CreateSysRoleReqDto record);

  /**
   * 更新
   * 
   * @param record
   * @return
   */
  int editById(UpdateSysRoleReqDto record);
}
