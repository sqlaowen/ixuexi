package com.x2016.messge.passive;

import java.io.Serializable;

/**
 * 图文消息
 * 
 * @author wensw
 *
 */
public class ArticleAnswerMsg implements Serializable {

  private static final long serialVersionUID = 8888629292628490400L;
  private String title;// 消息标题,可为null
  private String description;// 消息描述,可为null
  private String picUrl;// 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200,可为null
  private String url;// 点击图文消息跳转链接,可为null

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
   * 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200,可为null
   * 
   * @return
   */
  public String getPicUrl() {
    return picUrl;
  }

  /**
   * 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200,可为null
   * 
   * @param picUrl
   */
  public void setPicUrl(String picUrl) {
    this.picUrl = picUrl;
  }

  /**
   * 点击图文消息跳转链接,可为null
   * 
   * @return
   */
  public String getUrl() {
    return url;
  }

  /**
   * 点击图文消息跳转链接,可为null
   * 
   * @param url
   */
  public void setUrl(String url) {
    this.url = url;
  }

}
