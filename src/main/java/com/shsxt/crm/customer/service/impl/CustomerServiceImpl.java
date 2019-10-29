package com.shsxt.crm.customer.service.impl;

import com.shsxt.crm.base.constants.CrmConstants;
import com.shsxt.crm.base.util.AssertUtil;
import com.shsxt.crm.base.util.DateUtil;
import com.shsxt.crm.customer.dao.CustomerMapper;
import com.shsxt.crm.customer.pojo.Customer;
import com.shsxt.crm.customer.service.CustomerServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 客户管理Service
 */
@Service
public class CustomerServiceImpl extends CustomerServiceI {

    @Autowired
    private CustomerMapper customerMapper;

    /**
     * 添加或修改客户
     *
     * @param customer
     */
    @Override
    public void saveOrUpdateCustomer(Customer customer) {
        Integer id = customer.getId();
        // 添加
        if (null == id) {
            customer.setState(0);// 0 未流失 1 预流失
            customer.setCreateDate(new Date());
            customer.setUpdateDate(new Date());
            customer.setIsValid(1);
            // 客户编号
            customer.setKhno(generatorKhno());
            AssertUtil.isTrue(customerMapper.save(customer) < 1,
                    CrmConstants.OPS_FAILED_MESSAGE);
        } else {
            // 修改
            customer.setUpdateDate(new Date());
            AssertUtil.isTrue(customerMapper.update(customer) < 1,
                    CrmConstants.OPS_FAILED_MESSAGE);
        }
    }

    /**
     * 查询客户名称列表
     *
     * @return
     */
    @Override
    public List<Map<Object, Object>> selectCustomerNameList() {
        return customerMapper.selectCustomerNameList();
    }

    /**
     * 生产客户编号
     *
     * @return
     */
    private String generatorKhno() {
        return "KH" + DateUtil.getDateStr(LocalDateTime.now(), "yyyyMMddHHmmss")
                + UUID.randomUUID().toString().replace("-", "").substring(0, 6);
    }

}
