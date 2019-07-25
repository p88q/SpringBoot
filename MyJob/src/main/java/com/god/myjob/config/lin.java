package com.god.myjob.config;

import com.god.myjob.service.BaseJob;
import com.god.myjob.service.JobService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/6/24 14:37
 * @ClassName: lin
 * @Description:
 */
@Slf4j
public class lin implements BaseJob {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("定时任务开始了----------");
    }
}
