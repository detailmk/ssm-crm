package com.shsxt.crm.serve.service;

import com.shsxt.crm.base.BaseService;
import com.shsxt.crm.serve.pojo.CustomerServe;

/**
 * 服务管理Service
 */
public abstract class CustomerServeServiceI extends BaseService<CustomerServe> {

    /**
     * 添加或修改服务
     *
     * @param customerServe
     * @param userId
     */
    public abstract void saveOrUpdateCustomerServe(CustomerServe customerServe, Integer userId);

}
