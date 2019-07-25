package com.god.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

/**
 * 国际化语言工具类
 */
public class LocaleMessageUtil {

    private static final Logger logger = LoggerFactory.getLogger(LocaleMessageUtil.class);

    private static MessageSource messageSource;

    /**
     * @param code：对应文本配置的key.
     * @return 对应地区的语言消息字符串
     */
    public static String getMessage(String code) {
        return LocaleMessageUtil.getMessage(code, new Object[] {});
    }

    public static String getMessage(String code, String defaultMessage) {
        return LocaleMessageUtil.getMessage(code, null, defaultMessage);
    }

    public static String getMessage(String code, String defaultMessage, Locale locale) {
        return LocaleMessageUtil.getMessage(code, null, defaultMessage, locale);
    }

    public static String getMessage(String code, Locale locale) {
        return LocaleMessageUtil.getMessage(code, null, "", locale);
    }

    public static String getMessage(String code, Object[] args) {
        return LocaleMessageUtil.getMessage(code, args, "");
    }

    public static String getMessage(String code, Object[] args, Locale locale) {
        return LocaleMessageUtil.getMessage(code, args, "", locale);
    }

    public static String getMessage(String code, Object[] args, String defaultMessage) {
        Locale locale = LocaleContextHolder.getLocale();
        return LocaleMessageUtil.getMessage(code, args, defaultMessage, locale);
    }

    public static String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        String message = messageSource.getMessage(code, args, defaultMessage, locale);
        logger.debug("code:{}, message:{}", code, message);
        return message;
    }

    public void setMessageSource(MessageSource messageSource) {
        LocaleMessageUtil.messageSource = messageSource;
    }
}