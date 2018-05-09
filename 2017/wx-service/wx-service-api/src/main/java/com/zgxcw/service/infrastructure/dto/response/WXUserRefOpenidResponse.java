package com.zgxcw.service.infrastructure.dto.response;

import java.io.Serializable;

import com.zgxcw.service.infrastructure.dto.base.WXUserRefOpenidBase;

public class WXUserRefOpenidResponse extends WXUserRefOpenidBase implements Serializable {

  private static final long serialVersionUID = 1586545051278553351L;

  public WXUserRefOpenidResponse() {}

  // 关联id
  private String refId;

  private Long createTime;

  /**
   * 关联id
   *
   * @return
   */
  public String getRefId() {
    return refId;
  }

  /**
   * 关联id
   *
   * @param refId
   */
  public void setRefId(String refId) {
    this.refId = refId == null ? null : refId.trim();
  }

  public Long getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Long createTime) {
    this.createTime = createTime;
  }

}
