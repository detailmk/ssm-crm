package com.shsxt.crm.base;

import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

/**
 * 公共Controller
 */
public class BaseController {

    /**
     * 在所有Controller方法执行前执行
     *
     * @param request
     */
    @ModelAttribute
    public void preMethod(HttpServletRequest request) {
        request.setAttribute("ctx", request.getContextPath());
    }

}
