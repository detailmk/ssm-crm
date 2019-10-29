package com.shsxt.crm.sales.service;

import com.shsxt.crm.base.BaseService;
import com.shsxt.crm.sales.pojo.CusDevPlan;

/**
 * 开发计划service
 */
public abstract class CusDevPlanServiceI extends BaseService<CusDevPlan> {

    /**
     * 添加或修改客户开发计划
     *
     * @param cusDevPlan
     */
    public abstract void saveOrUpdateCusDevPlan(CusDevPlan cusDevPlan);

}
