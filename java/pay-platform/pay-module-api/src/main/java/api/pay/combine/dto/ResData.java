package api.pay.combine.dto;

import java.io.Serializable;

public class ResData<T> implements Serializable {
  private static final long serialVersionUID = -6429648732879323650L;
  
  private String msg;// 返回消息提示
  private String code;// 返回状态码
  private T t;// 返回内容

  /**
   * 返回消息提示
   * 
   * @return
   */
  public String getMsg() {
    return msg;
  }

  /**
   * 返回消息提示
   * 
   * @param msg
   */
  public void setMsg(String msg) {
    this.msg = msg;
  }

  /**
   * 返回状态码
   * 
   * @return
   */
  public String getCode() {
    return code;
  }

  /**
   * 返回状态码
   * 
   * @param code
   */
  public void setCode(String code) {
    this.code = code;
  }

  /**
   * 返回内容
   * 
   * @return
   */
  public T getT() {
    return t;
  }

  /**
   * 返回内容
   * 
   * @param t
   */
  public void setT(T t) {
    this.t = t;
  }

}
