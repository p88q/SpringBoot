package com.god.validator.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/8/1 16:12
 * @ClassName: BindingResultAspect
 * @Description: aop实现校验拦截，两种方式
 */
@Aspect
@Component
@Order(2)
public class BindingResultAspect {

    @Pointcut("execution(public * com.god.validator.controller.*.*(..))")
    public void BindingResult(){
    }

    /**
     * 环绕通知，controller层需要在参数中写BindingResult方法
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("BindingResult()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof BindingResult) {
                BindingResult result = (BindingResult) arg;
                if (result.hasErrors()) {
                    return result.getFieldError().getDefaultMessage();
                }
            }
        }
        return joinPoint.proceed();
    }

    /**
     * 使用aop在传递参数之前验证参数是否正常
     * @param joinPoint
     */
    @Before("BindingResult()")
    public void before(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            BeanValidtor.validate(arg);
        }
    }
}
