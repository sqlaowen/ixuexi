package com.zgxcw.service.infrastructure.dto.response;

import com.zgxcw.service.infrastructure.dto.base.WXGetwayBase;

import java.io.Serializable;

/**
 * Created by huolh on 2016/3/1.
 */
public class WXGetwayResponse extends WXGetwayBase implements Serializable {
  
  private static final long serialVersionUID = 5336944129619575191L;

  public WXGetwayResponse() {
  }

  private String wxId;

  /**
   * 微信网关id
   *
   * @return 微信网关id
   */
  public String getWxId() {
    return wxId;
  }

  /**
   * 微信网关id
   *
   * @param wxId 微信网关id
   */
  public void setWxId(String wxId) {
    this.wxId = wxId == null ? null : wxId.trim();
  }
}
