package com.zgxcw.service.infrastructure.dto.request.wxgetway;

import com.zgxcw.service.infrastructure.dto.base.WXGetwayBase;

import java.io.Serializable;

/**
 * Created by huolh on 2016/3/1.
 */
public class UpdateWXGetwayRequest extends WXGetwayBase implements Serializable {
 
  private static final long serialVersionUID = -5471169498564986548L;

  public UpdateWXGetwayRequest() {
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
