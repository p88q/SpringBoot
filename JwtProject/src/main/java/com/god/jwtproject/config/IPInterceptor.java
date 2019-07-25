package com.god.jwtproject.config;

import com.alibaba.fastjson.JSONObject;
import com.god.jwtproject.base.RequestIP;
import com.god.util.IPUtil;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/6/20 11:51
 * @ClassName: IPInterceptor
 * @Description:
 */
public class IPInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(IPInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // TODO 差对同一个请求地址进行检验，因为同一个页面可能请求多次接口，还有待完善
        String requestURI = request.getRequestURI();
        logger.info(requestURI);
        System.out.println(requestURI);
        StringBuffer requestURL = request.getRequestURL();
        logger.info(requestURL.toString());
        // 返回给页面进行展示
        PrintWriter out = null;
        response.setCharacterEncoding("UTF-8");
        Map<String, Object> map = new HashMap<>();
        // 获取用户真实IP
        String ip = IPUtil.getIpAddress(request);

        RequestIP requestIP = (RequestIP) request.getSession().getAttribute(ip);
        long currentTimeMillis = System.currentTimeMillis();
        if (null == requestIP) {
            RequestIP reIp = new RequestIP();
            reIp.setCount(1);
            reIp.setDate(currentTimeMillis);
            reIp.setIp(ip);
            request.getSession().setAttribute(ip, reIp);
            return true;
        } else {
            Long createTime = requestIP.getDate();
            if (null == createTime) {
                map.put("code", 503);
                map.put("message", "请求太快，请稍后重试！");
                out = response.getWriter();
                JSONObject json = (JSONObject) JSONObject.toJSON(map);
                out.append(json.toString());
                return false;
            } else {
                long time = currentTimeMillis - createTime;
                if (time > 3000) {
                    logger.info("请求通过！距离上一次请求时间间隔为：{}", time);
                    RequestIP reqIp = new RequestIP();
                    reqIp.setIp(ip);
                    reqIp.setCount(1);
                    reqIp.setDate(currentTimeMillis);
                    request.getSession().setAttribute(ip, reqIp);
                    return true;
                } else {
                    //小于3秒，并且3秒之内请求了10次，返回提示
                    if(requestIP.getCount() > 10){
                        map.put("code", 504);
                        map.put("message", "请求太快，请稍后再试！");
                        out = response.getWriter();
                        JSONObject json = (JSONObject) JSONObject.toJSON(map);
                        out.append(json.toString());//以json形式返回给页面，也可以直接返回提示信息
                        return false;
                    }else{
                        //小于3秒，但请求数小于10次，给对象添加
                        requestIP.setDate(System.currentTimeMillis());
                        requestIP.setCount(requestIP.getCount()+1);
                        request.getSession().setAttribute(ip, requestIP);
                        return true;
                    }
                }
            }
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
