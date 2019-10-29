package com.shsxt.crm.system.service;

import com.shsxt.crm.base.BaseService;
import com.shsxt.crm.system.dto.ModuleDto;
import com.shsxt.crm.system.pojo.Module;

import java.util.List;
import java.util.Map;

/**
 * 模块Service
 */
public abstract class ModuleServiceI extends BaseService<Module> {

    /**
     * 查询所有权限并根据角色查询所拥有的权限
     *
     * @param roleId
     * @return
     */
    public abstract List<ModuleDto> selectAllModuleListByRoleId(Integer roleId);

    /**
     * 添加或修改模块
     *
     * @param module
     */
    public abstract void saveOrUpdateModule(Module module);

    /**
     * 根据菜单等级查询上级菜单
     *
     * @param grade
     * @return
     */
    public abstract List<Map<Object, Object>> selectModuleByGrade(Integer grade);

    /**
     * 根据权限码逻辑删除模块及其子模块
     *
     * @param moduleId
     * @return
     */
    public abstract void deleteModuleByOptValue(Integer moduleId);

}
