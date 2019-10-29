package com.shsxt.crm.system.dao;

import com.shsxt.crm.base.BaseDao;
import com.shsxt.crm.system.dto.ModuleDto;
import com.shsxt.crm.system.pojo.Module;

import java.util.List;
import java.util.Map;

/**
 * 模块Mapper
 */
public interface ModuleMapper extends BaseDao<Module> {

    /**
     * 查询所有权限并根据角色查询所拥有的权限
     *
     * @return
     */
    List<ModuleDto> selectAllModuleListByRoleId(Integer roleId);

    /**
     * 根据菜单等级查询上级菜单
     *
     * @param grade
     * @return
     */
    List<Map<Object, Object>> selectModuleByGrade(Integer grade);

    /**
     * 根据模块名查询模块
     *
     * @param moduleName
     * @return
     */
    Module selectModuleByModuleName(String moduleName);

    /**
     * 根据权限码查询模块
     *
     * @param optValue
     * @return
     */
    Module selectModuleByOptValue(String optValue);

    /**
     * 根据主键自查询子模块
     *
     * @param id
     * @return
     */
    List<Module> selectModuleChildrenListById(Integer id);

    /**
     * 根据权限码统计模块及其子模块
     *
     * @param optValue
     * @return
     */
    int selectModuleTotalByOptValue(String optValue);

    /**
     * 根据权限码逻辑删除模块及其子模块
     *
     * @param optValue
     * @return
     */
    int deleteModuleByOptValue(String optValue);

}
