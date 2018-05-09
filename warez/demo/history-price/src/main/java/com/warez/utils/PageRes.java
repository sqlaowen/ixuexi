package com.warez.utils;

import com.github.pagehelper.Page;

import java.io.Serializable;
import java.util.List;

public class PageRes<T> implements Serializable {

    private static final long serialVersionUID = 8656597559014685635L;
    private long total; // 总记录数
    private int pages; //总页数
    private int pageSize; //每页大小
    private List<T> list; // 结果集
    private String msg; // 消息

    public PageRes() {
    }

    /**
     * 包装Page对象
     *
     * @param list page结果
     */
    public PageRes(List<T> list) {
        if (list instanceof Page) {
            Page<T> page = (Page<T>) list;
            this.total = page.getTotal();
            this.pages = page.getPages();
            this.pageSize = page.getPageSize();
            this.list = page;
        }
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
