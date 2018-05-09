package com.laowen.service;

import com.laowen.bean.FansRefCard;
import com.laowen.bean.domain.CMOIT2003Dto;
import com.laowen.dao.FansRefCardMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by iyou on 2017/5/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:notenv/applicationContext.xml"})
public class FansRefCardMapperTest {

    @Autowired
    private FansRefCardMapper internetCardMapper;

    @Test
    public void xx() {
        CMOIT2003Dto cmoit2003Dto1 = new CMOIT2003Dto();
        cmoit2003Dto1.setImsi("1");

        CMOIT2003Dto cmoit2003Dto2 = new CMOIT2003Dto();
        cmoit2003Dto2.setIccid("2");
        cmoit2003Dto2.setMsisdn("2");

        BeanUtils.copyProperties(cmoit2003Dto2, cmoit2003Dto1);
        System.out.println(cmoit2003Dto1);
        System.out.println(cmoit2003Dto2);

    }

    @Test
    public void test01(){
        FansRefCard internetCard = internetCardMapper.findByOpenIdAndCmoit2003("1", new CMOIT2003Dto() {{
            setMsisdn("1");
            setIccid("2");
            //setImsi("3");
        }});
        System.out.println(internetCard);
    }

    @Test
    public void test02(){
        List<FansRefCard> internetCardList = internetCardMapper.findByOpenId("1");
        System.out.println(internetCardList);
    }

}
