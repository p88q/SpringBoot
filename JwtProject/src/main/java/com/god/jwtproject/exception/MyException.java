package com.god.jwtproject.exception;

import lombok.Data;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/6/19 14:58
 * @ClassName: MyException
 * @Description:
 */
@Data
public class MyException extends RuntimeException {

    private String code;

    private String message;
}
