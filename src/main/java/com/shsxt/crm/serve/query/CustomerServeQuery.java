package com.shsxt.crm.serve.query;

import com.shsxt.crm.base.BaseQuery;

/**
 * 服务管理查询对象
 */
public class CustomerServeQuery extends BaseQuery {

    private String serveType;
    private String overview;
    private String customer;
    private String createDate;
    private String state;

    public String getServeType() {
        return serveType;
    }

    public void setServeType(String serveType) {
        this.serveType = serveType;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
