package com.god.exception.aspectj;

import com.god.util.Result;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/7/31 16:39
 * @ClassName: CustomAuthorityAspect
 * @Description: aop拦截controller层的方法
 */
//@Aspect
//@Component
public class CustomAuthorityAspect {

    private static final Logger logger = LoggerFactory.getLogger(CustomAuthorityAspect.class);

    /**
     * 声明切入点，
     *      value:声明切入点
     *      argNames:指定命名切入点方法参数列表参数名字，可以有多个用","分隔，这些参数将传递给通知方法同名的参数
     */
    @Pointcut("execution(public * com.god.exception.controller.*.*(..))")
//    @Order(10) // 执行优先级，数字越小优先级越高
    public void pointcut() {

    }

    /**
     * 定义前置通知
     * @param joinPoint
     */
    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        // 接受请求记录内容
        logger.info("[注解：before】--------------------------切面 before");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        logger.info("浏览器输入网址=URL:" + request.getRequestURL().toString());
        logger.info("HTTP_METHOD:" + request.getMethod());
        logger.info("ip:" + request.getRemoteAddr());
        logger.info("执行的业务方法=CLASS_METHOD:" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("业务方法获得的参数=ARGS:" + Arrays.toString(joinPoint.getArgs()));
    }

    /**
     * 后置返回通知
     *  * 这里需要注意的是:
     *  *      如果参数中的第一个参数为JoinPoint，则第二个参数为返回值的信息
     *  *      如果参数中的第一个参数不为JoinPoint，则第一个参数为returning中对应的参数
     *  *       returning：限定了只有目标方法返回值与通知方法相应参数类型时才能执行后置返回通知，否则不执行，
     *  *       对于returning对应的通知方法参数为Object类型将匹配任何目标返回值
     * @param result
     */
    @AfterReturning(pointcut = "pointcut()", returning = "result")
    public void afterReturning(Object result) {
        logger.info("切面最后打印，方法的返回值：" + result);
    }

    /**
     * 后置异常通知
     * @param jp
     */
    @AfterThrowing(pointcut = "pointcut()")
    public void afterThrowing(JoinPoint jp) {
        logger.info("方法异常时执行，jp:" + jp.toString());
    }

    @After("pointcut()")
    public void after(JoinPoint jp) {
        logger.info("方法最后执行");
    }

    /**
     * 环绕通知：
     *   环绕通知非常强大，可以决定目标方法是否执行，什么时候执行，执行时是否需要替换方法参数，执行完毕是否需要替换返回值。
     *   环绕通知第一个参数必须是org.aspectj.lang.ProceedingJoinPoint类型
     */
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint pjp) {
        logger.info("环绕通知，环绕前");
        try {
            Object o = pjp.proceed();
            logger.info("环绕通知执行方法结束后，结果是：" + o);
            return o;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }
}