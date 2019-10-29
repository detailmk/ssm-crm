package com.shsxt.crm.system.dao;

import com.shsxt.crm.base.BaseDao;
import com.shsxt.crm.system.pojo.Permission;

/**
 * 权限Mapper
 */
public interface PermissionMapper extends BaseDao<Permission> {

    /**
     * 根据角色id统计权限
     *
     * @param roleId
     * @return
     */
    int selectPermissionTotalByRoleId(Integer roleId);

    /**
     * 根据角色id删除权限
     *
     * @param roleId
     * @return
     */
    int deletePermissionByRoleId(Integer roleId);

    /**
     * 根据模块id修改权限码
     *
     * @param permission
     * @return
     */
    int updateAclValueByModuleId(Permission permission);

    /**
     * 根据权限码统计模块权限关联信息
     *
     * @param aclValue
     * @return
     */
    int selectPermissionTotalByAclValue(String aclValue);

    /**
     * 根据权限码删除模块权限关联信息
     *
     * @param aclValue
     * @return
     */
    int deletePermissionByAclValue(String aclValue);

}
