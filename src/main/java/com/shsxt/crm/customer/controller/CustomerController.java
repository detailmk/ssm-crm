package com.shsxt.crm.customer.controller;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.base.BaseResult;
import com.shsxt.crm.base.constants.CrmConstants;
import com.shsxt.crm.base.util.AssertUtil;
import com.shsxt.crm.customer.dto.CustomerOrderDto;
import com.shsxt.crm.customer.pojo.Customer;
import com.shsxt.crm.customer.pojo.CustomerContact;
import com.shsxt.crm.customer.pojo.CustomerLinkman;
import com.shsxt.crm.customer.pojo.CustomerOrder;
import com.shsxt.crm.customer.query.CustomerQuery;
import com.shsxt.crm.customer.service.*;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 客户管理Controller
 */
@Controller
@RequestMapping("/customer")
public class CustomerController extends BaseController {

    @Autowired
    private CustomerServiceI customerService;

    @Autowired
    private CustomerLinkmanServiceI customerLinkmanService;

    @Autowired
    private CustomerContactServiceI customerContactService;

    @Autowired
    private CustomerOrderServiceI customerOrderService;

    @Autowired
    private OrderDetailsServiceI orderDetailsService;

    @Autowired
    private CustomerLossServiceI customerLossService;

    /**
     * 页面跳转
     * flag 0 客户信息管理
     * flag 1 客户联系人
     * flag 2 客户交往记录
     * flag 3 客户历史订单
     *
     * @return
     */
    @RequestMapping("/index/{flag}")
    public String index(@PathVariable Integer flag, Integer id, Model model) {
        // 如果客户主键不为空，查询客户信息设置至Model
        if (null != id)
            model.addAttribute("customer", customerService.selectById(id));
        if (0 == flag) return "customer";
        if (1 == flag) return "customer_linkman";
        if (2 == flag) return "customer_contact";
        if (3 == flag) return "customer_order";
        return "error";
    }

    /**
     * 分页查询
     *
     * @return
     */
    @RequestMapping("/selectCustomerByParams")
    @ResponseBody
    public Map<String, Object> selectCustomerByParams(CustomerQuery query,
                                                      @RequestParam(defaultValue = "1") Integer page,
                                                      @RequestParam(defaultValue = "10") Integer rows) {
        query.setPageNum(page);
        query.setPageSize(rows);
        return customerService.selectForPage(query);
    }

    /**
     * 查询客户名称
     * @return
     */
    @RequestMapping("/selectCustomerNameList")
    @ResponseBody
    public List<Map<Object, Object>> selectCustomerNameList() {
        return customerService.selectCustomerNameList();
    }

    /**
     * 添加或修改客户
     *
     * @return
     */
    @RequestMapping("/saveOrUpdateCustomer")
    @ResponseBody
    public BaseResult saveOrUpdateCustomer(Customer customer) {
        customerService.saveOrUpdateCustomer(customer);
        return BaseResult.success();
    }

    /**
     * 逻辑删除客户
     *
     * @param ids
     * @return
     */
    @RequestMapping("/deleteBatchCustomer")
    @ResponseBody
    public BaseResult deleteBatchCustomer(Integer[] ids) {
        customerService.deleteBatch(ids);
        return BaseResult.success();
    }

    // -----------------------客户联系人 begin------------------------

    /**
     * 分页查询客户联系人
     *
     * @param query
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/selectCustomerLinkmanByParams")
    @ResponseBody
    public Map<String, Object> selectCustomerLinkmanByParams(CustomerQuery query,
                                                             @RequestParam(defaultValue = "1") Integer page,
                                                             @RequestParam(defaultValue = "10") Integer rows) {
        query.setPageNum(page);
        query.setPageSize(rows);
        return customerLinkmanService.selectForPage(query);
    }

    /**
     * 添加客户联系人
     *
     * @param customerLinkman
     * @return
     */
    @RequestMapping("/saveCustomerLinkman")
    @ResponseBody
    public BaseResult saveCustomerLinkman(CustomerLinkman customerLinkman) {
        customerLinkman.setCeateDate(new Date());
        customerLinkman.setUpdateDate(new Date());
        customerLinkman.setIsValid(1);
        int result = customerLinkmanService.save(customerLinkman);
        return result > 0 ? BaseResult.success() : BaseResult.error();
    }

    /**
     * 修改客户联系人
     *
     * @param customerLinkman
     * @return
     */
    @RequestMapping("/updateCustomerLinkman")
    @ResponseBody
    public BaseResult updateCustomerLinkman(CustomerLinkman customerLinkman) {
        customerLinkman.setUpdateDate(new Date());
        int result = customerLinkmanService.update(customerLinkman);
        return result > 0 ? BaseResult.success() : BaseResult.error();
    }

    /**
     * 逻辑删除客户联系人
     *
     * @param ids
     * @return
     */
    @RequestMapping("/deleteBatchCustomerLinkman")
    @ResponseBody
    public BaseResult deleteBatchCustomerLinkman(Integer[] ids) {
        AssertUtil.isTrue(ArrayUtils.isEmpty(ids), "请选择待删除记录!");
        int result = customerLinkmanService.deleteBatch(ids);
        return result > 0 ? BaseResult.success() : BaseResult.error();
    }
    // -----------------------客户联系人 end------------------------

    // -----------------------客户交往记录 begin------------------------

    /**
     * 分页查询客户交往记录
     *
     * @param query
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/selectCustomerContactByParams")
    @ResponseBody
    public Map<String, Object> selectCustomerContactByParams(CustomerQuery query,
                                                             @RequestParam(defaultValue = "1") Integer page,
                                                             @RequestParam(defaultValue = "10") Integer rows) {
        query.setPageNum(page);
        query.setPageSize(rows);
        return customerContactService.selectForPage(query);
    }

    /**
     * 添加客户交往记录
     *
     * @param customerContact
     * @return
     */
    @RequestMapping("/saveCustomerContact")
    @ResponseBody
    public BaseResult saveCustomerLinkman(CustomerContact customerContact) {
        customerContact.setCreateDate(new Date());
        customerContact.setUpdateDate(new Date());
        customerContact.setIsValid(1);
        int result = customerContactService.save(customerContact);
        return result > 0 ? BaseResult.success() : BaseResult.error();
    }

    /**
     * 修改客户交往记录
     *
     * @param customerContact
     * @return
     */
    @RequestMapping("/updateCustomerContact")
    @ResponseBody
    public BaseResult updateCustomerContact(CustomerContact customerContact) {
        customerContact.setUpdateDate(new Date());
        int result = customerContactService.update(customerContact);
        return result > 0 ? BaseResult.success() : BaseResult.error();
    }

    /**
     * 逻辑删除客户交往记录
     *
     * @param ids
     * @return
     */
    @RequestMapping("/deleteBatchCustomerContact")
    @ResponseBody
    public BaseResult deleteBatchCustomerContact(Integer[] ids) {
        AssertUtil.isTrue(ArrayUtils.isEmpty(ids), "请选择待删除记录!");
        int result = customerContactService.deleteBatch(ids);
        return result > 0 ? BaseResult.success() : BaseResult.error();
    }
    // -----------------------客户交往记录 end------------------------

    // -----------------------客户历史订单 begin------------------------

    /**
     * 分页查询客户历史订单
     *
     * @param query
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/selectCustomerOrderByParams")
    @ResponseBody
    public Map<String, Object> selectCustomerOrderByParams(CustomerQuery query,
                                                           @RequestParam(defaultValue = "1") Integer page,
                                                           @RequestParam(defaultValue = "10") Integer rows) {
        query.setPageNum(page);
        query.setPageSize(rows);
        return customerOrderService.selectForPage(query);
    }

    /**
     * 添加客户历史订单
     *
     * @param customerOrder
     * @return
     */
    @RequestMapping("/saveCustomerOrder")
    @ResponseBody
    public BaseResult saveCustomerLinkman(CustomerOrder customerOrder) {
        customerOrder.setCreateDate(new Date());
        customerOrder.setUpdateDate(new Date());
        customerOrder.setIsValid(1);
        int result = customerOrderService.save(customerOrder);
        return result > 0 ? BaseResult.success() : BaseResult.error();
    }

    /**
     * 修改客户历史订单
     *
     * @param customerOrder
     * @return
     */
    @RequestMapping("/updateCustomerOrder")
    @ResponseBody
    public BaseResult updateCustomerOrder(CustomerOrder customerOrder) {
        customerOrder.setUpdateDate(new Date());
        int result = customerOrderService.update(customerOrder);
        return result > 0 ? BaseResult.success() : BaseResult.error();
    }

    /**
     * 逻辑删除客户历史订单
     *
     * @param ids
     * @return
     */
    @RequestMapping("/deleteBatchCustomerOrder")
    @ResponseBody
    public BaseResult deleteBatchCustomerOrder(Integer[] ids) {
        AssertUtil.isTrue(ArrayUtils.isEmpty(ids), "请选择待删除记录!");
        int result = customerOrderService.deleteBatch(ids);
        return result > 0 ? BaseResult.success() : BaseResult.error();
    }

    /**
     * 根据订单主键查询订单及总金额
     *
     * @param id
     * @return
     */
    @RequestMapping("/selectCustomerOrderAndTotalPriceById")
    @ResponseBody
    public BaseResult selectCustomerOrderAndTotalPriceById(Integer id) {
        BaseResult result = BaseResult.success();
        CustomerOrderDto customerOrderDto = customerOrderService.selectCustomerOrderAndTotalPriceById(id);
        AssertUtil.isTrue(null == customerOrderDto, CrmConstants.OPS_FAILED_MESSAGE);
        result.setResult(customerOrderDto);
        return result;
    }

    /**
     * 根据历史订单ID查询订单详情列表
     *
     * @param query
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/selectOrderDetailsByOrderId")
    @ResponseBody
    public Map<String, Object> selectOrderDetailsByOrderId(CustomerQuery query,
                                                           @RequestParam(defaultValue = "1") Integer page,
                                                           @RequestParam(defaultValue = "10") Integer rows) {
        query.setPageNum(page);
        query.setPageSize(rows);
        return orderDetailsService.selectForPage(query);
    }
    // -----------------------客户历史订单 end------------------------

}
