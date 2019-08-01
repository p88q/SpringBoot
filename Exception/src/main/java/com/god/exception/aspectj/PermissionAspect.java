package com.god.exception.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/7/31 15:34
 * @ClassName: PermissionAspect
 * @Description: 自定义注解apo拦截方法2
 */
@Aspect
@Component
public class PermissionAspect {

    // 被myMethodAnnotation注解表示的方法
    @Pointcut("@annotation(com.god.util.annotation.MyMethodAnnotation)")
    public void permissionCodeAspect() {
    }

    @Before("permissionCodeAspect()")
    public void deBefor(JoinPoint joinPoint) {
        try {
            //*========控制台输出=========*//
            System.out.println("=====前置通知开始=====");
            System.out.println("###请求方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            System.out.println("=====前置通知结束=====");
        }  catch (Exception e) {
            //记录本地异常日志
            e.printStackTrace();
        }
    }

    @After("permissionCodeAspect()")
    public void doAfterTask(JoinPoint joinPoint){
        System.out.println("=====后置通知=====");
        System.out.println("###请求方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
        System.out.println("=====后置通知end=====");
    }
}
