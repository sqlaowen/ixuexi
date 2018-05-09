package com.laowen.service.impl;

import com.laowen.mapper.RefUserRoleMapper;
import com.laowen.service.RefUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RefUserRoleServiceImpl implements RefUserRoleService {

    @Autowired
    private RefUserRoleMapper refUserRoleMapper;

    @Override
    public int delByUserId(String userId) {
        return refUserRoleMapper.delByUserId(userId);
    }

    @Override
    public int saveBatch(String userId, List<String> roleIdList) {
        refUserRoleMapper.delByUserId(userId);

        List<Map<String, Object>> mapList = new ArrayList<>();
        Map<String, Object> map = null;
        for (String str : roleIdList) {
            map = new HashMap<>();
            map.put("refId", UUID.randomUUID().toString().replaceAll("-", ""));
            map.put("userId", userId);
            map.put("roleId", str);
            mapList.add(map);
        }
        return refUserRoleMapper.saveBatch(mapList);
    }

}