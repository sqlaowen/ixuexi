package com.laowen.wxmsg.receive.event;

import com.laowen.wxmsg.receive.MsgBase;

import java.io.Serializable;

public abstract class EventBase extends MsgBase implements Serializable {

    private static final long serialVersionUID = -8999632964201360047L;
    private String event;// 事件类型，订阅:subscribe, 取消订阅:unsubscribe, 扫描二维码:subscribe, 已关注:SCAN,
    // 上报地理位置:LOCATION, 自定义菜单:CLICK, 点击链接:VIEW,

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

}
