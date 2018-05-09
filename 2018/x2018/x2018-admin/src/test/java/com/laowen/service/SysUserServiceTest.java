package com.laowen.service;

import com.laowen.entity.SysUser;
import com.laowen.service.SysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class SysUserServiceTest {

    @Autowired
    private SysUserService sysUserService;

    @Test
    public void test01(){
        SysUser sysUser = sysUserService.findById("e2e904211f4311e6a2d6185e0f846dee");
        System.out.println("-------------------------------------------");
        System.out.println(sysUser);
    }

}
