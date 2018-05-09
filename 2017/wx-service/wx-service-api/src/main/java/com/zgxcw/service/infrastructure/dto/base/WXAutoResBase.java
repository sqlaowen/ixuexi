package com.zgxcw.service.infrastructure.dto.base;

import java.io.Serializable;

public abstract class WXAutoResBase implements Serializable {

  private static final long serialVersionUID = 5823062449906911416L;

  private String wxId;

  private String resName;

  private String resType;

  private Integer resState;

  private Integer disableState;

  private Integer msgType;

  public String getWxId() {
    return wxId;
  }

  public void setWxId(String wxId) {
    this.wxId = wxId == null ? null : wxId.trim();
  }

  public String getResName() {
    return resName;
  }

  public void setResName(String resName) {
    this.resName = resName == null ? null : resName.trim();
  }


  public String getResType() {
    return resType;
  }

  public void setResType(String resType) {
    this.resType = resType == null ? null : resType.trim();
  }

  public Integer getResState() {
    return resState;
  }

  public void setResState(Integer resState) {
    this.resState = resState;
  }

  public Integer getDisableState() {
    return disableState;
  }

  public void setDisableState(Integer disableState) {
    this.disableState = disableState;
  }

  public Integer getMsgType() {
    return msgType;
  }

  public void setMsgType(Integer msgType) {
    this.msgType = msgType;
  }
}
