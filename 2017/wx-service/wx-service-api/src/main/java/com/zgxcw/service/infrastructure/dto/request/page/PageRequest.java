package com.zgxcw.service.infrastructure.dto.request.page;

import java.io.Serializable;

/**
 * Created by huolh on 2016/3/8.
 */
public class PageRequest <T> implements Serializable {

  public PageRequest(){}

  private static final long serialVersionUID = 8656597559014685635L;

  private T t; //
  private int pageNum; //
  private int pageSize; //

  public T getT() {
    return t;
  }

  public void setT(T t) {
    this.t = t;
  }

  public int getPageNum() {
    return pageNum;
  }

  public void setPageNum(Integer pageNum) {
    this.pageNum = pageNum;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }
}
