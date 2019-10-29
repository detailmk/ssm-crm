package com.shsxt.crm.customer.query;

import com.shsxt.crm.base.BaseQuery;

/**
 * 客户管理查询对象
 */
public class CustomerQuery extends BaseQuery {

    private String khno;
    private String name;
    private String fr;

    // 客户联系人使用属性
    private Integer cusId;
    // 订单详情使用属性
    private Integer orderId;

    public String getKhno() {
        return khno;
    }

    public void setKhno(String khno) {
        this.khno = khno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFr() {
        return fr;
    }

    public void setFr(String fr) {
        this.fr = fr;
    }

    public Integer getCusId() {
        return cusId;
    }

    public void setCusId(Integer cusId) {
        this.cusId = cusId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

}
