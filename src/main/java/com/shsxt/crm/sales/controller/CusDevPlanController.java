package com.shsxt.crm.sales.controller;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.base.BaseResult;
import com.shsxt.crm.sales.pojo.CusDevPlan;
import com.shsxt.crm.sales.pojo.SaleChance;
import com.shsxt.crm.sales.query.CusDevPlanQuery;
import com.shsxt.crm.sales.service.CusDevPlanServiceI;
import com.shsxt.crm.sales.service.SaleChanceServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 客户开发Controller
 */
@Controller
@RequestMapping("/cusDevPlan")
public class CusDevPlanController extends BaseController {

    @Autowired
    private SaleChanceServiceI saleChanceService;

    @Autowired
    private CusDevPlanServiceI cusDevPlanService;

    /**
     * 跳转查看开发计划详情或者开发客户页面
     *
     * @param sid
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String index(Integer sid, Model model) {
        SaleChance saleChance = saleChanceService.selectById(sid);
        model.addAttribute("saleChance", saleChance);
        return "cus_dev_plan_detail";
    }

    /**
     * 分页查询
     *
     * @return
     */
    @RequestMapping("/selectCusDevPlanByParams")
    @ResponseBody
    public Map<String, Object> selectCusDevPlanByParams(CusDevPlanQuery query,
                                                        @RequestParam(defaultValue = "1") Integer page,
                                                        @RequestParam(defaultValue = "10") Integer rows) {
        query.setPageNum(page);
        query.setPageSize(rows);
        return cusDevPlanService.selectForPage(query);
    }

    /**
     * 添加或修改客户开发计划
     *
     * @param cusDevPlan
     * @return
     */
    @RequestMapping("/saveOrUpdateCusDevPlan")
    @ResponseBody
    public BaseResult saveOrUpdateCusDevPlan(CusDevPlan cusDevPlan) {
        cusDevPlanService.saveOrUpdateCusDevPlan(cusDevPlan);
        return BaseResult.success();
    }

    /**
     * 批量逻辑删除客户开发计划
     *
     * @param ids
     * @return
     */
    @RequestMapping("/deleteBatchCusDevPlan")
    @ResponseBody
    public BaseResult deleteBatchCusDevPlan(Integer[] ids) {
        cusDevPlanService.deleteBatch(ids);
        return BaseResult.success();
    }

}
