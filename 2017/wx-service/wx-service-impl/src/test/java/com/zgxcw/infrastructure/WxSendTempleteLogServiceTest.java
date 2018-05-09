package com.zgxcw.infrastructure;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zgxcw.service.infrastructure.dto.request.WxSendTempleteLog.CreateWxSendTempleteLogRequest;
import com.zgxcw.service.infrastructure.service.WxSendTempleteLogService;

/**
 * Created by huolh on 2016/7/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/notenv/applicationContext.xml")
public class WxSendTempleteLogServiceTest {
  @Autowired
  private WxSendTempleteLogService wxSendTempleteLogService;

  @Test
  public void test01() {
    CreateWxSendTempleteLogRequest request = new CreateWxSendTempleteLogRequest();
    request.setOpenid("Openid");
    request.setContent("Content");
    request.setPreFill("PreFill");
    request.setTempId("TempId");
    request.setCreateTime(System.currentTimeMillis());
    request.setCreateUser("CreateUser");
    wxSendTempleteLogService.saveOne(request);
  }
}
