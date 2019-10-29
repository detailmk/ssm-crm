package com.shsxt.crm.system.service.impl;

import com.shsxt.crm.base.constants.CrmConstants;
import com.shsxt.crm.base.util.AssertUtil;
import com.shsxt.crm.system.dao.ModuleMapper;
import com.shsxt.crm.system.dao.PermissionMapper;
import com.shsxt.crm.system.dto.ModuleDto;
import com.shsxt.crm.system.pojo.Module;
import com.shsxt.crm.system.pojo.Permission;
import com.shsxt.crm.system.service.ModuleServiceI;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 模块Service
 */
@Service
public class ModuleServiceImpl extends ModuleServiceI {

    @Autowired
    private ModuleMapper moduleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 查询所有权限并根据角色查询所拥有的权限
     *
     * @param roleId
     * @return
     */
    @Override
    public List<ModuleDto> selectAllModuleListByRoleId(Integer roleId) {
        return moduleMapper.selectAllModuleListByRoleId(roleId);
    }

    /**
     * 添加或修改模块
     *
     * @param module
     */
    @Override
    public void saveOrUpdateModule(Module module) {
        Integer id = module.getId();
        // 添加
        if (null == id) {
            // 校验模块参数合法性
            checkModuleParams(module);
            module.setCreateDate(new Date());
            module.setUpdateDate(new Date());
            module.setIsValid((byte) 1);
            AssertUtil.isTrue(moduleMapper.save(module) < 1, CrmConstants.OPS_FAILED_MESSAGE);
        } else {
            // 修改
            // 存在子模块不允许修改
            List<Module> moduleList = moduleMapper.selectModuleChildrenListById(id);
            AssertUtil.isTrue(CollectionUtils.isEmpty(moduleList) || moduleList.size() < 1,
                    "该模块存在子模块无法修改");
            // 校验模块参数合法性
            checkModuleParams(module);
            AssertUtil.isTrue(moduleMapper.update(module) < 1, CrmConstants.OPS_FAILED_MESSAGE);
            // 根据模块id修改权限码
            Permission permission = new Permission();
            permission.setModuleId(id);// 模块id
            permission.setAclValue(module.getOptValue());// 权限码
            AssertUtil.isTrue(permissionMapper.updateAclValueByModuleId(permission) < 1,
                    CrmConstants.OPS_FAILED_MESSAGE);
        }
    }

    /**
     * 校验模块参数合法性
     *
     * @param module
     */
    private void checkModuleParams(Module module) {
        // 模块名，权限名，菜单层级不可以为空
        AssertUtil.isTrue(StringUtils.isBlank(module.getModuleName()), "模块名为空");
        AssertUtil.isTrue(StringUtils.isBlank(module.getOptValue()), "权限名为空");
        AssertUtil.isTrue(null == module.getGrade(), "菜单层级为空");
        // 模块名是否唯一
        AssertUtil.isTrue(null != moduleMapper.selectModuleByModuleName(module.getModuleName()),
                "模块名已存在");
        // 权限码是否唯一
        AssertUtil.isTrue(null != moduleMapper.selectModuleByOptValue(module.getOptValue()),
                "权限码已存在");
        // 如果选了非根级菜单，上级菜单不可以为空
        if (0 != module.getGrade()) {
            AssertUtil.isTrue(null == module.getParentId(), "上级菜单为空");
            // 校验权限码格式：根级无需校验，一级菜单10XX，二级菜单10XXXX
            // 根据id查询上级菜单并获取权限码
            String parentOptValue = moduleMapper.selectById(module.getParentId()).getOptValue();
            AssertUtil.isTrue(!module.getOptValue().startsWith(parentOptValue),
                    "权限码格式错误，应该为 " + parentOptValue + "XX");
        }
        /*
            校验权限码位数：
                根级菜单2位 0 * 2 + 2
                一级菜单4位 1 * 2 + 2
                二级菜单6位 2 * 2 + 2
         */
        int len = module.getGrade() * 2 + 2;
        AssertUtil.isTrue(len != module.getOptValue().length(),
                "权限码位数错误，应该为 " + len + " 位数字");
    }

    /**
     * 根据菜单等级查询上级菜单
     *
     * @param grade
     * @return
     */
    @Override
    public List<Map<Object, Object>> selectModuleByGrade(Integer grade) {
        return moduleMapper.selectModuleByGrade(grade);
    }

    /**
     * 根据权限码逻辑删除模块及其子模块
     *
     * @param moduleId
     * @return
     */
    @Override
    public void deleteModuleByOptValue(Integer moduleId) {
        // 根据主键查询模块
        Module module = moduleMapper.selectById(moduleId);
        AssertUtil.isTrue(null == module, "模块不存在");
        // 删除模块信息
        int total = moduleMapper.selectModuleTotalByOptValue(module.getOptValue());
        if (total > 0)
            AssertUtil.isTrue(moduleMapper.deleteModuleByOptValue(module.getOptValue()) != total,
                    CrmConstants.OPS_FAILED_MESSAGE);
        // 删除模块权限关联信息
        total = permissionMapper.selectPermissionTotalByAclValue(module.getOptValue());
        if (total > 0)
            AssertUtil.isTrue(permissionMapper.deletePermissionByAclValue(module.getOptValue()) != total,
                    CrmConstants.OPS_FAILED_MESSAGE);
    }

}
