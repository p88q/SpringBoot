package com.god.jwtproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/6/19 14:41
 * @ClassName: InterceptorConfig
 * @Description: 配置拦截器
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    /**
     * 需要一个实现HandlerInterceptor接口的拦截器实例，addPathPatterns方法用于设置拦截器的过滤路径规则。
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(ipInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public AuthenticationInterceptor authenticationInterceptor() {
        return new AuthenticationInterceptor();
    }

    @Bean
    public IPInterceptor ipInterceptor() {
        return new IPInterceptor();
    }
}
