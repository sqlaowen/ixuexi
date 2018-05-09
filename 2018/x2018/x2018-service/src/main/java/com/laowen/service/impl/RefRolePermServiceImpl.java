package com.laowen.service.impl;

import com.laowen.mapper.RefRolePermMapper;
import com.laowen.service.RefRolePermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RefRolePermServiceImpl implements RefRolePermService {

    @Autowired
    private RefRolePermMapper refRolePermMapper;

    @Override
    public int delByRoleId(String roleId) {
        return refRolePermMapper.delByRoleId(roleId);
    }

    @Override
    public int saveBatch(String roleId, List<String> permIdList) {
        refRolePermMapper.delByRoleId(roleId);

        List<Map<String, Object>> mapList = new ArrayList<>();
        Map<String, Object> map = null;
        for (String str : permIdList) {
            map = new HashMap<>();
            map.put("refId", UUID.randomUUID().toString().replaceAll("-", ""));
            map.put("roleId", roleId);
            map.put("permId", str);
            mapList.add(map);
        }
        return refRolePermMapper.saveBatch(mapList);
    }

}