package com.shsxt.crm.customer.dao;

import com.shsxt.crm.base.BaseDao;
import com.shsxt.crm.customer.dto.CustomerOrderDto;
import com.shsxt.crm.customer.pojo.CustomerOrder;

/**
 * 客户历史订单Mapper
 */
public interface CustomerOrderMapper extends BaseDao<CustomerOrder> {

    /**
     * 根据订单主键查询订单及总金额
     *
     * @param id
     * @return
     */
    CustomerOrderDto selectCustomerOrderAndTotalPriceById(Integer id);

}