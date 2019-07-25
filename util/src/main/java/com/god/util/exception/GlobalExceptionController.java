package com.god.util.exception;

import com.alibaba.fastjson.JSONObject;
import com.god.util.IPUtil;
import com.god.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/7/24 10:24
 * @ClassName: GlobalExceptionController
 * @Description: 全局异常处理方式: 2
 */
@Controller
@RequestMapping({"${server.error.path:${error.path:/error}}"})
public class GlobalExceptionController implements ErrorController {

    private ErrorAttributes errorAttributes;

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionController.class);

    @Autowired
    public GlobalExceptionController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    /**
     * web页面错误处理
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(produces="text/html")
    @ResponseBody
    public String errorPageHandler(HttpServletRequest request, HttpServletResponse response) {
        ServletWebRequest requestAttributes =  new ServletWebRequest(request);
        Map<String, Object> attr = this.errorAttributes.getErrorAttributes(requestAttributes, false);
        printLogger(attr, request);
        return JSONObject.toJSONString(Result.getFail(String.valueOf(attr.get("status")), attr.get("message").toString()));
    }

    /**
     * 除web页面外的错误处理，比如json/xml等
     * @param request
     * @return
     */
    @RequestMapping()
    @ResponseBody
    public Result errorApiHander(HttpServletRequest request) {
        ServletWebRequest requestAttributes = new ServletWebRequest(request);
        Map<String, Object> attr=this.errorAttributes.getErrorAttributes(requestAttributes, false);
        printLogger(attr, request);
        return Result.getFail(String.valueOf(attr.get("status")), attr.get("message").toString());
    }


    @Override
    public String getErrorPath() {
        return "";
    }

    /**
     * 打印错误日志信息
     * @param attr
     * @param request
     */
    private void printLogger(Map<String, Object> attr, HttpServletRequest request) {
        Object status = attr.get("status");
        Object message = attr.get("message");
        Object path = attr.get("path");
        String ipAddress = IPUtil.getIpAddress(request);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        String date = simpleDateFormat.format(new Date());
        logger.error("请求发生错误的IP地址：{}，请求路径为：{}，请求时间为：{}，返回状态码为：{}，返回信息为：{}", ipAddress, path, date, status, message);
    }
}
