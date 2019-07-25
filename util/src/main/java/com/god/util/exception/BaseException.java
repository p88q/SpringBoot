package com.god.util.exception;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/7/23 17:24
 * @ClassName: MyException
 * @Description:
 */
public class BaseException extends RuntimeException{

    private String message;

    public BaseException() {
        super();
    }

    public BaseException(String message) {
        super(message);
    }

}
