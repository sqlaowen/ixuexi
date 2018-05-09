package com.laowen.service;

import com.laowen.bean.domain.ChinaMobileDto;
import com.laowen.utils.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by iyou on 2017/4/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:notenv/applicationContext.xml"})
public class ChinaMobileServiceTest {

    @Autowired
    private ChinaMobileService chinaMobileService;

    @Test
    public void cmiot_api2005(){
        Result<String> result = chinaMobileService.cmiot_api2005(new ChinaMobileDto() {{
            setMsisdn("1064803535019");
        }});
        System.out.println(result);
        System.out.println("================================");
    }

}
