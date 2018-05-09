package com.laowen.mapper;

import com.laowen.entity.WXFans;
import com.laowen.mapper.WXFansMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class WXFansMapperTest {

    @Autowired
    private WXFansMapper wxFansMapper;

    @Test
    public void test01(){
        WXFans wxFans = wxFansMapper.findByOpenId("oFKMnwxNrzCTsV-Mr1N9OV3549Rk");
        System.out.println("-------------------------------------------");
        System.out.println(wxFans);
    }
}
