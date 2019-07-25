package com.god.util;

import com.alibaba.fastjson.JSONObject;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/6/25 00:03
 * @ClassName: Result
 * @Description: 返回数据类型
 */
public class Result {

    /** 返回状态码 */
    private String code;
    /** 返回提示信息 */
    private String message;
    /** 请求的随机数 */
    private String contextId;
    /** 返回的数据信息 */
    private Object data;

    public Result() {}

    public Result(String code, String message, String contextId, Object data) {
        this.code = code;
        this.message = message;
        this.contextId = contextId;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getContextId() {
        return contextId;
    }

    public void setContextId(String contextId) {
        this.contextId = contextId;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 重写toString方法
     * @return 返回JSON字符串
     */
    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

    /**
     * 返回成功
     * @return
     */
    public static Result getSuccess() {
        return new Result(Constants.RESULT_SUCCESS, Constants.MESSAGE_SUCCESS, null, null);
    }

    /**
     * 返回成功
     * @param message 返回提示信息
     * @return
     */
    public static Result getSuccess(String message) {
        return new Result(Constants.RESULT_SUCCESS, message, null, null);
    }

    /**
     * 返回成功
     * @param message 成功的提示信息
     * @param data 返回的数据
     * @return
     */
    public static Result getSuccess(String message, Object data) {
        return new Result(Constants.RESULT_SUCCESS, message, null, data);
    }

    /**
     * 返回成功
     * @param message 返回的提示信息
     * @param contextId 请求的随机数
     * @param data 返回的提示信息
     * @return
     */
    public static Result getSuccess(String message, String contextId, Object data) {
        return new Result(Constants.RESULT_SUCCESS, message, contextId, data);
    }

    /**
     * 返回失败
     * @return
     */
     public static Result getFail() {
        return new Result(Constants.RESULT_FAIL, Constants.MESSAGE_FAIL, null, null);
     }

    /**
     * 返回失败
     * @param message 返回失败的提示信息
     * @return
     */
    public static Result getFail(String code, String message) {
        return new Result(code, message, null, null);
    }

    /**
     * 返回失败
     * @param code 失败的状态码
     * @param message 失败信息
     * @param contextId 失败的请求id
     * @return
     */
    public static Result getFail(String code, String message, String contextId) {
        return new Result(code, message, contextId, null);
    }

    /**
     * 返回失败
     * @param code 失败的状态码
     * @param message 失败的返回信息
     * @param contextId 失败的请求id
     * @param data 失败返回的数据
     * @return
     */
    public static Result getFail(String code, String message, String contextId, Object data) {
        return new Result(code, message, contextId, data);
    }
}
