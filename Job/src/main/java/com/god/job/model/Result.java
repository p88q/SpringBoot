package com.god.job.model;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/6/21 19:21
 * @ClassName: Result
 * @Description: 返回对象
 */
@Data
public class Result {
    private String message;
    private Object data;

    public Result(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public static Result getSuccess() {
        return getSuccess("执行成功");
    }

    public static Result getSuccess(Object message) {
        return new Result(HttpStatus.OK.getReasonPhrase(), message);
    }

    public static Result getFail() {
        return getFail("执行失败");
    }

    public static Result getFail(String message) {
        return new Result("-1", message);
    }
}
