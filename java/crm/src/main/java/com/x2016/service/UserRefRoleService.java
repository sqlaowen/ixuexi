package com.x2016.service;

import java.util.List;

public interface UserRefRoleService {

  int delByUserId(String userId);

  int saveBatch(String userId,List<String> roleIdList);
}