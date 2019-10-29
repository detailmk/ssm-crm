package com.shsxt.crm.base.exception;

import com.shsxt.crm.base.constants.CrmConstants;

/**
 * 登录异常
 */
public class LoginException extends RuntimeException {

    private Integer code = CrmConstants.OPS_FAILED_CODE;// 400
    private String message = "用户未登录或不存在";

    public LoginException() {
    }

    public LoginException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public LoginException(Integer code) {
        super("用户未登录或不存在");
        this.code = code;
    }

    public LoginException(String message) {
        super(message);
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
