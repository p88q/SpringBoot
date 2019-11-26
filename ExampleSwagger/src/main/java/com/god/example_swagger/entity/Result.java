package com.god.example_swagger.entity;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/9/3 18:43
 * @ClassName: Result
 * @Description:
 */
public class Result implements Serializable {

    /** 成功状态码 */
    public static final int SUCCESS = 0;

    /** 后台失败状态码 */
    public static final int FAIL = 1000;

    /** 未知错误 */
    private static final int UNKNOWN = 500;

    private Integer status;

    private String message;

    private Long total;

    private Object data;

    public Result() {
    }

    public Result(Integer status, String message, Long total, Object data) {
        this.status = status;
        this.message = message;
        this.total = total;
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
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


    public static Result isOK() {
        return isOK(null);
    }

    public static Result isOK(Object data) {
        return isOK("成功!", null);
    }


    public static Result isOK(String message, Object data) {
        return new Result(SUCCESS, message, null, data);
    }

    public static Result fail() {
        return fail("操作失败!");
    }

    public static Result fail(String message) {
        return fail(FAIL, message);
    }

    public static Result fail(Integer code, String message) {
        return fail(code, message, null);
    }

    public static Result fail(Integer code, String message, Long total) {
        return new Result(code, message, total, null);
    }
}