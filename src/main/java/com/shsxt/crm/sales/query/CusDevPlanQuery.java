package com.shsxt.crm.sales.query;

import com.shsxt.crm.base.BaseQuery;

/**
 * 客户开发计划查询对象
 */
public class CusDevPlanQuery extends BaseQuery {

    private Integer saleChanceId;

    public Integer getSaleChanceId() {
        return saleChanceId;
    }

    public void setSaleChanceId(Integer saleChanceId) {
        this.saleChanceId = saleChanceId;
    }
}
