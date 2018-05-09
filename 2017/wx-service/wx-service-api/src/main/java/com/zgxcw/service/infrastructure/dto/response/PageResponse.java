package com.zgxcw.service.infrastructure.dto.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by huolh on 2016/3/8.
 */
public class PageResponse<T> implements Serializable {
  public PageResponse() {}

  private static final long serialVersionUID = 8656597559014685635L;
  private long total; // 总记录数
  private List<T> list; // 结果集


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
}
