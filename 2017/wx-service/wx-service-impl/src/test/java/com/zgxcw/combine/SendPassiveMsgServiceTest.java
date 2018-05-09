package com.zgxcw.combine;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zgxcw.service.combine.service.SendPassiveMsgService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/notenv/applicationContext.xml")
public class SendPassiveMsgServiceTest {

  @Autowired
  private SendPassiveMsgService sendPassiveMsgService;
  
  //自动回复
  @Test
  public void test01(){
    String reqXml="<xml><ToUserName><![CDATA[gh_d306845639cb]]></ToUserName><FromUserName><![CDATA[oFKMnwzhv83wK79L1rwfHbNniPkg]]></FromUserName><CreateTime>1456801510</CreateTime><MsgType><![CDATA[image]]></MsgType><PicUrl><![CDATA[http://mmbiz.qpic.cn/mmbiz/FnhxsdcPcp0fdF2micq3YjIOYQrCiav1VIqBb4l8NtLdq23PSRgF2w17GjKnwEyficJCwdWmHMCFNovrGQwRweceA/0]]></PicUrl><MsgId>6256914842615575786</MsgId><MediaId><![CDATA[pxIxKQa5FdFX0apO9KA6UqXJ9Leivi-i27kp91G_3IkOkHK5vn0HcX4qvNh-lVsa]]></MediaId></xml>";
    String sendXml=sendPassiveMsgService.autoAnswer(reqXml,"100");
    System.out.println(sendXml);
  }
}
