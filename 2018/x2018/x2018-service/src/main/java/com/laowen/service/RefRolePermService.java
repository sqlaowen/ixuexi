package com.laowen.service;

import java.util.List;

/**
 * 角色ref权限
 *
 * @author wensw
 */
public interface RefRolePermService {

    /**
     * 删除
     *
     * @param roleId
     * @return
     */
    int delByRoleId(String roleId);

    /**
     * 批量保存
     *
     * @param roleId
     * @param permIdList
     * @return
     */
    int saveBatch(String roleId, List<String> permIdList);
}
