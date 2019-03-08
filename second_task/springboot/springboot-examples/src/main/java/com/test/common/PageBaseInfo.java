package com.test.common;

import java.util.List;

public class PageBaseInfo {

    // 需要显示的数据集 每页显示的数据量
    @SuppressWarnings("rawtypes")
    private List rows;

    // 当前页
    private long page;

    // 总页数
    private long total;

    // 总记录数
    private long records;

    // 反馈状态码
    private Integer status;

    // 反馈信息
    private String message;

    private long size;

    // 默认页面容量为20
    public final static long NO_SIZE = 20;

    private String account;

    public PageBaseInfo() {
        this.size = NO_SIZE;
    }

    /**
     * @return the account
     */
    public String getAccount() {
        return account;
    }

    /**
     * @param account the account to set
     */
    public void setAccount(String account) {
        this.account = account;
    }
    @SuppressWarnings("rawtypes") //去掉泛型的警告
    public PageBaseInfo(List rows, long page, long records, long size) {
        this.rows = rows;// 数据集合
        this.page = page;// 当前页
        this.records = records;// 总记录数
        this.size = size;// 页面容量
        this.total = (records + this.size - 1) / this.size;// 总页数
        this.status = 200;
        this.message = "";
    }

    public PageBaseInfo(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    @SuppressWarnings("rawtypes")
    public List getRows() {
        return rows;
    }

    @SuppressWarnings("rawtypes")
    public void setRows(List rows) {
        this.rows = rows;
    }

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getRecords() {
        return records;
    }

    public void setRecords(long records) {
        this.records = records;
        // 设置总记录数的时候同时设置总页数
        setTotal((records + this.size - 1) / this.size);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}