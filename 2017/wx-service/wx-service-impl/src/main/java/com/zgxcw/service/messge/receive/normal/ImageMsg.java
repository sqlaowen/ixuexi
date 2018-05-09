package com.zgxcw.service.messge.receive.normal;

import java.io.Serializable;

/**
 * 图片消息
 * 
 *
 */
public class ImageMsg extends NormalBase implements Serializable {

  private static final long serialVersionUID = -4897642392301778927L;
  private String picUrl;// 图片链接
  private String mediaId;// 消息媒体id，可以调用多媒体文件下载接口拉取数据

  public String getPicUrl() {
    return picUrl;
  }

  public void setPicUrl(String picUrl) {
    this.picUrl = picUrl;
  }

  public String getMediaId() {
    return mediaId;
  }

  public void setMediaId(String mediaId) {
    this.mediaId = mediaId;
  }
}
