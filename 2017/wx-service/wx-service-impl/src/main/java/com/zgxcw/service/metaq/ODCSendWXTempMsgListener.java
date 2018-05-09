package com.zgxcw.service.metaq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.json.ParseException;
import com.taobao.metamorphosis.client.extension.spring.DefaultMessageListener;
import com.taobao.metamorphosis.client.extension.spring.MetaqMessage;
import com.zgxcw.service.combine.dto.request.SendBankMsgTempleteRequest;
import com.zgxcw.service.combine.service.WXCommonService;

public class ODCSendWXTempMsgListener extends DefaultMessageListener<String> {

  private Logger log = LoggerFactory.getLogger(getClass());
  
  private WXCommonService wxCommonService;
  public void setWxCommonService(WXCommonService wxCommonService) {
    this.wxCommonService = wxCommonService;
  }
  
  @Override
  public void onReceiveMessages(MetaqMessage<String> msg) {
    log.debug("发送银行卡模板消息监听到的内容为:{}", msg.getBody());
    SendBankMsgTempleteRequest msgRequest = null;
    try {
      msgRequest = JSON.parse(msg.getBody(), SendBankMsgTempleteRequest.class);
    } catch (ParseException e) {
      log.error("发送银行卡模板消息监听解析json失败,对应消息:{},失败提示:{}", msg.getBody(), e.getMessage());
      e.printStackTrace();
    }
    if(null==msgRequest)
      return;
    wxCommonService.sendBankTempMsg(msgRequest);
  }

}
