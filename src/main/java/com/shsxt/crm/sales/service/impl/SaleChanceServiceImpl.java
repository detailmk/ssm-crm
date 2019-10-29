package com.shsxt.crm.sales.service.impl;

import com.shsxt.crm.base.util.AssertUtil;
import com.shsxt.crm.sales.dao.SaleChanceMapper;
import com.shsxt.crm.sales.pojo.SaleChance;
import com.shsxt.crm.sales.service.SaleChanceServiceI;
import com.shsxt.crm.system.dao.UserMapper;
import com.shsxt.crm.system.pojo.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 营销机会管理Service
 */
@Service
public class SaleChanceServiceImpl extends SaleChanceServiceI {

    @Autowired
    private SaleChanceMapper saleChanceMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 添加或修改营销机会
     *
     * @param saleChance
     * @param id
     */
    @Override
    public void saveOrUpdateSaleChance(SaleChance saleChance, Integer id) {
        // 校验参数
        AssertUtil.isTrue(StringUtils.isBlank(saleChance.getCustomerName()), "客户名称为空");
        AssertUtil.isTrue(StringUtils.isBlank(saleChance.getLinkMan()), "联系人为空");
        AssertUtil.isTrue(StringUtils.isBlank(saleChance.getLinkPhone()), "联系电话为空");

        // 判断是否指定分配人
        String assignMan = saleChance.getAssignMan();
        if (null == assignMan || "".equals(assignMan)) {
            saleChance.setState(0);// 未分配
        } else {
            saleChance.setState(1);// 已分配
            saleChance.setAssignTime(new Date());// 分配时间
        }

        // 判断是否修改操作
        Integer saleChanceId = saleChance.getId();
        // 添加
        if (null == saleChanceId) {
            // 根据主键查询用户信息
            User user = userMapper.selectById(id);
            // 设置参数
            saleChance.setIsValid(1);// 1 有效
            saleChance.setCreateMan(user.getUserName());// 创建人
            saleChance.setDevResult(0);// 0 未开发
            saleChance.setCreateDate(new Date());
            saleChance.setUpdateDate(new Date());
            // 添加
            AssertUtil.isTrue(saleChanceMapper.save(saleChance) < 1, "营销机会添加失败");
        } else {
            saleChance.setUpdateDate(new Date());
            // 修改
            AssertUtil.isTrue(saleChanceMapper.update(saleChance) < 1, "营销机会修改失败");
        }
    }

    /**
     * 修改开发状态
     *
     * @param saleChance
     * @return
     */
    @Override
    public int updateSaleChanceDevResult(SaleChance saleChance) {
        return saleChanceMapper.updateSaleChanceDevResult(saleChance);
    }

}
