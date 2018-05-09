package com.laowen.service;

import java.util.List;

public interface RefUserRoleService {

    /**
     * 删除
     *
     * @param userId
     * @return
     */
    int delByUserId(String userId);

    /**
     * 批量保存
     *
     * @param userId
     * @param roleIdList
     * @return
     */
    int saveBatch(String userId, List<String> roleIdList);
}
