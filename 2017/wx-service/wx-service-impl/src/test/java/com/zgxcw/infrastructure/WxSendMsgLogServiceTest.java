package com.zgxcw.infrastructure;

import com.zgxcw.service.infrastructure.dto.request.WxSendMsgLog.CreateWxSendMsgLogRequest;
import com.zgxcw.service.infrastructure.service.WxSendMsgLogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by huolh on 2016/7/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/notenv/applicationContext.xml")
public class WxSendMsgLogServiceTest {
    @Autowired
    private WxSendMsgLogService wxSendMsgLogService;

    @Test
    public void test01() {

        CreateWxSendMsgLogRequest request = new CreateWxSendMsgLogRequest();
        request.setGhid("Ghid");
        request.setMsgContent("MsgContent");
        request.setMsgType("MsgType");
        request.setOpenid("Openid");
        request.setCreateTime(System.currentTimeMillis());
        request.setCreateUser("CreateUser");
        wxSendMsgLogService.saveOne(request);
    }

}
