package com.shsxt.crm.system.dto;

import com.shsxt.crm.system.pojo.User;

public class UserDto extends User {

    // 角色id字符串
    private String roleIdsStr;

    // 角色名称字符串
    private String roleName;

    // 接收前台多个角色id
    private Integer[] roleIds;

    public String getRoleIdsStr() {
        return roleIdsStr;
    }

    public void setRoleIdsStr(String roleIdsStr) {
        this.roleIdsStr = roleIdsStr;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Integer[] roleIds) {
        this.roleIds = roleIds;
    }
}
