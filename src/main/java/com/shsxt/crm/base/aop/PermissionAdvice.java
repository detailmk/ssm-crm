package com.shsxt.crm.base.aop;

import com.shsxt.crm.base.annotation.RequestPermission;
import com.shsxt.crm.base.constants.CrmConstants;
import com.shsxt.crm.base.util.AssertUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 权限通知
 */
@Component
@Aspect// 切面
public class PermissionAdvice {

    @Autowired
    private HttpSession session;

    // 切入点
    @Pointcut("@annotation(com.shsxt.crm.base.annotation.RequestPermission)")
    public void cut() {
    }

    // 环绕通知
    @Around(value = "cut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        // 方法执行前
        Object obj = null;
        // 获取方法签名
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();
        RequestPermission requestPermission = method.getAnnotation(RequestPermission.class);
        if (null != requestPermission) {
            // 请求目标方法的权限码
            System.out.println("权限码：" + requestPermission.optValue());
            // 获取当前用户所有权限码
            List<String> permissions = (List<String>) session.getAttribute(CrmConstants.USER_PERMISSIONS);
            // 权限是否为空
            AssertUtil.isTrue(CollectionUtils.isEmpty(permissions) || permissions.size() < 1,
                    "暂无权限");
            // 当前用户权限是否包含请求目标方法的权限
            AssertUtil.isTrue(!(permissions.contains(requestPermission.optValue())), "暂无权限");
        }
        // 执行目标对象方法
        obj = pjp.proceed();
        // 方法执行后...
        return obj;
    }

}
