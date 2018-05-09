package api.authc.single.service;

import java.util.List;

import api.authc.single.dto.req.perm.CreateSysPermissionReqDto;
import api.authc.single.dto.req.perm.FindSysPermissionReqDto;
import api.authc.single.dto.req.perm.UpdateSysPermissionReqDto;
import api.authc.single.dto.res.SysPermissionResDto;

public interface SysPermissionService {

  /**
   * 根据id取资源
   * 
   * @param permId
   * @return
   */
  SysPermissionResDto findById(String permId);

  /**
   * 取资源
   * 
   * @param record
   * @return
   */
  List<SysPermissionResDto> findList(FindSysPermissionReqDto record);

  /**
   * 取菜单
   * 
   * @return
   */
  List<SysPermissionResDto> findMenu();

  /**
   * 取左侧菜单树
   * 
   * @param userId
   * @return
   */
  List<SysPermissionResDto> findLeftTreeMenu(String userId);

  /**
   * 取权限
   * 
   * @param menuId
   * @return
   */
  List<SysPermissionResDto> findPermByMenuId(String menuId);

  /**
   * 根据角色id取资源
   * 
   * @param roleId
   * @return
   */
  List<SysPermissionResDto> findResourceByRoleId(String roleId);

  /**
   * 根据userId查询权限list
   * 
   * @param userId
   * @return
   */
  List<SysPermissionResDto> findListPermByUserId(String userId);

  /**
   * 保存
   * 
   * @param record
   * @return
   */
  String save(CreateSysPermissionReqDto record);

  /**
   * 更新
   * 
   * @param record
   * @return
   */
  int editById(UpdateSysPermissionReqDto record);
}
