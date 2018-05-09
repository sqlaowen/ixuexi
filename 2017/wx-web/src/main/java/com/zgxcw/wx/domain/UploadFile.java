package com.zgxcw.wx.domain;

import java.io.Serializable;

/**
 * Created by huolh on 2016/3/9.
 */
public class UploadFile implements Serializable {
  public UploadFile(){}

  private static final long serialVersionUID = 5823062449906911416L;

  private String realPath;
  private String fileName;
  private String code;
  private String message;



  public String getRealPath() {
    return realPath;
  }

  public void setRealPath(String realPath) {
    this.realPath = realPath;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
