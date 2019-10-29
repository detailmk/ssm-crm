package com.shsxt.crm.customer.service;

import com.shsxt.crm.base.BaseService;
import com.shsxt.crm.customer.pojo.CustomerLoss;

/**
 * 客户流失Service
 */
public abstract class CustomerLossServiceI extends BaseService<CustomerLoss> {

    /**
     * 批量添加客户流失信息
     */
    public abstract void saveBatchCustomerLoss();

    /**
     * 确认流失
     *
     * @param lossId
     * @param lossReason
     */
    public abstract void confirmLoss(Integer lossId, String lossReason);

}
