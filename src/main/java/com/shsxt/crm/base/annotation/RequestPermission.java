package com.shsxt.crm.base.annotation;

import java.lang.annotation.*;

/**
 * 权限自定义注解
 */
@Target({ElementType.METHOD})// 基于方法层
@Retention(RetentionPolicy.RUNTIME)// 运行时
@Documented
public @interface RequestPermission {

    // 权限码
    String optValue() default "";

}
