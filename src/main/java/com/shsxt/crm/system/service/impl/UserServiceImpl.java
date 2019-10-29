package com.shsxt.crm.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shsxt.crm.base.BaseQuery;
import com.shsxt.crm.base.constants.CrmConstants;
import com.shsxt.crm.base.util.AssertUtil;
import com.shsxt.crm.base.util.Base64Util;
import com.shsxt.crm.base.util.Md5Util;
import com.shsxt.crm.system.dao.UserMapper;
import com.shsxt.crm.system.dao.UserRoleMapper;
import com.shsxt.crm.system.dto.UserDto;
import com.shsxt.crm.system.model.UserModel;
import com.shsxt.crm.system.pojo.User;
import com.shsxt.crm.system.pojo.UserRole;
import com.shsxt.crm.system.service.UserServiceI;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 用户Service
 */
@Service
@Transactional
public class UserServiceImpl extends UserServiceI {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    /**
     * 用户登录
     *
     * @param userName
     * @param userPwd
     */
    @Override
    public UserModel userLogin(String userName, String userPwd, String rememberMe) {
        // 校验用户名
        AssertUtil.isTrue(StringUtils.isBlank(userName), "用户名不可以为空");
        // 校验密码
        AssertUtil.isTrue(StringUtils.isBlank(userName), "密码不可以为空");
        // 用户是否存在
        User user = userMapper.selectUserByUserName(userName);
        AssertUtil.isTrue(null == user, "用户不存在");
        // 密码是否正确
        AssertUtil.isTrue(!user.getUserPwd().equals(Md5Util.encode(userPwd)), "密码输入错误");
        // 构建用户对象模型
        return buildUserModel(user, userPwd, rememberMe);
    }

    /**
     * 修改密码
     *
     * @param userId
     * @param oldUserPwd
     * @param newUserPwd
     * @param confirmUserPwd
     */
    @Override
    public void updateUserPwd(Integer userId, String oldUserPwd,
                              String newUserPwd, String confirmUserPwd) {
        // 校验原密码
        AssertUtil.isTrue(StringUtils.isBlank(oldUserPwd), "原密码不可以为空");
        // 校验新密码
        AssertUtil.isTrue(StringUtils.isBlank(newUserPwd), "新密码不可以为空");
        // 判断两次密码是否一致
        AssertUtil.isTrue(!newUserPwd.equals(confirmUserPwd), "两次密码不一致");
        // 根据ID查询用户，判断原密码是否输入正确
        UserDto user = userMapper.selectById(userId);
        AssertUtil.isTrue(!user.getUserPwd().equals(Md5Util.encode(oldUserPwd)),
                "原密码输入错误");
        // 设置新密码，需要加密处理
        user.setUserPwd(Md5Util.encode(newUserPwd));
        AssertUtil.isTrue(userMapper.update(user) < 1,
                "密码修改失败");
    }

    /**
     * 查询所有客户经理人
     *
     * @return
     */
    @Override
    public List<Map<Object, Object>> selectCustomerList() {
        return userMapper.selectCustomerList();
    }

    /**
     * 添加或修改用户
     *
     * @param userDto
     */
    @Override
    public void saveOrUpdateUser(UserDto userDto) {
        // 校验参数合法性
        AssertUtil.isTrue(StringUtils.isBlank(userDto.getUserName()), "用户名为空");
        AssertUtil.isTrue(StringUtils.isBlank(userDto.getTrueName()), "真实名称为空");
        AssertUtil.isTrue(StringUtils.isBlank(userDto.getEmail()), "邮箱为空");
        AssertUtil.isTrue(StringUtils.isBlank(userDto.getPhone()), "手机号为空");

        Integer id = userDto.getId();
        // 添加或修改用户信息
        // 添加
        if (null == id) {
            // 用户名唯一校验
            AssertUtil.isTrue(null != userMapper.selectUserByUserName(userDto.getUserName()),
                    "用户名已存在");
            // 默认密码：123456
            userDto.setUserPwd(Md5Util.encode("123456"));
            userDto.setCreateDate(new Date());
            userDto.setUpdateDate(new Date());
            userDto.setIsValid(1);
            // 添加失败
            AssertUtil.isTrue(userMapper.save(userDto) < 1, CrmConstants.OPS_FAILED_MESSAGE);
        } else {
            // 修改
            userDto.setUpdateDate(new Date());
            // 判断用户名是否被更改
            String oldUserName = userMapper.selectById(id).getUserName();
            // 如果用户名被修改，校验用户名唯一
            if (!userDto.getUserName().equals(oldUserName))
                AssertUtil.isTrue(null != userMapper.selectUserByUserName(userDto.getUserName()),
                        "用户名已存在");
            // 修改失败
            AssertUtil.isTrue(userMapper.update(userDto) < 1, CrmConstants.OPS_FAILED_MESSAGE);
            /*
                修改角色：
                    1. 先删除原有角色(删除之前，先查询是否有需要删除的角色)
                    2. 添加新角色
             */
            Integer total = userRoleMapper.selectUserRoleTotalByUserId(id);
            if (total > 0)
                AssertUtil.isTrue(userRoleMapper.deleteUserRoleByUserId(id) != total, CrmConstants.OPS_FAILED_MESSAGE);
        }

        // 添加用户角色关联信息
        Integer[] roleIds = userDto.getRoleIds();
        if (ArrayUtils.isNotEmpty(roleIds)) {
            List<UserRole> userRoles = new ArrayList<>();
            for (Integer roleId : roleIds) {
                UserRole userRole = new UserRole();
                userRole.setUserId(userDto.getId());
                userRole.setRoleId(roleId);
                userRole.setCreateDate(new Date());
                userRole.setUpdateDate(new Date());
                userRoles.add(userRole);
            }
            // 批量用户角色关联信息
            AssertUtil.isTrue(userRoleMapper.saveBatch(userRoles) != userRoles.size(), CrmConstants.OPS_FAILED_MESSAGE);
        }
    }

    /**
     * 逻辑删除用户信息-真实删除用户角色关联信息
     *
     * @param ids
     * @return
     * @throws DataAccessException
     */
    public void deleteBatchUserAndUserRole(Integer[] ids) throws DataAccessException {
        AssertUtil.isTrue(ArrayUtils.isEmpty(ids), "请选择待删除记录");
        AssertUtil.isTrue(userMapper.deleteBatch(ids) != ids.length,
                CrmConstants.OPS_FAILED_MESSAGE);
        // 根据用户ID真实删除用户角色关联信息
        AssertUtil.isTrue(userRoleMapper.deleteBatchRoleByUserIds(ids) < 1,
                CrmConstants.OPS_FAILED_MESSAGE);
    }

    /**
     * 根据用户id查询权限信息
     *
     * @param userId
     * @return
     */
    @Override
    public List<String> selectPermissionByUserId(Integer userId) {
        return userMapper.selectPermissionByUserId(userId);
    }

    /**
     * 分页查询
     *
     * @param baseQuery
     * @return
     * @throws DataAccessException
     */
    @Override
    public Map<String, Object> selectForPage(BaseQuery baseQuery) throws DataAccessException {
        PageHelper.startPage(baseQuery.getPageNum(), baseQuery.getPageSize());
        List<UserDto> entities = userMapper.selectByParams(baseQuery);
        PageInfo<UserDto> pageInfo = new PageInfo<>(entities);

        // 把字符串 1,2,3 改为数组 [1, 2, 3]
        List<UserDto> userDtoList = pageInfo.getList();
        for (UserDto userDto : userDtoList) {
            String roleIdsStr = userDto.getRoleIdsStr();
            if (StringUtils.isNotBlank(roleIdsStr)) {
                String[] roldIdArr = roleIdsStr.split(",");
                Integer[] roleIds = new Integer[roldIdArr.length];
                for (int i = 0; i < roleIds.length; i++) {
                    roleIds[i] = Integer.valueOf(roldIdArr[i]);
                }
                // 设置roleIds
                userDto.setRoleIds(roleIds);
            } else {
                // 用户不存在角色，设置空数组，否则为null页面报错
                userDto.setRoleIds(new Integer[0]);
            }
        }

        Map<String, Object> map = new HashMap<>();
        map.put("total", pageInfo.getTotal());
        map.put("rows", pageInfo.getList());
        return map;
    }

    /**
     * 构建用户对象模型
     *
     * @param user
     * @return
     */
    private UserModel buildUserModel(User user, String userPwd, String rememberMe) {
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(user, userModel);
        userModel.setUserIdStr(Base64Util.encoder(String.valueOf(user.getId())));
        if (StringUtils.isNotBlank(rememberMe) && "true".equals(rememberMe))
            userModel.setUserInfo(Base64Util.encoder(userPwd));
        return userModel;
    }

}
