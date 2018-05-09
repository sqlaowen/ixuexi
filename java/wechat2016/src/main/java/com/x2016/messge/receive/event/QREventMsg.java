package com.x2016.messge.receive.event;

import java.io.Serializable;

/**
 * 扫描带参数二维码
 * event: 用户未关注时:subscribe, 用户已关注时:SCAN
 * 
 * @author wensw
 *
 */
public class QREventMsg extends EventBase implements Serializable {

  private static final long serialVersionUID = -5629820672324863303L;
  private String eventKey;// 事件KEY值，qrscene_为前缀，后面为二维码的参数值
  private String ticket;// 二维码的ticket，可用来换取二维码图片

  public String getEventKey() {
    return eventKey;
  }

  public void setEventKey(String eventKey) {
    this.eventKey = eventKey;
  }

  public String getTicket() {
    return ticket;
  }

  public void setTicket(String ticket) {
    this.ticket = ticket;
  }

}
