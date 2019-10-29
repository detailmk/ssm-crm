package com.shsxt.crm.base.interceptor;

import com.shsxt.crm.base.constants.CrmConstants;
import com.shsxt.crm.base.util.AssertUtil;
import com.shsxt.crm.base.util.Base64Util;
import com.shsxt.crm.base.util.CookieUtil;
import com.shsxt.crm.system.pojo.User;
import com.shsxt.crm.system.service.UserServiceI;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserServiceI userService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        // 获取 Cookie 中的用户加密ID
        String userIdStr = CookieUtil.getCookieValue(request, "userIdStr");
        // 判断 userIdStr 是否存在
        AssertUtil.isNotLogin(StringUtils.isBlank(userIdStr));
        // 解密用户ID
        Integer userId = Integer.valueOf(Base64Util.decoder(userIdStr));
        // 判断用户是否存在
        User user = userService.selectById(userId);
        AssertUtil.isNotLogin(null == user);
        // 将用户信息放入session
        request.getSession().setAttribute("user", user);
        // 将用户权限信息放入session
        request.getSession().setAttribute(CrmConstants.USER_PERMISSIONS,
                userService.selectPermissionByUserId(userId));
        return true;
    }

}
