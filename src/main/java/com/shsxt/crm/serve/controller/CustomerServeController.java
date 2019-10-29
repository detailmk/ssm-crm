package com.shsxt.crm.serve.controller;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.base.BaseResult;
import com.shsxt.crm.base.util.Base64Util;
import com.shsxt.crm.base.util.CookieUtil;
import com.shsxt.crm.serve.pojo.CustomerServe;
import com.shsxt.crm.serve.query.CustomerServeQuery;
import com.shsxt.crm.serve.service.CustomerServeServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 服务管理Controller
 */
@Controller
@RequestMapping("/customerServe")
public class CustomerServeController extends BaseController {

    @Autowired
    private CustomerServeServiceI customerServeService;

    /**
     * 页面跳转
     * flag 1 服务创建
     * flag 2 服务分配
     * flag 3 服务处理
     * flag 4 服务反馈
     * flag 5 服务归档
     *
     * @param flag
     * @return
     */
    @RequestMapping("/index/{flag}")
    public String index(@PathVariable Integer flag) {
        if (1 == flag) return "customer_serve_create";// 服务创建
        if (2 == flag) return "customer_serve_assign";// 服务分配
        if (3 == flag) return "customer_serve_proce";// 服务处理
        if (4 == flag) return "customer_serve_feed_back";// 服务反馈
        if (5 == flag) return "customer_serve_archive";// 服务归档
        return "error";
    }

    /**
     * 分页查询
     *
     * @return
     */
    @RequestMapping("/selectCustomerServeByParams")
    @ResponseBody
    public Map<String, Object> selectCustomerServeByParams(CustomerServeQuery query,
                                                           @RequestParam(defaultValue = "1") Integer page,
                                                           @RequestParam(defaultValue = "10") Integer rows) {
        query.setPageNum(page);
        query.setPageSize(rows);
        return customerServeService.selectForPage(query);
    }

    /**
     * 服务创建
     *
     * @return
     */
    @RequestMapping("/saveOrUpdateCustomerServe")
    @ResponseBody
    public BaseResult saveOrUpdateCustomerServe(CustomerServe customerServe,
                                          HttpServletRequest request) {
        // 获取 Cookie 中的用户加密ID
        String userIdStr = CookieUtil.getCookieValue(request, "userIdStr");
        // 解密用户ID
        Integer userId = Integer.valueOf(Base64Util.decoder(userIdStr));
        customerServeService.saveOrUpdateCustomerServe(customerServe, userId);
        return BaseResult.success();
    }

}
