package com.zgxcw.infrastructure;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zgxcw.service.infrastructure.dto.request.WxMsgTemplete.CreateWxMsgTempleteRequest;
import com.zgxcw.service.infrastructure.dto.response.WxMsgTempleteResponse;
import com.zgxcw.service.infrastructure.service.WxMsgTempleteService;

/**
 * Created by huolh on 2016/7/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/notenv/applicationContext.xml")
public class WxMsgTempleteServiceTest {

    @Autowired
    private WxMsgTempleteService wxMsgTempleteService;

    @Test
    public void test01() {
        WxMsgTempleteResponse wxMsgTempleteResponse = wxMsgTempleteService.findById("TempId");
        System.out.println(wxMsgTempleteResponse);
    }

    @Test
    public void test02() {
        CreateWxMsgTempleteRequest request = new CreateWxMsgTempleteRequest();
        request.setTempId("TempId");
        request.setTempTitle("TempTitle");
        request.setTempContent("TempContent");
        request.setIndustry1("Industry1");
        request.setIndustry2("Industry2");
        wxMsgTempleteService.saveOne(request);
    }
}
