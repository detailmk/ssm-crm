package com.shsxt.crm.customer.service.impl;

import com.shsxt.crm.customer.dao.CustomerOrderMapper;
import com.shsxt.crm.customer.dto.CustomerOrderDto;
import com.shsxt.crm.customer.service.CustomerOrderServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 客户历史订单Service
 */
@Service
public class CustomerOrderServiceImpl extends CustomerOrderServiceI {

    @Autowired
    private CustomerOrderMapper customerOrderMapper;

    /**
     * 根据订单主键查询订单及总金额
     *
     * @param id
     * @return
     */
    @Override
    public CustomerOrderDto selectCustomerOrderAndTotalPriceById(Integer id) {
        return customerOrderMapper.selectCustomerOrderAndTotalPriceById(id);
    }

}
