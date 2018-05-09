package com.zgxcw.service.messge.receive.normal;

import java.io.Serializable;

import com.zgxcw.service.messge.receive.MsgBase;

public abstract class NormalBase extends MsgBase implements Serializable {

  private static final long serialVersionUID = 7444122880446372560L;
  private String msgId;// 消息id，64位整型

  public String getMsgId() {
    return msgId;
  }

  public void setMsgId(String msgId) {
    this.msgId = msgId;
  }
}
