package com.x2016.service;

import java.util.List;

public interface RefRolePermService {

    int delByRoleId(String roleId);

    int saveBatch(String roleId,List<String> permIdList);
}