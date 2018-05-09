package com.zgxcw.service.messge.receive.event;

import java.io.Serializable;

/**
 * 自定义菜单事件 
 * event: 点击事件:CLICK, 链接事件:VIEW
 * 
 *
 */
public class CustomEventMsg extends EventBase implements Serializable {

  private static final long serialVersionUID = -6112056005897425802L;
  private String eventKey;// 事件KEY值，qrscene_为前缀，后面为二维码的参数值

  public String getEventKey() {
    return eventKey;
  }

  public void setEventKey(String eventKey) {
    this.eventKey = eventKey;
  }

}
