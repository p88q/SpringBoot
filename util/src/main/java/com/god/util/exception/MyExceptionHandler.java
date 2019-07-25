package com.god.util.exception;

import com.god.util.Constants;
import com.god.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/7/23 17:26
 * @ClassName: MyExceptionHandler
 * @Description: 全局异常处理方式: 1
 */
@ResponseBody
//@ControllerAdvice // 捕获controller层异常
public class MyExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(MyExceptionHandler.class);

    /**
     * 捕获全局异常
     * @param ex 异常信息
     * @return 返回未知错误
     */
    @ExceptionHandler(value = Exception.class)
    public Result javaExceptionHandler(Exception ex) {
        logger.error("捕获到异常信息为：", ex.getMessage());
        ex.printStackTrace();
        return Result.getFail(Constants.RESULT_UNKNOWN, Constants.MESSAGE_UNKNOWN);
    }

    /**
     * 实现自定义异常捕获处理
     * @param ex
     * @return
     */
    @ExceptionHandler(value = BaseException.class)
    public Result MyExceptionHandler(BaseException ex) {
        logger.error("捕获到MyException自定义异常：", ex.getMessage());
        ex.printStackTrace();
        return Result.getFail(Constants.RESULT_UNKNOWN, ex.getMessage());
    }
}
