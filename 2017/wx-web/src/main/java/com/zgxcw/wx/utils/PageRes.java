package com.zgxcw.wx.utils;

import java.io.Serializable;
import java.util.List;

public class PageRes<T> implements Serializable {

  private static final long serialVersionUID = 8656597559014685635L;
  private long total; // 总记录数
  private List<T> list; // 结果集
  private String msg; // 消息

  public PageRes() {}

  public long getTotal() {
    return total;
  }

  public void setTotal(long total) {
    this.total = total;
  }

  public List<T> getList() {
    return list;
  }

  public void setList(List<T> list) {
    this.list = list;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

}
