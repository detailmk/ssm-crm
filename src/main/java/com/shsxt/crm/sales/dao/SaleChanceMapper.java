package com.shsxt.crm.sales.dao;

import com.shsxt.crm.base.BaseDao;
import com.shsxt.crm.sales.pojo.SaleChance;

public interface SaleChanceMapper extends BaseDao<SaleChance> {

    /**
     * 修改开发状态
     *
     * @param saleChance
     * @return
     */
    int updateSaleChanceDevResult(SaleChance saleChance);

}