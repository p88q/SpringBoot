package com.god.myjob.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/6/21 19:23
 * @ClassName: Result
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Result {

    /** 失败 */
    public static Integer FAIL = -1;
    /** 返回状态码 */
    private Integer code;
    /** 返回提示信息 */
    private String message;
    /** 返回数据 */
    private Object data;

    /**
     * 返回成功
     * @return
     */
    public static Result getSuccess() {
        return new Result().setCode(HttpStatus.OK.value()).setData(null).setMessage("操作成功");
    }

    /**
     *  返回成功
     * @param message 成功的提示信心
     * @return
     */
    public static Result getSuccess(String message) {
        return new Result().setCode(HttpStatus.OK.value()).setData(null).setMessage(message);
    }

    /**
     * 返回失败
     * @return
     */
    public static Result getFail() {
        return new Result().setCode(Result.FAIL).setData(null).setMessage("操作失败");
    }

    /**
     * 返回失败
     * @param message 失败的提示信息
     * @return
     */
    public static Result getFail(String message) {
        return new Result().setCode(Result.FAIL).setData(null).setMessage(message);
    }
}
