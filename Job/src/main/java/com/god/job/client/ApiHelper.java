package com.god.job.client;

import com.god.job.tool.JobUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/6/24 17:38
 * @ClassName: ApiHelper
 * @Description: 接口方式实现定时任务
 */
@Slf4j
@Component
public class ApiHelper {

    @Value("${baidu.url}")
    private String token;

    public void post(String url) {
        Map<String, String> param = new HashMap<>();
        Map<String, String> header = new HashMap<>();
        log.info("====================定时开始====================");
        log.info("url:" + url);
//        String resultStr = OkHttpUtil.post(url, header, param);
        String resultStr = null;
        if (JobUtil.isEmpty(resultStr)) {
            log.error("error: 访问失败 ,url:" + url);
        } else {
            log.info("====================定时结束====================");
        }

    }
}
