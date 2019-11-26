package com.god.example.aspectj;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/8/1 18:28
 * @ClassName: PageHelperAspectj
 * @Description: 没有完成
 */
//@Aspect
//@Component
public class PageHelperAspectj {

    private static final Logger logger = LoggerFactory.getLogger(PageHelperAspectj.class);

    @Pointcut("execution(public * com.god.example.service.*.queryPageList(..))")
    public void pointCut() {}

    // TODO

    /**
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("pointCut()")
    public Object  arount(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("进入PageHelper Aop方法！");
        // 获取入参参数列表
        Object[] args = joinPoint.getArgs();
        // 获取连接点的方法签名对象
        Signature signature = joinPoint.getSignature();
        // 获取连接点所在的类的对象(实例)
        Object target = joinPoint.getTarget();

        PageHelper.startPage(Integer.parseInt(args[1].toString()), Integer.parseInt(args[2].toString()));
        logger.info("方法[{}]开始执行...",signature.getName());
        Object object = joinPoint.proceed();
        logger.info("方法[{}]执行结束.",signature.getName());

        if(object instanceof List) {
            List objList = (List) object;
            PageInfo pageInfo = new PageInfo<>(objList);
            return pageInfo;
        }
        return object;

    }

}
