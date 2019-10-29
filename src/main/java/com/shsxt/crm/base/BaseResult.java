package com.shsxt.crm.base;

import com.shsxt.crm.base.constants.CrmConstants;

import java.io.Serializable;

/**
 * 公共返回对象
 */
public class BaseResult implements Serializable {

    // 状态编码
    private Integer code;
    // 状态描述
    private String message;
    // 返回对象
    private Object result;

    // 成功返回的对象
    public static BaseResult success() {
        BaseResult baseResult = new BaseResult();
        baseResult.setCode(CrmConstants.OPS_SUCCESS_CODE);
        baseResult.setMessage(CrmConstants.OPS_SUCCESS_MESSAGE);
        return baseResult;
    }

    // 成功返回的对象
    public static BaseResult success(String message) {
        BaseResult baseResult = new BaseResult();
        baseResult.setCode(CrmConstants.OPS_SUCCESS_CODE);
        baseResult.setMessage(message);
        return baseResult;
    }

    // 成功返回的对象
    public static BaseResult success(String message, Object result) {
        BaseResult baseResult = new BaseResult();
        baseResult.setCode(CrmConstants.OPS_SUCCESS_CODE);
        baseResult.setMessage(message);
        baseResult.setResult(result);
        return baseResult;
    }

    // 失败返回对象
    public static BaseResult error() {
        BaseResult baseResult = new BaseResult();
        baseResult.setCode(CrmConstants.OPS_FAILED_CODE);
        baseResult.setMessage(CrmConstants.OPS_FAILED_MESSAGE);
        return baseResult;
    }

    // 失败返回对象
    public static BaseResult error(String message) {
        BaseResult baseResult = new BaseResult();
        baseResult.setCode(CrmConstants.OPS_FAILED_CODE);
        baseResult.setMessage(message);
        return baseResult;
    }

    // 失败返回对象
    public static BaseResult error(String message, Object result) {
        BaseResult baseResult = new BaseResult();
        baseResult.setCode(CrmConstants.OPS_FAILED_CODE);
        baseResult.setMessage(message);
        baseResult.setResult(result);
        return baseResult;
    }

    public BaseResult() {
    }

    public BaseResult(String message) {
        this.message = message;
    }

    public BaseResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseResult(Integer code, Object result) {
        this.code = code;
        this.result = result;
    }

    public BaseResult(String message, Object result) {
        this.message = message;
        this.result = result;
    }

    public BaseResult(Integer code, String message, Object result) {
        this.code = code;
        this.message = message;
        this.result = result;
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

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
