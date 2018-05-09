package com.zgxcw.wx.domain;

import java.io.Serializable;

/**
 * Created by huolh on 2016/3/8.
 */
public class QueryWxAutoRes implements Serializable {

  public QueryWxAutoRes(){}

  private static final long serialVersionUID = 5823062449906911416L;

  private String wxId;

  private String resName;

  private String resType;

  private Integer resState;

  private Integer msgType;

  private Integer pageNum;

  private Integer pageSize;

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

  public Integer getMsgType() {
    return msgType;
  }

  public void setMsgType(Integer msgType) {
    this.msgType = msgType;
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
