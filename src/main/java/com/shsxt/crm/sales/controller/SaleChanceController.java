package com.shsxt.crm.sales.controller;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.base.BaseResult;
import com.shsxt.crm.base.annotation.RequestPermission;
import com.shsxt.crm.base.util.Base64Util;
import com.shsxt.crm.base.util.CookieUtil;
import com.shsxt.crm.sales.pojo.SaleChance;
import com.shsxt.crm.sales.query.SaleChanceQuery;
import com.shsxt.crm.sales.service.SaleChanceServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 营销机会管理Controller
 */
@Controller
@RequestMapping("/saleChance")
public class SaleChanceController extends BaseController {

    @Autowired
    private SaleChanceServiceI saleChanceService;

    /**
     * 页面跳转
     * flag 0 营销机会管理
     * flag 1 客户开发计划
     */
    @RequestMapping("/index/{flag}")
    public String saleChanceIndex(@PathVariable Integer flag) {
        if (0 == flag)
            return "sale_chance";
        if (1 == flag)
            return "cus_dev_plan";
        return "error";
    }

    /**
     * 分页查询
     *
     * @return
     */
    @RequestPermission(optValue = "101004")
    @RequestMapping("/selectSaleChanceByParams")
    @ResponseBody
    public Map<String, Object> selectSaleChanceByParams(SaleChanceQuery query,
                                                        @RequestParam(defaultValue = "1") Integer page,
                                                        @RequestParam(defaultValue = "10") Integer rows) {
        query.setPageNum(page);
        query.setPageSize(rows);
        return saleChanceService.selectForPage(query);
    }

    /**
     * 添加或修改营销机会
     *
     * @param saleChance
     * @param request
     * @return
     */
    @RequestMapping("/saveOrUpdateSaleChance")
    @ResponseBody
    public BaseResult saveOrUpdateSaleChance(SaleChance saleChance, HttpServletRequest request) {
        // 获取 Cookie 中的用户加密ID
        String userIdStr = CookieUtil.getCookieValue(request, "userIdStr");
        // 解密用户ID
        Integer userId = Integer.valueOf(Base64Util.decoder(userIdStr));
        saleChanceService.saveOrUpdateSaleChance(saleChance, userId);
        return BaseResult.success();
    }

    /**
     * 批量逻辑删除营销机会
     *
     * @param ids
     * @return
     */
    @RequestMapping("/deleteBatchSaleChance")
    @ResponseBody
    public BaseResult deleteBatchSaleChance(Integer[] ids) {
        saleChanceService.deleteBatch(ids);
        return BaseResult.success();
    }

    /**
     * 修改开发状态
     *
     * @param saleChance
     * @return
     */
    @RequestMapping("/updateSaleChanceDevResult")
    @ResponseBody
    public BaseResult updateSaleChanceDevResult(SaleChance saleChance) {
        int result = saleChanceService.updateSaleChanceDevResult(saleChance);
        return result > 0 ? BaseResult.success() : BaseResult.error();
    }

}
