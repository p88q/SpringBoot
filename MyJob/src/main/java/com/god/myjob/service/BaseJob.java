package com.god.myjob.service;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/6/21 18:35
 * @ClassName: BaseJob
 * @Description: job任务接口
 */
public interface BaseJob extends Job {

    @Override
    void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException;
}
