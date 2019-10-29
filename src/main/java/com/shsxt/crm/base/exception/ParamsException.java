package com.shsxt.crm.base.exception;

/**
 * 参数异常
 */
public class ParamsException extends RuntimeException {

    private Integer code = 400;
    private String message = "参数异常";

    public ParamsException() {
    }

    public ParamsException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public ParamsException(Integer code) {
        super("参数异常");
        this.code = code;
    }

    public ParamsException(String message) {
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
