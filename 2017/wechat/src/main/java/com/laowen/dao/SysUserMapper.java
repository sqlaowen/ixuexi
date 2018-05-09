package com.laowen.dao;

import com.laowen.bean.SysUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserMapper {

    SysUser findById(String userId);

    SysUser findByUserAccount(String userAccount);

    List<SysUser> findList(SysUser sysUser);

    int save(SysUser sysUser);

    int editById(SysUser sysUser);

}
