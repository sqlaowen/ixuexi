package com.laowen.wxmsg.receive.normal;

import java.io.Serializable;

/**
 * 文本消息
 */
public class TextMsg extends NormalBase implements Serializable {

    private static final long serialVersionUID = -1586330643578562474L;

    private String content;// 消息内容

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
