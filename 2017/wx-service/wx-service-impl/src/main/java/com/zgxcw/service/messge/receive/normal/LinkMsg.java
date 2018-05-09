package com.zgxcw.service.messge.receive.normal;

import java.io.Serializable;

/**
 * 链接消息
 * 
 *
 */
public class LinkMsg extends NormalBase implements Serializable {

  private static final long serialVersionUID = -8298111584186538561L;
  private String title;// 链接消息标题
  private String description;// 链接消息描述
  private String url;// 链接消息链接

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

}
