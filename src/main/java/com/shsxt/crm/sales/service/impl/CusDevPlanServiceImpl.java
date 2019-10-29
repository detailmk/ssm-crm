package com.shsxt.crm.sales.service.impl;

import com.shsxt.crm.base.constants.CrmConstants;
import com.shsxt.crm.base.util.AssertUtil;
import com.shsxt.crm.sales.dao.CusDevPlanMapper;
import com.shsxt.crm.sales.pojo.CusDevPlan;
import com.shsxt.crm.sales.pojo.SaleChance;
import com.shsxt.crm.sales.service.CusDevPlanServiceI;
import com.shsxt.crm.sales.service.SaleChanceServiceI;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 开发计划service
 */
@Service
public class CusDevPlanServiceImpl extends CusDevPlanServiceI {

    @Autowired
    private CusDevPlanMapper cusDevPlanMapper;

    @Autowired
    private SaleChanceServiceI saleChanceService;

    /**
     * 添加或修改客户开发计划
     *
     * @param cusDevPlan
     */
    @Override
    public void saveOrUpdateCusDevPlan(CusDevPlan cusDevPlan) {
        // 校验参数
        AssertUtil.isTrue(null == cusDevPlan.getPlanDate(), "计划日期为空");
        AssertUtil.isTrue(StringUtils.isBlank(cusDevPlan.getPlanItem()), "计划内容为空");
        AssertUtil.isTrue(StringUtils.isBlank(cusDevPlan.getExeAffect()), "执行效果为空");

        // 判断是否修改操作
        Integer id = cusDevPlan.getId();
        // 添加
        if (null == id) {
            cusDevPlan.setCreateDate(new Date());
            cusDevPlan.setUpdateDate(new Date());
            cusDevPlan.setIsValid(1);
            AssertUtil.isTrue(cusDevPlanMapper.save(cusDevPlan) < 1, CrmConstants.OPS_FAILED_MESSAGE);

            // 根据主键查询营销机会
            SaleChance saleChance = saleChanceService.selectById(cusDevPlan.getSaleChanceId());
            /*
                修改开发状态：
                    如果是 0 未开发，修改为 1 开发中；
                    如果是 1 开发中，不做任何更改
             */
            if (0 == saleChance.getDevResult()) {
                saleChance.setDevResult(1);
                AssertUtil.isTrue(saleChanceService.update(saleChance) < 1, CrmConstants.OPS_FAILED_MESSAGE);
            }
        } else {
            // 修改
            cusDevPlan.setUpdateDate(new Date());
            AssertUtil.isTrue(cusDevPlanMapper.update(cusDevPlan) < 1, CrmConstants.OPS_FAILED_MESSAGE);
        }
    }

}
