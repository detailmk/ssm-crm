package com.shsxt.crm.customer.service;

import com.shsxt.crm.base.BaseService;
import com.shsxt.crm.customer.pojo.Customer;

import java.util.List;
import java.util.Map;

/**
 * 客户管理Service
 */
public abstract class CustomerServiceI extends BaseService<Customer> {

    /**
     * 添加或修改客户
     *
     * @param customer
     */
    public abstract void saveOrUpdateCustomer(Customer customer);

    /**
     * 查询客户名称列表
     *
     * @return
     */
    public abstract List<Map<Object, Object>> selectCustomerNameList();

}
