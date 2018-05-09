package com.zgxcw.infrastructure;

import com.zgxcw.service.infrastructure.dto.request.WxReceiveMsg.CreateWxReceiveMsgRequest;
import com.zgxcw.service.infrastructure.dto.response.WxReceiveMsgResponse;
import com.zgxcw.service.infrastructure.service.WxReceiveMsgService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by huolh on 2016/7/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/notenv/applicationContext.xml")
public class WxReceiveMsgServiceTest {
    @Autowired
    private WxReceiveMsgService wxReceiveMsgService;


    @Test
    public void test01() {
        CreateWxReceiveMsgRequest request = new CreateWxReceiveMsgRequest();
        request.setToUserName("ToUserName");
        request.setFromUserName("FromUserName");
        request.setMsgType("MsgType");
        request.setMsgId("MsgId");
        request.setEvent("Event");
        request.setEventKey("EventKey");
        request.setTicket("Ticket");
        request.setLatitude("Latitude");
        request.setLongitude("Longitude");
        request.setPrecision("Precision");
        request.setPicUrl("PicUrl");
        request.setMediaId("MediaId");
        request.setThumbMediaId("ThumbMediaId");
        request.setTitle("Title");
        request.setDescription("Description");
        request.setUrl("Url");
        request.setLocationX("Location_X");
        request.setLocationY("Location_Y");
        request.setScale("Scale");
        request.setLabel("Label");
        request.setContent("Content");
        request.setFormat("Format");
        request.setRecognition("Recognition");
        request.setCreateTime(System.currentTimeMillis());
        request.setCreateUser("CreateUser");
        wxReceiveMsgService.saveOne(request);
    }

    @Test
    public void test02() {
        WxReceiveMsgResponse wxReceiveMsgResponse = wxReceiveMsgService.findById("533dc3bd77594808a65208024a515f65");
        System.out.println(wxReceiveMsgResponse);
    }
}
