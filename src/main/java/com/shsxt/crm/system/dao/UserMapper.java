package com.shsxt.crm.system.dao;

import com.shsxt.crm.base.BaseDao;
import com.shsxt.crm.system.dto.UserDto;
import com.shsxt.crm.system.pojo.User;

import java.util.List;
import java.util.Map;

/**
 * 用户Mapper
 */
public interface UserMapper extends BaseDao<UserDto> {

    /**
     * 根据用户名查询用户
     *
     * @param userName
     * @return
     */
    User selectUserByUserName(String userName);

    /**
     * 查询所有客户经理人
     *
     * @return
     */
    List<Map<Object, Object>> selectCustomerList();

    /**
     * 根据用户id查询权限信息
     *
     * @param userId
     * @return
     */
    List<String> selectPermissionByUserId(Integer userId);

}