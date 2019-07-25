package com.god.util.internationalization;

import com.god.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/7/24 14:52
 * @ClassName: MyLocaleResolver
 * @Description:
 */
public class MyLocaleResolver implements LocaleResolver {

    private static final Logger logger = LoggerFactory.getLogger(MyLocaleResolver.class);

    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        // 获取请求头中的语言格式：en_US或者zh_CN
        String lan = httpServletRequest.getParameter(Constants.PARAM_LANG);
        // 获取默认的语言
        Locale locale = Locale.getDefault();
        // 判断是否为空
        if(StringUtils.hasText(lan)){
            try{
                String[] split = lan.split("_");
                locale = new Locale(split[0], split[1]);
            }catch (Exception e){
                e.printStackTrace();
                logger.error("获取国际化语言错误信息:", e);
            }
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {
    }
}
