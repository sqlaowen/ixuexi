package com.example;

import java.io.Serializable;

public class PageQuery<T> implements Serializable {

    /**
     * 当前的页码
     */
    private Integer currentPage;

    /**
     * 每页大小
     */
    private Integer pageSize;

    /**
     * 查询数据
     */
    private T data;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
