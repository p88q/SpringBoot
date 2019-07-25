package com.god.util.internationalization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/7/24 11:46
 * @ClassName: WebConfig
 * @Description: 语言拦截器
 */
@Component
public class LocalMessagesChangeInterceptor extends LocaleChangeInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LocalMessagesChangeInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) {
        // 获取容器启动时存储的paramName中key(lang)
        String paramName = getParamName();
        // 获取请求头或请求体中的key，对应的是lang
        String newLocale = getParam(request, paramName);

        // 判断lang是否为空
        if (!StringUtils.isEmpty(newLocale)) {
            // 判断lang是什么类型
            if (Locale.ENGLISH.getLanguage().equals(newLocale)) {
                newLocale = Locale.US.toString();
            } else if (Locale.CHINESE.getLanguage().equals(newLocale)) {
                newLocale = Locale.SIMPLIFIED_CHINESE.toString();
            }
            if (checkHttpMethod(request.getMethod())) {
                // 获取区域设置解析器
                LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
                if (localeResolver == null) {
                    throw new IllegalStateException("No LocaleResolver found: not in a DispatcherServlet request?");
                }
                try {
                    localeResolver.setLocale(request, response, parseLocaleValue(newLocale));
                } catch (IllegalArgumentException ex) {
                    // 是否忽略无效区域设置
                    if (isIgnoreInvalidLocale()) {
                        logger.debug("Ignoring invalid locale value [" + newLocale + "]: " + ex.getMessage());
                    } else {
                        throw ex;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 检验请求方法是否为mvc支持的
     * @param currentMethod 请求方法名
     * @return true为有
     */
    private boolean checkHttpMethod(String currentMethod) {
        String[] configuredMethods = getHttpMethods();
        if (ObjectUtils.isEmpty(configuredMethods)) {
            return true;
        }
        for (String configuredMethod : configuredMethods) {
            if (configuredMethod.equalsIgnoreCase(currentMethod)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 从请求头或请求体中获取指定paramName参数的value
     * @param request 请求
     * @param paramName 参数的key
     * @return 参数的value
     */
    private String getParam(HttpServletRequest request, String paramName) {
        String param = request.getParameter(paramName);
        if (StringUtils.isEmpty(param)) {
            param = request.getHeader(paramName);
        }
        return param;
    }
}