package com.shsxt.crm.system.dao;

import com.shsxt.crm.base.BaseDao;
import com.shsxt.crm.system.pojo.Role;

import java.util.List;
import java.util.Map;

/**
 * 角色Mapper
 */
public interface RoleMapper extends BaseDao<Role> {

    /**
     * 查询所有角色
     *
     * @return
     */
    List<Map<Object, Object>> selectRoleList();

    /**
     * 根据角色名查询角色
     *
     * @param roleName
     * @return
     */
    Role selectRoleByRoleName(String roleName);

    /**
     * 单个逻辑删除角色
     *
     * @param id
     * @return
     */
    int deleteOne(Integer id);

}