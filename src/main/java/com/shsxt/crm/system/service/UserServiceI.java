package com.shsxt.crm.system.service;

import com.shsxt.crm.base.BaseService;
import com.shsxt.crm.system.dto.UserDto;
import com.shsxt.crm.system.model.UserModel;

import java.util.List;
import java.util.Map;

/**
 * 用户Service
 */
public abstract class UserServiceI extends BaseService<UserDto> {

    /**
     * 用户登录
     *
     * @param userName
     * @param userPwd
     * @param rememberMe
     * @return
     */
    public abstract UserModel userLogin(String userName, String userPwd, String rememberMe);

    /**
     * 修改密码
     *
     * @param userId
     * @param oldUserPwd
     * @param newUserPwd
     * @param confirmUserPwd
     */
    public abstract void updateUserPwd(Integer userId, String oldUserPwd,
                                       String newUserPwd, String confirmUserPwd);

    /**
     * 查询所有客户经理人
     *
     * @return
     */
    public abstract List<Map<Object, Object>> selectCustomerList();

    /**
     * 添加或修改用户
     *
     * @param userDto
     */
    public abstract void saveOrUpdateUser(UserDto userDto);

    /**
     * 逻辑删除用户信息-真实删除用户角色关联信息
     *
     * @param ids
     */
    public abstract void deleteBatchUserAndUserRole(Integer[] ids);

    /**
     * 根据用户id查询权限信息
     *
     * @param userId
     * @return
     */
    public abstract List<String> selectPermissionByUserId(Integer userId);

}
