package com.pay.metaq;

import com.alibaba.fastjson.JSON;
import com.taobao.metamorphosis.client.extension.spring.DefaultMessageListener;
import com.taobao.metamorphosis.client.extension.spring.MetaqMessage;

public class MetaqTestListener extends DefaultMessageListener<String> {

  @Override
  public void onReceiveMessages(MetaqMessage<String> msg) {
    String y = msg.getBody();
    System.out.println(JSON.toJSONString(msg));
    System.out.println(y);
  }

}
