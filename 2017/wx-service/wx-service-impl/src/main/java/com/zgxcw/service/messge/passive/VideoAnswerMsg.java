package com.zgxcw.service.messge.passive;

import java.io.Serializable;

/**
 * 回复视频消息
 * 
 * @author wensw
 *
 */
public class VideoAnswerMsg extends PassiveBase implements Serializable {

  private static final long serialVersionUID = 4424290850111905088L;
  private String mediaId;// 通过素材管理接口上传多媒体文件，得到的id
  private String title;// 消息标题,可为null
  private String description;// 消息描述,可为null

  /**
   * 通过素材管理接口上传多媒体文件，得到的id
   * 
   * @return
   */
  public String getMediaId() {
    return mediaId;
  }

  /**
   * 通过素材管理接口上传多媒体文件，得到的id
   * 
   * @param mediaId
   */
  public void setMediaId(String mediaId) {
    this.mediaId = mediaId;
  }

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

}
