package com.laowen.service.impl;

import com.github.pagehelper.PageHelper;
import com.laowen.PageRequest;
import com.laowen.PageResponse;
import com.laowen.entity.SysUser;
import com.laowen.mapper.SysUserMapper;
import com.laowen.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Transactional
    @Override
    public SysUser findById(String userId) {
        return sysUserMapper.findById(userId);
    }

    @Override
    public SysUser findByUserAccount(String userAccount) {
        return sysUserMapper.findByUserAccount(userAccount);
    }

    @Override
    public PageResponse<SysUser> findList(PageRequest<SysUser> req) {
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        List<SysUser> list = sysUserMapper.findList(req.getT());
        return new PageResponse<SysUser>(list);
    }

    @Override
    public String save(SysUser record) {

        String id = UUID.randomUUID().toString().replaceAll("-", "");
        record.setCreateTime(new Date());
        record.setUserId(id);
        sysUserMapper.save(record);
        return id;
    }

    @Override
    public int editById(SysUser record) {
        record.setUpdateTime(new Date());
        return sysUserMapper.editById(record);
    }

}
