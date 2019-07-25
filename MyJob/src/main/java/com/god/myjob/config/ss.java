package com.god.myjob.config;

import com.god.myjob.service.BaseJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/6/24 15:05
 * @ClassName: ss
 * @Description:
 */
@Slf4j
public class ss implements BaseJob {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("ss定时任务开始启动");
    }
}
