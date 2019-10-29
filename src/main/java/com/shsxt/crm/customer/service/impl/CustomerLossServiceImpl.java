package com.shsxt.crm.customer.service.impl;

import com.shsxt.crm.base.constants.CrmConstants;
import com.shsxt.crm.base.util.AssertUtil;
import com.shsxt.crm.customer.dao.CustomerLossMapper;
import com.shsxt.crm.customer.dao.CustomerMapper;
import com.shsxt.crm.customer.pojo.Customer;
import com.shsxt.crm.customer.pojo.CustomerLoss;
import com.shsxt.crm.customer.service.CustomerLossServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 客户流失Service
 */
@Service
public class CustomerLossServiceImpl extends CustomerLossServiceI {

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private CustomerLossMapper customerLossMapper;

    /**
     * 批量添加客户流失信息
     */
    @Override
    public void saveBatchCustomerLoss() {
        // 查询预流失客户
        List<Customer> customerList = customerMapper.selectPrepareLossCustomer();
        // 判断是否存在预流失客户
        if (CollectionUtils.isEmpty(customerList) || customerList.size() < 1)
            return;
        // 添加客户流失信息
        List<CustomerLoss> customerLossList = new ArrayList<>();
        for (Customer c : customerList) {
            CustomerLoss cl = new CustomerLoss();
            cl.setCusNo(c.getKhno());// 客户编号
            cl.setCusName(c.getName());// 客户名称
            cl.setCusManager(c.getCusManager());// 客户经理
            cl.setState(1);// 1 预流失 2 确认流失
            cl.setIsValid(1);
            cl.setCreateDate(new Date());
            cl.setUpdateDate(new Date());
            customerLossList.add(cl);
            // 将客户表state状态改为预流失
            c.setState(1);
            AssertUtil.isTrue(customerMapper.update(c) < 1, CrmConstants.OPS_FAILED_MESSAGE);
        }
        AssertUtil.isTrue(customerLossMapper.saveBatch(customerLossList) < customerLossList.size(),
                CrmConstants.OPS_FAILED_MESSAGE);
    }

    /**
     * 确认流失
     *
     * @param lossId
     * @param lossReason
     */
    @Override
    public void confirmLoss(Integer lossId, String lossReason) {
        AssertUtil.isTrue(null == lossId, CrmConstants.OPS_FAILED_MESSAGE);
        CustomerLoss customerLoss = customerLossMapper.selectById(lossId);
        AssertUtil.isTrue(null == customerLoss, CrmConstants.OPS_FAILED_MESSAGE);
        // 添加确认流失信息
        customerLoss.setState(2);// 1 预流失 2 确认流失
        customerLoss.setLossReason(lossReason);
        customerLoss.setConfirmLossTime(new Date());
        customerLoss.setUpdateDate(new Date());
        AssertUtil.isTrue(customerLossMapper.update(customerLoss) < 1,
                CrmConstants.OPS_FAILED_MESSAGE);
    }

}
