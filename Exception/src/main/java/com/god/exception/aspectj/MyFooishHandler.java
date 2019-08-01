package com.god.exception.aspectj;

import com.god.exception.model.Foo;
import com.god.util.annotation.MyClassAnnotation;
import com.god.util.annotation.MyMethodAnnotation;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/7/31 15:13
 * @ClassName: MyFooishHandler
 * @Description: 自定义注解拦截方法1
 */
@Component
public class MyFooishHandler implements ApplicationContextAware, InitializingBean {

    private ApplicationContext applicationContext;

    private List<String> allFooish = new ArrayList<>();

    /**
     * 当Spring启动并实例化该类的时候，会调用afrerPropertiesSet方法
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        scanFooishClass();
        scanFooishMethod();
        System.out.println(allFooish);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    private void scanFooishClass() throws Exception {
        final Map<String, Object> permissionMap = applicationContext.getBeansWithAnnotation(MyClassAnnotation.class);

        for (final Object permissionObject : permissionMap.values()) {
            final Class<? extends Object> permissionClass = permissionObject.getClass();
            final MyClassAnnotation annotation = permissionClass.getAnnotation(MyClassAnnotation.class);
            if (annotation != null) {
                allFooish.addAll(Arrays.asList(annotation.desc()));
            }
        }
    }

    /**
     * 查找 用 Component 注解的 类 下面 用 Fooish 注解的方法
     * @throws Exception
     */
    private void scanFooishMethod() throws Exception {
        final Map<String, Object> controllerMap = applicationContext.getBeansWithAnnotation(Component.class);
        for (final Object controllerObject : controllerMap.values()) {
            final Class<? extends Object> controllerClass = controllerObject.getClass();
            for (Method method : controllerClass.getDeclaredMethods()) {
                MyMethodAnnotation fooish = method.getAnnotation(MyMethodAnnotation.class);
                if (fooish != null) {
                    allFooish.addAll(Arrays.asList(fooish.desc()));
                }
            }
        }
    }

}
