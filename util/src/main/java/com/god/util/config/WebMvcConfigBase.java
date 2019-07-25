package com.god.util.config;

import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.god.util.Constants;
import com.god.util.LocaleMessageUtil;
import com.god.util.internationalization.LocalMessagesChangeInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/7/24 11:46
 * @ClassName: WebConfig
 * @Description:
 */
public class WebMvcConfigBase implements WebMvcConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(WebMvcConfigBase.class);

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private LocalMessagesChangeInterceptor localMessagesChangeInterceptor;

    /**
     * 替换spring中的jackson为fastjson
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 处理字符串，避免返回直接返回字符串的时候被添加了引号
        StringHttpMessageConverter smc = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        converters.add(smc);
        converters.add(fastJsonHttpMessageConverters());
    }

    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localMessagesChangeInterceptor);
    }

    /**
     * 在程序启动的时候设置默认时区为UTC
     */
    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    /**
     * 将国际化信息工具类注入到spring容器中
     * @return
     */
    @Bean
    public LocaleMessageUtil getLocaleMessageUtil() {
        LocaleMessageUtil util = new LocaleMessageUtil();
        util.setMessageSource(messageSource);
        return util;
    }

    /**
     * 设定默认会话区域解释器，只针对当前会话
     * @return
     */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.CHINA);
        return localeResolver;
    }

    /**
     * 将语言拦截器自动注入到spring容器中，并设置语言参数paramName的key为lang
     * @return
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        localMessagesChangeInterceptor.setParamName(Constants.PARAM_LANG);
        return localMessagesChangeInterceptor;
    }

    /**
     * 使用fastjson替换spring默认的jackson
     * @return
     */
    @Bean
    public FastJsonHttpMessageConverter fastJsonHttpMessageConverters() {
        // 定义一个convert转换消息对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        // 添加fastjson的配置信息，比如：是否要格式化返回的信息
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        // 解决Long转为json精度丢失的问题
        SerializeConfig serializeConfig = SerializeConfig.globalInstance;
        serializeConfig.put(BigInteger.class, ToStringSerializer.instance);
        serializeConfig.put(Long.class, ToStringSerializer.instance);
        serializeConfig.put(Long.TYPE, ToStringSerializer.instance);
        // 设置驼峰转下划线
//        serializeConfig.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;

        // 序列化格式
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.PrettyFormat, // 结果是否格式化
                SerializerFeature.WriteMapNullValue, // 是否输出为null的字段
                SerializerFeature.WriteNullListAsEmpty, // list字段如果为null，则输出为[]
                SerializerFeature.WriteNullNumberAsZero, // number字段如果为nulll，则输出0
                SerializerFeature.WriteNullStringAsEmpty, // string字段如果输出为null，则输出""
                SerializerFeature.WriteNullBooleanAsFalse, // boolean字段如果为null，则输出false
                SerializerFeature.DisableCircularReferenceDetect, // 是否消除对同一对象的循环引用
                SerializerFeature.SortField); // 按字段名称排序后输出
        // 处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastMediaTypes.add(MediaType.APPLICATION_JSON);
        fastConverter.setSupportedMediaTypes(fastMediaTypes);
        fastJsonConfig.setSerializeConfig(serializeConfig);
        fastConverter.setFastJsonConfig(fastJsonConfig);
        return fastConverter;
    }
}
