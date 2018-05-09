package com.zgxcw.wx.utils;

import java.io.Serializable;

public class ResData implements Serializable {
  private static final long serialVersionUID = -6429648732879323650L;
  private String msg;
  private String code;

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

}
