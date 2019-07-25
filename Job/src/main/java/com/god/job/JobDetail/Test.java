package com.god.job.JobDetail;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/6/24 18:02
 * @ClassName: Test
 * @Description: 普通测试定时任务
 */
@Slf4j
public class Test implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("普通测试定时任务");
    }
}
