package com.laowen.bean.domain;

import java.io.Serializable;

/**
 * Created by iyou on 2017/4/20.
 */
public class CMOIT2020Dto implements Serializable {

    private static final long serialVersionUID = 5823062449906911416L;

    private String left; //套餐剩余量(单位:KB)
    private String prodid; //套餐ID
    private String prodinstid; //产品实例ID
    protected String prodname; //套餐名称
    protected String total; //套餐总量(单位:MB)
    protected String used; //套餐使用量(单位:KB)

    /**
     * 套餐剩余量(单位:KB)
     *
     * @return
     */
    public String getLeft() {
        return left;
    }

    /**
     * 套餐剩余量(单位:KB)
     *
     * @param left
     */
    public void setLeft(String left) {
        this.left = left;
    }

    /**
     * 套餐ID
     *
     * @return
     */
    public String getProdid() {
        return prodid;
    }

    /**
     * 套餐ID
     *
     * @param prodid
     */
    public void setProdid(String prodid) {
        this.prodid = prodid;
    }

    /**
     * 产品实例ID
     *
     * @return
     */
    public String getProdinstid() {
        return prodinstid;
    }

    /**
     * 产品实例ID
     *
     * @param prodinstid
     */
    public void setProdinstid(String prodinstid) {
        this.prodinstid = prodinstid;
    }

    /**
     * 套餐名称
     *
     * @return
     */
    public String getProdname() {
        return prodname;
    }

    /**
     * 套餐名称
     *
     * @param prodname
     */
    public void setProdname(String prodname) {
        this.prodname = prodname;
    }

    /**
     * 套餐总量(单位:MB)
     *
     * @return
     */
    public String getTotal() {
        return total;
    }

    /**
     * 套餐总量(单位:MB)
     *
     * @param total
     */
    public void setTotal(String total) {
        this.total = total;
    }

    /**
     * 套餐使用量(单位:KB)
     *
     * @return
     */
    public String getUsed() {
        return used;
    }

    /**
     * 套餐使用量(单位:KB)
     *
     * @param used
     */
    public void setUsed(String used) {
        this.used = used;
    }

    @Override
    public String toString() {
        return "CMOIT2020Dto{" +
                "left='" + left + '\'' +
                ", prodid='" + prodid + '\'' +
                ", prodinstid='" + prodinstid + '\'' +
                ", prodname='" + prodname + '\'' +
                ", total='" + total + '\'' +
                ", used='" + used + '\'' +
                '}';
    }
}
