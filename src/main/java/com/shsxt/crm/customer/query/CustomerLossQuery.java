package com.shsxt.crm.customer.query;

import com.shsxt.crm.base.BaseQuery;

/**
 * 客户流失管理查询对象
 */
public class CustomerLossQuery extends BaseQuery {

    private String cusName;
    private String cusNo;
    private String createDate;
    // 客户暂缓措施使用属性
    private Integer lossId;

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusNo() {
        return cusNo;
    }

    public void setCusNo(String cusNo) {
        this.cusNo = cusNo;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Integer getLossId() {
        return lossId;
    }

    public void setLossId(Integer lossId) {
        this.lossId = lossId;
    }
}
