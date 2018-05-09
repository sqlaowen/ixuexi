package com.laowen.mapper;

import com.laowen.entity.SysUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserMapper {

    SysUser findById(String roleId);

    SysUser findByUserAccount(String userAccount);

    List<SysUser> findList(SysUser record);

    int save(SysUser record);

    int editById(SysUser record);

}
