package com.laowen.service;

import com.laowen.bean.SysUser;
import com.laowen.dao.SysUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by iyou on 2017/6/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:notenv/applicationContext.xml"})
public class SysUserMapperTest {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Test
    public void test01(){
        SysUser sysUser = sysUserMapper.findByUserAccount("admin");
        System.out.println(sysUser);
    }

}
