package com.zgxcw.service.infrastructure.dto.response;

import com.zgxcw.service.infrastructure.dto.base.WxMsgTempleteBase;

import java.io.Serializable;

/**
 * Created by huolh on 2016/7/26.
 */
public class WxMsgTempleteResponse extends WxMsgTempleteBase implements Serializable {
  
  private static final long serialVersionUID = -1489752560067172246L;

  public WxMsgTempleteResponse() {}

  @Override
  public String toString() {
    return "WxMsgTempleteResponse{} " + super.toString();
  }
}
