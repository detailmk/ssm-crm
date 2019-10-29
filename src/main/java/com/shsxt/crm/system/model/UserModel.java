package com.shsxt.crm.system.model;

/**
 * 用户对象模型
 */
public class UserModel {

    // 用户ID加密后的字符串
    private String userIdStr;
    // 用户密码加密后的字符串
    private String userInfo;
    private String userName;
    private String trueName;

    public String getUserIdStr() {
        return userIdStr;
    }

    public void setUserIdStr(String userIdStr) {
        this.userIdStr = userIdStr;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }
}
