package com.shsxt.crm.system.service;

import com.shsxt.crm.base.BaseService;
import com.shsxt.crm.system.pojo.Role;

import java.util.List;
import java.util.Map;

/**
 * 角色Service
 */
public abstract class RoleServiceI extends BaseService<Role> {

    /**
     * 查询所有角色
     *
     * @return
     */
    public abstract List<Map<Object, Object>> selectRoleList();

    /**
     * 添加或修改角色信息
     *
     * @param role
     */
    public abstract void saveOrUpdateRole(Role role);

    /**
     * 添加权限
     *
     * @param roleId
     * @param moduleIds
     */
    public abstract void doGrant(Integer roleId, Integer[] moduleIds);

    /**
     * 批量删除角色
     *
     * @param ids
     */
    public abstract void deleteBatchRole(Integer[] ids);

}
