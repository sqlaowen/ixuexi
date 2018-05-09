package api.authc.single.dto;

import java.io.Serializable;

public class PageReq<T> implements Serializable {

  private static final long serialVersionUID = 8656597559014685635L;

  private T t; //
  private Integer pageNum; //
  private Integer pageSize; //

  public T getT() {
    return t;
  }

  public void setT(T t) {
    this.t = t;
  }

  public Integer getPageNum() {
    return pageNum;
  }

  public void setPageNum(Integer pageNum) {
    this.pageNum = pageNum;
  }

  public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }
}
