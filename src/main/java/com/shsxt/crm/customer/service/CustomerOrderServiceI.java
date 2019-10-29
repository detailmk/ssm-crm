package com.shsxt.crm.customer.service;

import com.shsxt.crm.base.BaseService;
import com.shsxt.crm.customer.dto.CustomerOrderDto;
import com.shsxt.crm.customer.pojo.CustomerOrder;

/**
 * 客户历史订单Service
 */
public abstract class CustomerOrderServiceI extends BaseService<CustomerOrder> {

    /**
     * 根据订单主键查询订单及总金额
     *
     * @param id
     * @return
     */
    public abstract CustomerOrderDto selectCustomerOrderAndTotalPriceById(Integer id);

}
