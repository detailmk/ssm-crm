package com.shsxt.crm.customer.controller;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.base.BaseResult;
import com.shsxt.crm.base.util.AssertUtil;
import com.shsxt.crm.customer.pojo.CustomerReprieve;
import com.shsxt.crm.customer.query.CustomerLossQuery;
import com.shsxt.crm.customer.service.CustomerLossServiceI;
import com.shsxt.crm.customer.service.CustomerReprieveServiceI;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Map;

/**
 * 客户流失Controller
 */
@Controller
@RequestMapping("/customerLoss")
public class CustomerLossController extends BaseController {

    @Autowired
    private CustomerLossServiceI customerLossService;

    @Autowired
    private CustomerReprieveServiceI customerReprieveService;

    /**
     * 批量添加客户流失信息(手动添加接口)
     *
     * @return
     */
    @RequestMapping("/saveBatchCustomerLoss")
    @ResponseBody
    public BaseResult saveBatchCustomerLoss() {
        customerLossService.saveBatchCustomerLoss();
        return BaseResult.success();
    }

    /**
     * 跳转客户流失管理首页
     *
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "customer_loss";
    }

    /**
     * 分页查询客户流失信息
     *
     * @param query
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/selectCustomerLossByParams")
    @ResponseBody
    public Map<String, Object> selectCustomerLossByParams(CustomerLossQuery query,
                                                          @RequestParam(defaultValue = "1") Integer page,
                                                          @RequestParam(defaultValue = "10") Integer rows) {
        query.setPageNum(page);
        query.setPageSize(rows);
        return customerLossService.selectForPage(query);
    }

    /**
     * 跳转添加暂缓措施页面
     *
     * @return
     */
    @RequestMapping("/reprieve/index")
    public String reprieveIndex(Integer lossId, Model model) {
        // 根据主键查询客户流失信息
        model.addAttribute("customerLoss", customerLossService.selectById(lossId));
        return "customer_loss_reprieve";
    }

    /**
     * 分页查询客户暂缓措施信息
     *
     * @param query
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/selectCustomerReprieveByParams")
    @ResponseBody
    public Map<String, Object> selectCustomerReprieveByParams(CustomerLossQuery query,
                                                              @RequestParam(defaultValue = "1") Integer page,
                                                              @RequestParam(defaultValue = "10") Integer rows) {
        query.setPageNum(page);
        query.setPageSize(rows);
        return customerReprieveService.selectForPage(query);
    }

    /**
     * 添加客户暂缓措施
     *
     * @param customerReprieve
     * @return
     */
    @RequestMapping("/saveCustomerReprieve")
    @ResponseBody
    public BaseResult saveCustomerReprieve(CustomerReprieve customerReprieve) {
        customerReprieve.setCreateDate(new Date());
        customerReprieve.setUpdateDate(new Date());
        customerReprieve.setIsValid(1);
        int result = customerReprieveService.save(customerReprieve);
        return result > 0 ? BaseResult.success() : BaseResult.error();
    }

    /**
     * 修改客户暂缓措施
     *
     * @param customerReprieve
     * @return
     */
    @RequestMapping("/updateCustomerReprieve")
    @ResponseBody
    public BaseResult updateCustomerReprieve(CustomerReprieve customerReprieve) {
        customerReprieve.setUpdateDate(new Date());
        int result = customerReprieveService.update(customerReprieve);
        return result > 0 ? BaseResult.success() : BaseResult.error();
    }

    /**
     * 逻辑删除客户暂缓措施
     *
     * @param ids
     * @return
     */
    @RequestMapping("/deleteBatchCustomerReprieve")
    @ResponseBody
    public BaseResult deleteBatchCustomerReprieve(Integer[] ids) {
        AssertUtil.isTrue(ArrayUtils.isEmpty(ids), "请选择待删除记录!");
        int result = customerReprieveService.deleteBatch(ids);
        return result > 0 ? BaseResult.success() : BaseResult.error();
    }

    /**
     * 确认流失
     *
     * @param lossId
     * @param lossReason
     * @return
     */
    @RequestMapping("/confirmLoss")
    @ResponseBody
    public BaseResult confirmLoss(Integer lossId, String lossReason) {
        customerLossService.confirmLoss(lossId, lossReason);
        return BaseResult.success();
    }

}
