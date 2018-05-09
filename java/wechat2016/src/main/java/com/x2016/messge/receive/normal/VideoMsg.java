package com.x2016.messge.receive.normal;

import java.io.Serializable;

/**
 * 视频消息
 * msgtype: 视频:video, 小视频:shortvideo
 * 
 * @author wensw
 *
 */
public class VideoMsg extends NormalBase implements Serializable {

  private static final long serialVersionUID = 8854484098992404265L;
  private String mediaId;// 消息媒体id，可以调用多媒体文件下载接口拉取数据。
  private String thumbMediaId;// 消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。

  public String getMediaId() {
    return mediaId;
  }

  public void setMediaId(String mediaId) {
    this.mediaId = mediaId;
  }

  public String getThumbMediaId() {
    return thumbMediaId;
  }

  public void setThumbMediaId(String thumbMediaId) {
    this.thumbMediaId = thumbMediaId;
  }

}
