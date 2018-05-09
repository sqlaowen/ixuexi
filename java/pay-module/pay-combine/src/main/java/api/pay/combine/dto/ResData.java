package api.pay.combine.dto;

import java.io.Serializable;

public class ResData<T> implements Serializable {
  private static final long serialVersionUID = -6429648732879323650L;
  private String msg;//返回消息提示
  private String code;//返回状态码
  private T t;//返回内容

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public T getT() {
    return t;
  }

  public void setT(T t) {
    this.t = t;
  }

}
