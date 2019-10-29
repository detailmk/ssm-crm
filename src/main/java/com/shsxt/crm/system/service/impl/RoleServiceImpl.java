package com.shsxt.crm.system.service.impl;

import com.shsxt.crm.base.constants.CrmConstants;
import com.shsxt.crm.base.util.AssertUtil;
import com.shsxt.crm.system.dao.ModuleMapper;
import com.shsxt.crm.system.dao.PermissionMapper;
import com.shsxt.crm.system.dao.RoleMapper;
import com.shsxt.crm.system.dao.UserRoleMapper;
import com.shsxt.crm.system.pojo.Permission;
import com.shsxt.crm.system.pojo.Role;
import com.shsxt.crm.system.service.RoleServiceI;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 角色Service
 */
@Service
public class RoleServiceImpl extends RoleServiceI {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private ModuleMapper moduleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    /**
     * 查询所有角色
     *
     * @return
     */
    @Override
    public List<Map<Object, Object>> selectRoleList() {
        return roleMapper.selectRoleList();
    }

    /**
     * 添加或修改角色信息
     *
     * @param role
     */
    @Override
    public void saveOrUpdateRole(Role role) {
        Integer id = role.getId();
        // 添加
        if (null == id) {
            // 参数校验
            AssertUtil.isTrue(StringUtils.isBlank(role.getRoleName()), "角色名为空");
            // 唯一校验
            AssertUtil.isTrue(null != roleMapper.selectRoleByRoleName(role.getRoleName()),
                    "角色名已存在");
            role.setCreateDate(new Date());
            role.setUpdateDate(new Date());
            role.setIsValid(1);
            AssertUtil.isTrue(roleMapper.save(role) < 1, CrmConstants.OPS_FAILED_MESSAGE);
        } else {
            // 修改
            role.setUpdateDate(new Date());
            // 根据主键查询角色
            String oldRoleName = roleMapper.selectById(id).getRoleName();
            // 如果角色名被修改，校验角色名唯一
            if (!role.getRoleName().equals(oldRoleName))
                AssertUtil.isTrue(null != roleMapper.selectRoleByRoleName(role.getRoleName()),
                        "角色名已存在");
            AssertUtil.isTrue(roleMapper.update(role) < 1, CrmConstants.OPS_FAILED_MESSAGE);
        }
    }

    /**
     * 添加权限
     *
     * @param roleId
     * @param moduleIds
     */
    @Override
    public void doGrant(Integer roleId, Integer[] moduleIds) {
        int total = permissionMapper.selectPermissionTotalByRoleId(roleId);
        // 判断role是否有权限，有就删除，没有不做处理
        if (total > 0)
            AssertUtil.isTrue(permissionMapper.deletePermissionByRoleId(roleId) < total,
                    CrmConstants.OPS_FAILED_MESSAGE);

        // 判断 moduleIds 是否为空
        if (ArrayUtils.isNotEmpty(moduleIds)) {
            List<Permission> permissions = new ArrayList<>();
            for (Integer moduleId : moduleIds) {
                Permission permission = new Permission();
                permission.setRoleId(roleId);// 角色ID
                permission.setModuleId(moduleId);// 模块ID
                permission.setCreateDate(new Date());// 创建时间
                permission.setUpdateDate(new Date());// 更新时间

                // 添加权限码
                permission.setAclValue(moduleMapper.selectById(moduleId).getOptValue());
                permissions.add(permission);
            }
            // 添加权限
            AssertUtil.isTrue(permissionMapper.saveBatch(permissions) < permissions.size(),
                    CrmConstants.OPS_FAILED_MESSAGE);
        }
    }

    /**
     * 批量删除角色
     *
     * @param ids
     */
    public void deleteBatchRole(Integer[] ids) {
        if (ArrayUtils.isNotEmpty(ids)) {
            for (Integer id : ids) {
                // 删除角色
                AssertUtil.isTrue(roleMapper.deleteOne(id) < 1,
                        CrmConstants.OPS_FAILED_MESSAGE);

                // 删除用户角色关联信息
                Integer total = userRoleMapper.selectUserRoleTotalByRoleId(id);
                if (total > 0)
                    AssertUtil.isTrue(userRoleMapper.deleteUserRoleByRoleId(id) < total,
                            CrmConstants.OPS_FAILED_MESSAGE);

                // 删除角色权限
                total = permissionMapper.selectPermissionTotalByRoleId(id);
                if (total > 0)
                    AssertUtil.isTrue(permissionMapper.deletePermissionByRoleId(id) < total,
                            CrmConstants.OPS_FAILED_MESSAGE);
            }
        }
    }

}
