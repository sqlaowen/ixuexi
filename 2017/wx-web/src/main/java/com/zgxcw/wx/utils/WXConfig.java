package com.zgxcw.wx.utils;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 微信参数配置
 * 
 * @author wensw
 *
 */
public class WXConfig implements Serializable {

  private static final long serialVersionUID = 2601893989190873750L;
  @Autowired
  private static HttpServletRequest request;
  
  private String wxId; // 微信网关id

  /**
   * 微信网关id
   * 
   * @return
   */
  public String getWxId() {
    System.out.println(request);
    System.out.println("--------------------------------");
    return wxId;
  }

  /**
   * 微信网关id
   * 
   * @param wxId
   */
  public void setWxId(String wxId) {
    System.out.println(request);
    System.out.println("--------------------------------");
    this.wxId = wxId;
  }
}
