package com.shsxt.crm.system.controller;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.system.service.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 跳转页面Controller
 */
@Controller
public class PageController extends BaseController {

    @Autowired
    private UserServiceI userService;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

}
