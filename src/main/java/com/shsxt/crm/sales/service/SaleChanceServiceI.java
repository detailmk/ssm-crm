package com.shsxt.crm.sales.service;

import com.shsxt.crm.base.BaseService;
import com.shsxt.crm.sales.pojo.SaleChance;

/**
 * 营销机会管理Service
 */
public abstract class SaleChanceServiceI extends BaseService<SaleChance> {

    /**
     * 添加或修改营销机会
     *
     * @param saleChance
     * @param id
     */
    public abstract void saveOrUpdateSaleChance(SaleChance saleChance, Integer id);

    /**
     * 修改开发状态
     *
     * @param saleChance
     * @return
     */
    public abstract int updateSaleChanceDevResult(SaleChance saleChance);

}
