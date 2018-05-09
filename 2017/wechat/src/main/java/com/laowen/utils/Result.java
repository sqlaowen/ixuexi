package com.laowen.utils;

import java.io.Serializable;

/**
 * Created by wenshiwei on 2017/4/12.
 */
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -6429648732879323650L;

    private String msg;// 返回消息提示
    private Boolean code;// true:成功, false:失败
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
     * true:成功, false:失败
     *
     * @return
     */
    public Boolean getCode() {
        return code;
    }

    /**
     * true:成功, false:失败
     *
     * @param code
     */
    public void setCode(Boolean code) {
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

    @Override
    public String toString() {
        return "Result{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                ", t=" + t +
                '}';
    }
}
