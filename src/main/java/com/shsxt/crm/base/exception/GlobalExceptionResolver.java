package com.shsxt.crm.base.exception;

import com.alibaba.fastjson.JSON;
import com.shsxt.crm.base.BaseResult;
import com.shsxt.crm.base.constants.CrmConstants;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * 全局异常处理类
 */
@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Object handler, Exception ex) {

        // 构建默认视图对象
        ModelAndView mv = getDefaultModelAndView(request, ex);

        // 如果请求出现异常的方法不属于HandlerMethod
        if (!(handler instanceof HandlerMethod))
            return mv;

        // 如果请求出现异常的方法属于HandlerMethod
        // 判断请求是跳转页面还是返回JSON
        // 获取@ResponseBody注解
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        ResponseBody responseBody = method.getAnnotation(ResponseBody.class);
        // 如果注解存在，返回JSON
        if (null != responseBody) {
            BaseResult baseResult = new BaseResult(CrmConstants.OPS_FAILED_CODE,
                    CrmConstants.OPS_FAILED_MESSAGE);

            // 判断异常是否属于参数异常
            if (ex instanceof ParamsException) {
                ParamsException pe = (ParamsException) ex;
                baseResult.setCode(pe.getCode());
                baseResult.setMessage(pe.getMessage());
            }

            // 判断异常是否属于登录异常
            if (ex instanceof LoginException) {
                LoginException le = (LoginException) ex;
                baseResult.setCode(le.getCode());
                baseResult.setMessage(le.getMessage());
            }

            // 初始化 Response 构建JSON对象
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json;charset=utf-8");
            PrintWriter pw = null;
            try {
                pw = response.getWriter();
                pw.write(JSON.toJSONString(baseResult));
                pw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (null != pw)
                    pw.close();
            }
            return null;
            // 如果注解不存在，返回页面
        } else {
            // 判断异常是否属于参数异常
            if (ex instanceof ParamsException) {
                ParamsException pe = (ParamsException) ex;
                mv.addObject("errorMsg", pe.getMessage());
                mv.addObject("errorCode", pe.getCode());
            }

            // 判断异常是否属于登录异常
            if (ex instanceof LoginException) {
                LoginException le = (LoginException) ex;
                mv.addObject("errorMsg", le.getMessage());
                mv.addObject("errorCode", le.getCode());
            }
            return mv;
        }
    }

    /**
     * 构建默认视图对象
     *
     * @param request
     * @param ex
     * @return
     */
    private ModelAndView getDefaultModelAndView(HttpServletRequest request, Exception ex) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("error");
        mv.addObject("ctx", request.getContextPath());
        mv.addObject("uri", request.getRequestURI());
        mv.addObject("errorMsg", CrmConstants.OPS_FAILED_MESSAGE);
        mv.addObject("errorCode", CrmConstants.OPS_FAILED_CODE);
        return mv;
    }

}
