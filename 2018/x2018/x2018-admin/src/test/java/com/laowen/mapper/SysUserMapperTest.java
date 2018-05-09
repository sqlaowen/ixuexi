package com.laowen.mapper;

import com.laowen.entity.SysUser;
import com.laowen.mapper.SysUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class SysUserMapperTest {

    @Autowired
    private SysUserMapper sysUserMapper;


    @Test
    public void test01(){
        SysUser sysUser = sysUserMapper.findById("e2e904211f4311e6a2d6185e0f846dee");
        System.out.println("-------------------------------------------");
        System.out.println(sysUser);
    }

}
