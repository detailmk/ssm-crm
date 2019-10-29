package com.shsxt.crm.base.util;

import com.shsxt.crm.base.exception.LoginException;
import com.shsxt.crm.base.exception.ParamsException;

/**
 * 断言工具类
 */
public class AssertUtil {

    public static void isTrue(boolean flag) {
        if (flag)
            throw new ParamsException();
    }

    public static void isTrue(boolean flag, String message) {
        if (flag)
            throw new ParamsException(message);
    }

    public static void isTrue(boolean flag, Integer code, String message) {
        if (flag)
            throw new ParamsException(code, message);
    }

    public static void isNotLogin(boolean flag) {
        if (flag)
            throw new LoginException();
    }

    public static void isNotLogin(boolean flag, String message) {
        if (flag)
            throw new LoginException(message);
    }

}
