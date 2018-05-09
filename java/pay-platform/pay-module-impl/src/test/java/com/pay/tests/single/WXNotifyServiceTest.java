package com.pay.tests.single;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import api.pay.single.dto.req.CreateWXNotifyReqDto;
import api.pay.single.service.WXNotifyService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/notenv/applicationContext.xml")
public class WXNotifyServiceTest {

  @Autowired
  private WXNotifyService wxNotifyService;

  @Test
  public void test01() {
    CreateWXNotifyReqDto dto = new CreateWXNotifyReqDto();
    dto.setAppid("appid");
    String id = wxNotifyService.saveOne(dto);
    System.out.println(id);
  }
}
