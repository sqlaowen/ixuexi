package com.zgxcw.service.infrastructure.dto.response;

import java.io.Serializable;

/**
 * Created by wensw on 2016/6/30.
 */
public class WXResponse<T> implements Serializable {

  private static final long serialVersionUID = 1895546458119045042L;
  private String code;// 返回码
  private String msg;// 返回消息
  private T data;// 返回内容

  public WXResponse() {}

  /**
   * 返回码
   * 
   * @return
   */
  public String getCode() {
    return code;
  }

  /**
   * 返回码
   * 
   * @param code
   */
  public void setCode(String code) {
    this.code = code;
  }

  /**
   * 返回消息
   * 
   * @return
   */
  public String getMsg() {
    return msg;
  }

  /**
   * 返回消息
   * 
   * @param msg
   */
  public void setMsg(String msg) {
    this.msg = msg;
  }

  /**
   * 获取值
   * 
   * @return
   */
  public T getData() {
    return data;
  }

  /**
   * 设置值
   * 
   * @param data
   */
  public void setData(T data) {
    this.data = data;
  }
}
