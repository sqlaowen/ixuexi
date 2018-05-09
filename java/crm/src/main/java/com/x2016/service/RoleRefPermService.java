package com.x2016.service;

import java.util.List;

public interface RoleRefPermService {

    int delByRoleId(String roleId);

    int saveBatch(String roleId,List<String> permIdList);
}