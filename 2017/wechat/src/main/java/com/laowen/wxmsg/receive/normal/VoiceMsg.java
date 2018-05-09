package com.laowen.wxmsg.receive.normal;

import java.io.Serializable;

/**
 * 语音消息
 */
public class VoiceMsg extends NormalBase implements Serializable {

    private static final long serialVersionUID = 2376671765809385345L;
    private String mediaId;// 消息媒体id，可以调用多媒体文件下载接口拉取数据。
    private String format;// 语音格式，如amr，speex等
    private String recognition;// 开启语音识别后增加的字段,为语音识别结果，使用UTF8编码

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getRecognition() {
        return recognition;
    }

    public void setRecognition(String recognition) {
        this.recognition = recognition;
    }

}
