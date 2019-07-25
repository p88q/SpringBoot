package com.god.job.client.impl;

import com.god.job.client.ApiHelper;
import com.god.util.EmailUtil;
import com.god.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/6/24 17:38
 * @ClassName: Hello
 * @Description: 接口方式实现定时任务
 */
@Slf4j
public class Hello implements Job {

    @Value("${baidu.url}")
    private String url;
    @Autowired
    private ApiHelper apiHelper;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Result result = EmailUtil.sendMail("一封邮件", "codeem@163.com", "测试邮件");
        log.info("定时开始" + result);
    }
}
