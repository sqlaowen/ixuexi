package com.laowen.wxmsg.passive;

import java.io.Serializable;

/**
 * 回复音乐消息
 *
 * @author wensw
 */
public class MusicAnswerMsg extends PassiveBase implements Serializable {

    private static final long serialVersionUID = 5012818252677232656L;
    private String title;// 消息标题,可为null
    private String description;// 消息描述,可为null
    private String musicUrl;// 音乐链接,可为null
    private String hqMusicUrl;// 高质量音乐链接，WIFI环境优先使用该链接播放音乐,可为null
    private String thumbMediaId;// 缩略图的媒体id，通过素材管理接口上传多媒体文件，得到的id,可为null

    /**
     * 消息标题,可为null
     *
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * 消息标题,可为null
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 消息描述,可为null
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * 消息描述,可为null
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 音乐链接,可为null
     *
     * @return
     */
    public String getMusicUrl() {
        return musicUrl;
    }

    /**
     * 音乐链接,可为null
     *
     * @param musicUrl
     */
    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    /**
     * 高质量音乐链接，WIFI环境优先使用该链接播放音乐,可为null
     *
     * @return
     */
    public String getHQMusicUrl() {
        return hqMusicUrl;
    }

    /**
     * 高质量音乐链接，WIFI环境优先使用该链接播放音乐,可为null
     *
     * @param hqMusicUrl
     */
    public void setHQMusicUrl(String hqMusicUrl) {
        this.hqMusicUrl = hqMusicUrl;
    }

    /**
     * 缩略图的媒体id，通过素材管理接口上传多媒体文件，得到的id,可为null
     *
     * @return
     */
    public String getThumbMediaId() {
        return thumbMediaId;
    }

    /**
     * 缩略图的媒体id，通过素材管理接口上传多媒体文件，得到的id,可为null
     *
     * @param thumbMediaId
     */
    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

}
