package com.shsxt.crm.system.controller;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.base.BaseResult;
import com.shsxt.crm.base.util.Base64Util;
import com.shsxt.crm.base.util.CookieUtil;
import com.shsxt.crm.system.dto.UserDto;
import com.shsxt.crm.system.model.UserModel;
import com.shsxt.crm.system.query.UserQuery;
import com.shsxt.crm.system.service.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 用户Controller
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserServiceI userService;

    /**
     * 用户登录
     *
     * @param userName
     * @param userPwd
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public BaseResult login(String userName, String userPwd, String rememberMe,
                            HttpServletRequest request) {
        UserModel userModel = userService.userLogin(userName, userPwd, rememberMe);
        return BaseResult.success("登录成功", userModel);
    }

    /**
     * 安全退出
     *
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        // 清除用户 session
        request.getSession().removeAttribute("user");
        return "login";
    }

    /**
     * 修改密码
     *
     * @param oldUserPwd
     * @param newUserPwd
     * @param confirmUserPwd
     * @return
     */
    @RequestMapping("/updateUserPwd")
    @ResponseBody
    public BaseResult updateUserPwd(String oldUserPwd,
                                    String newUserPwd, String confirmUserPwd,
                                    HttpServletRequest request) {
        // 获取 Cookie 中的用户加密ID
        String userIdStr = CookieUtil.getCookieValue(request, "userIdStr");
        // 解密用户ID
        Integer userId = Integer.valueOf(Base64Util.decoder(userIdStr));
        userService.updateUserPwd(userId, oldUserPwd, newUserPwd, confirmUserPwd);
        return BaseResult.success();
    }

    /**
     * 查询所有客户经理人
     *
     * @return
     */
    @RequestMapping("/selectCustomerList")
    @ResponseBody
    public List<Map<Object, Object>> selectCustomerList() {
        return userService.selectCustomerList();
    }

    /**
     * 分页查询用户信息
     *
     * @param query
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/selectUserByParams")
    @ResponseBody
    public Map<String, Object> selectUserByParams(UserQuery query,
                                                  @RequestParam(defaultValue = "1") Integer page,
                                                  @RequestParam(defaultValue = "10") Integer rows) {
        query.setPageNum(page);
        query.setPageSize(rows);
        return userService.selectForPage(query);
    }

    /**
     * 跳转用户管理页面
     *
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "user";
    }

    /**
     * 添加或修改用户
     *
     * @param userDto
     * @return
     */
    @RequestMapping("/saveOrUpdateUser")
    @ResponseBody
    public BaseResult saveOrUpdateUser(UserDto userDto) {
        userService.saveOrUpdateUser(userDto);
        return BaseResult.success();
    }

    /**
     * 逻辑删除用户信息-真实删除用户角色关联信息
     *
     * @param ids
     * @return
     */
    @RequestMapping("/deleteBatchUser")
    @ResponseBody
    public BaseResult deleteBatchUser(Integer[] ids) {
        userService.deleteBatchUserAndUserRole(ids);
        return BaseResult.success();
    }

}
