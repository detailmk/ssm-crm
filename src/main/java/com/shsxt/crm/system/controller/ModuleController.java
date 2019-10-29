package com.shsxt.crm.system.controller;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.base.BaseResult;
import com.shsxt.crm.system.dto.ModuleDto;
import com.shsxt.crm.system.pojo.Module;
import com.shsxt.crm.system.query.ModuleQuery;
import com.shsxt.crm.system.service.ModuleServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 模块Controller
 */
@Controller
@RequestMapping("/module")
public class ModuleController extends BaseController {

    @Autowired
    private ModuleServiceI moduleService;

    /**
     * 查询所有权限并根据角色查询所拥有的权限
     *
     * @param roleId
     * @return
     */
    @RequestMapping("/selectAllModuleListByRoleId")
    @ResponseBody
    public List<ModuleDto> selectAllModuleListByRoleId(Integer roleId) {
        return moduleService.selectAllModuleListByRoleId(roleId);
    }

    /**
     * 跳转模块首页
     *
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "module";
    }

    /**
     * 分页查询模块信息
     *
     * @param query
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/selectModuleByParams")
    @ResponseBody
    public Map<String, Object> selectModuleByParams(ModuleQuery query,
                                                    @RequestParam(defaultValue = "1") Integer page,
                                                    @RequestParam(defaultValue = "10") Integer rows) {
        query.setPageNum(page);
        query.setPageSize(rows);
        return moduleService.selectForPage(query);
    }

    /**
     * 添加或修改模块
     *
     * @param module
     * @return
     */
    @RequestMapping("/saveOrUpdateModule")
    @ResponseBody
    public BaseResult saveOrUpdateModule(Module module) {
        moduleService.saveOrUpdateModule(module);
        return BaseResult.success();
    }

    /**
     * 根据菜单等级查询上级菜单
     *
     * @param grade
     * @return
     */
    @RequestMapping("/selectModuleByGrade")
    @ResponseBody
    public List<Map<Object, Object>> selectModuleByGrade(Integer grade) {
        return moduleService.selectModuleByGrade(grade);
    }

    /**
     * 根据权限码逻辑删除模块及其子模块
     *
     * @return
     */
    @RequestMapping("/deleteModuleByOptValue")
    @ResponseBody
    public BaseResult deleteModuleByOptValue(Integer moduleId) {
        moduleService.deleteModuleByOptValue(moduleId);
        return BaseResult.success();
    }

}
