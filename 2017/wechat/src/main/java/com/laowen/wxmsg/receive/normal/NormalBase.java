package com.laowen.wxmsg.receive.normal;

import com.laowen.wxmsg.receive.MsgBase;

import java.io.Serializable;

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
