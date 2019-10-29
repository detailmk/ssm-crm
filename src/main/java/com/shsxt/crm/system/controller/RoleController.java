package com.shsxt.crm.system.controller;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.base.BaseResult;
import com.shsxt.crm.system.pojo.Role;
import com.shsxt.crm.system.query.RoleQuery;
import com.shsxt.crm.system.service.RoleServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 角色Controller
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

    @Autowired
    private RoleServiceI roleService;

    /**
     * 查询所有角色
     *
     * @return
     */
    @RequestMapping("/selectRoleList")
    @ResponseBody
    public List<Map<Object, Object>> selectRoleList() {
        return roleService.selectRoleList();
    }

    /**
     * 跳转角色管理页面
     *
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "role";
    }

    /**
     * 分页查询角色信息
     *
     * @param query
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/selectRoleByParams")
    @ResponseBody
    public Map<String, Object> selectRoleByParams(RoleQuery query,
                                                  @RequestParam(defaultValue = "1") Integer page,
                                                  @RequestParam(defaultValue = "10") Integer rows) {
        query.setPageNum(page);
        query.setPageSize(rows);
        return roleService.selectForPage(query);
    }

    /**
     * 添加或修改角色
     *
     * @param role
     * @return
     */
    @RequestMapping("/saveOrUpdateRole")
    @ResponseBody
    public BaseResult saveOrUpdateRole(Role role) {
        roleService.saveOrUpdateRole(role);
        return BaseResult.success();
    }

    /**
     * 删除角色
     *
     * @param ids
     * @return
     */
    @RequestMapping("/deleteBatchRole")
    @ResponseBody
    public BaseResult deleteBatchRole(Integer[] ids) {
        roleService.deleteBatchRole(ids);
        return BaseResult.success();
    }

    /**
     * 添加权限
     *
     * @param roleId
     * @param moduleIds
     * @return
     */
    @RequestMapping("/doGrant")
    @ResponseBody
    public BaseResult doGrant(Integer roleId, Integer[] moduleIds) {
        roleService.doGrant(roleId, moduleIds);
        return BaseResult.success();
    }

}
