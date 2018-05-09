package com.zgxcw.combine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.dubbo.common.json.ParseException;
import com.alibaba.fastjson.JSON;
import com.taobao.metamorphosis.client.extension.spring.MessageBuilder;
import com.taobao.metamorphosis.client.extension.spring.MetaqTemplate;
import com.taobao.metamorphosis.client.producer.SendResult;
import com.zgxcw.framework.service.ServiceException;
import com.zgxcw.service.combine.dto.request.SendBankMsgTempleteDto;
import com.zgxcw.service.combine.dto.request.SendBankMsgTempleteRequest;
import com.zgxcw.service.combine.dto.request.SendMsgTempleteRequest;
import com.zgxcw.service.combine.service.WXCommonService;
import com.zgxcw.service.infrastructure.dto.response.WXResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/notenv/applicationContext.xml")
public class WXCommonServiceTest {

  @Autowired
  private WXCommonService wxCommonService;

  // 同步获取所有模板消息
  @Test
  public void test01() {
    wxCommonService.getAllTemplete("zgxcw_2016");
    System.out.println("------------------");
  }

  // 发送模板消息
  @Test
  public void test02() {
    SendMsgTempleteRequest msgRequest = new SendMsgTempleteRequest();
    msgRequest.setWxId("zgxcw_2016");
    msgRequest.setTemplateId("VjITH3pL1ZlgYSv7-9o2oCFVBhjUhWiIAUFxXxPFOoM");
    msgRequest.setQiyeId("44813a3d58674f98a2698f85f3a97c15");
    msgRequest.setDetailUrl("http://www.baidu.com");
    List<String> fillContentList = new ArrayList<>();
    fillContentList.add("{\"color\":\"#25428\",\"value\":\"卡车100辆\"}");
    fillContentList.add("{\"color\":\"#25428\",\"value\":\"售后不退换,不保修\"}");
    fillContentList.add("{\"color\":\"#25428\",\"value\":\"售后不退换,不保修\"}");
    fillContentList.add("{\"color\":\"#25428\",\"value\":\"售后不退换,不保修\"}");
    msgRequest.setFillContentList(fillContentList);
    WXResponse<String> res = wxCommonService.sendTempMsg(msgRequest);
    System.out.println(JSON.toJSONString(res));
  }

  @Autowired
  private MetaqTemplate metaqTemplate;

  // 发送银行卡模板消息
  @Test
  public void test03() throws Exception {
    final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String line = null;
    while ((line = reader.readLine()) != null) {
      SendBankMsgTempleteRequest msgRequest = new SendBankMsgTempleteRequest();
      msgRequest.setWxId("zgxcw_2016");
      msgRequest.setTemplateId("w4CvN_R5XDSJL7xa3DEEHIxzy0DtSUxZhE5gnrBYnHA");
      msgRequest.setBankNo("62160000000000");
      //msgRequest.setDetailUrl("http://www.baidu.com");
      List<String> fillContentList = new ArrayList<>();
      fillContentList.add("{\"value\":\"卡车100辆\",\"color\":\"#25428\"}");
      fillContentList.add("{\"value\":\"售后不退换,不保修\",\"color\":\"#25428\"}");
      msgRequest.setFillContentList(fillContentList);
     String str=  JSON.toJSONString(msgRequest);
     System.out.println(str);
      SendResult res = metaqTemplate.send(MessageBuilder.withTopic("ODC_SENDWXTEMPMSG").withPayload(str.getBytes()));
      System.out.println(JSON.toJSONString(res));
    }
  }
  
  // ODC发送银行卡模板消息到队列
  @Test
  public void test04() throws Exception {
    final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String line = null;
    while ((line = reader.readLine()) != null) {
      SendBankMsgTempleteDto msgRequest = new SendBankMsgTempleteDto();
      msgRequest.setWxId("zgxcw_2016");
      msgRequest.setTemplateId("w4CvN_R5XDSJL7xa3DEEHIxzy0DtSUxZhE5gnrBYnHA");
      msgRequest.setBankNo("62160000000000");
      //msgRequest.setDetailUrl("http://www.baidu.com");
      List<String> fillContentList = new ArrayList<>();
      fillContentList.add("{\"value\":\"卡车100辆\",\"color\":\"#25428\"}");
      fillContentList.add("{\"value\":\"售后不退换,不保修\",\"color\":\"#25428\"}");
      msgRequest.setFillContentList(fillContentList);
      WXResponse<String> res= wxCommonService.sendBankTempMsg2MQ(msgRequest);
      System.out.println(res);
    }
  }
  
  @Test
  public void test05() throws ServiceException, IOException, ParseException{
    final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String line = null;
    while ((line = reader.readLine()) != null) {
      String str="{\"bankNo\":\"6222020200086854979\",\"detailUrl\":\"\",\"fillContentList\":[\"{\\\"color\\\":\\\"#25428\\\",\\\"value\\\":\\\"您好，您收到一笔诸葛修车网的打款信息，运单号[201608160051]\\\"}\",\"{\\\"color\\\":\\\"#25428\\\",\\\"value\\\":\\\"6222***********4979\\\"}\",\"{\\\"color\\\":\\\"#25428\\\",\\\"value\\\":\\\"95.00元(代收货款100.00元，代收手续费5.00元，应打款95.00元)\\\"}\",\"{\\\"color\\\":\\\"#25428\\\",\\\"value\\\":\\\"2016年8月16日\\\"}\",\"{\\\"color\\\":\\\"#25428\\\",\\\"value\\\":\\\"请您注意查收！\\\"}\"],\"templateId\":\"Oq0xQBenWf-l7j7Z-FiFLAnNkRhLzrULYtL283DZzOY\",\"wxId\":\"zgxcw_2016\"}";
      SendBankMsgTempleteRequest msgRequest= com.alibaba.dubbo.common.json.JSON.parse(str, SendBankMsgTempleteRequest.class);
      WXResponse<String> res= wxCommonService.sendBankTempMsg(msgRequest);
      System.out.println(res);
    }
  }
}
