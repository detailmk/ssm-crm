package com.shsxt.crm.customer.dao;

import com.shsxt.crm.base.BaseDao;
import com.shsxt.crm.customer.pojo.Customer;

import java.util.List;
import java.util.Map;

/**
 * 客户管理Mapper
 */
public interface CustomerMapper extends BaseDao<Customer> {

    /**
     * 查询预流失客户
     *
     * @return
     */
    List<Customer> selectPrepareLossCustomer();

    /**
     * 查询客户名称列表
     *
     * @return
     */
    List<Map<Object, Object>> selectCustomerNameList();

}
