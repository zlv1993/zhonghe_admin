package com.admin.zhonghe.utils;

import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.List;

public class PageUtil<T> implements Serializable {
    private int code =200;
    private String msg ="查询成功";
    private List<T> data;
    private long count;
    private long pages;
    public PageUtil(PageInfo pageInfo){
        this.count=pageInfo.getTotal();
        this.data=pageInfo.getList();
        this.pages=pageInfo.getPages();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getPages() {
        return pages;
    }

    public void setPages(long pages) {
        this.pages = pages;
    }
}
