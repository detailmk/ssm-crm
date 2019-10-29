package com.shsxt.crm.serve.service.impl;

import com.shsxt.crm.base.constants.CrmConstants;
import com.shsxt.crm.base.util.AssertUtil;
import com.shsxt.crm.serve.dao.CustomerServeMapper;
import com.shsxt.crm.serve.pojo.CustomerServe;
import com.shsxt.crm.serve.service.CustomerServeServiceI;
import com.shsxt.crm.system.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 服务管理Service
 */
@Service
public class CustomerServeServiceImpl extends CustomerServeServiceI {

    @Autowired
    private CustomerServeMapper customerServeMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 添加或修改服务
     *
     * @param customerServe
     * @param userId
     */
    @Override
    public void saveOrUpdateCustomerServe(CustomerServe customerServe, Integer userId) {
        Integer id = customerServe.getId();
        // 添加
        if (null == id) {
            // 服务状态 1 创建 2 分配 3 处理 4 反馈 5 归档
            customerServe.setState("1");
            // 服务创建人
            customerServe.setCreatePeople(userMapper.selectById(userId).getUserName());
            customerServe.setCreateDate(new Date());
            customerServe.setUpdateDate(new Date());
            customerServe.setIsValid(1);
            AssertUtil.isTrue(customerServeMapper.save(customerServe) < 1,
                    CrmConstants.OPS_FAILED_MESSAGE);
        } else {
            // 修改
            // 获取服务状态
            String state = customerServe.getState();
            if ("1".equals(state)) {
                customerServe.setState("2");
                customerServe.setAssignTime(new Date());// 分配时间
            } else if ("2".equals(state)) {
                customerServe.setState("3");
                customerServe.setServiceProceTime(new Date());// 处理时间
                customerServe.setServiceProcePeople(userMapper.selectById(userId).getUserName());// 处理人
            } else if ("3".equals(state)) {
                customerServe.setState("4");
            }
            AssertUtil.isTrue(customerServeMapper.update(customerServe) < 1,
                    CrmConstants.OPS_FAILED_MESSAGE);
        }
    }

}
