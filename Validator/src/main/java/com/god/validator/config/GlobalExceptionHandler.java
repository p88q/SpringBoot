package com.god.validator.config;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.security.validator.ValidatorException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/8/1 11:47
 * @ClassName: GlobalExceptionHandler
 * @Description:
 */
@ControllerAdvice
@Component
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ConstraintViolationException.class)
    public Map handle(ConstraintViolationException exception) {
        Map<String, Object> respMap = new HashMap<>();
        StringBuffer message = new StringBuffer();
        // 遍历验证期间报告的约束违规集合，获取约束违规提示信息
        for (ConstraintViolation violation : exception.getConstraintViolations()) {
            message.append(violation.getMessage() + ",");
        }
        System.out.println(exception.getLocalizedMessage());
        /** 错误码 */
        respMap.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
        /** 错误描述 */
        respMap.put("message", message.toString());
        return respMap;
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Map<String, Object> validException(MethodArgumentNotValidException exception) {
        Map<String, Object> respMap = new HashMap<>();
        StringBuffer message = new StringBuffer();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        for (FieldError error : fieldErrors) {
            message.append(error.getDefaultMessage() + ",");
        }
        respMap.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
        respMap.put("message", message);
        return respMap;
    }

}
