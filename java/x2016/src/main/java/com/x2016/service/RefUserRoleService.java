package com.x2016.service;

import java.util.List;

public interface RefUserRoleService {

  int delByUserId(String userId);

  int saveBatch(String userId,List<String> roleIdList);
}