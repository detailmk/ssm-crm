package com.shsxt.crm.system.dao;

import com.shsxt.crm.base.BaseDao;
import com.shsxt.crm.system.pojo.UserRole;

/**
 * 用户角色Mapper
 */
public interface UserRoleMapper extends BaseDao<UserRole> {

    /**
     * 根据用户id删除用户角色关联信息
     *
     * @param userId
     * @return
     */
    int deleteUserRoleByUserId(Integer userId);

    /**
     * 根据角色id删除用户角色关联信息
     *
     * @param roleId
     * @return
     */
    int deleteUserRoleByRoleId(Integer roleId);

    /**
     * 根据用户id统计角色数量
     *
     * @param userId
     * @return
     */
    int selectUserRoleTotalByUserId(Integer userId);

    /**
     * 根据角色id统计角色数量
     *
     * @param roleId
     * @return
     */
    int selectUserRoleTotalByRoleId(Integer roleId);

    /**
     * 批量删除用户角色关联信息
     *
     * @param ids
     * @return
     */
    int deleteBatchRoleByUserIds(Integer[] ids);

}