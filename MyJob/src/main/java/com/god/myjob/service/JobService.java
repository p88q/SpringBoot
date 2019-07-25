package com.god.myjob.service;

import com.github.pagehelper.PageInfo;
import com.god.myjob.model.JobAndTrigger;
import com.god.myjob.model.JobInfo;
import org.quartz.SchedulerException;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/6/21 18:37
 * @ClassName: JobService
 * @Description: job相关操作
 */
public interface JobService {

    /**
     * 添加并启动定时任务
     *
     * @param jobInfo 表单参数 {@link JobInfo}
     * @throws Exception 异常
     */
    void addJob(JobInfo jobInfo) throws Exception;

    /**
     * 删除定时任务
     * @param jobClassName
     * @param jobGroupName
     * @throws SchedulerException
     */
    public void deleteJob(String jobClassName, String jobGroupName) throws SchedulerException;

    /**
     * 暂停定时任务
     * @param jobClassName
     * @param jobGroupName
     * @throws SchedulerException
     */
    void pauseJob(String jobClassName, String jobGroupName) throws SchedulerException;

    /**
     * 恢复定时任务
     *
     * @param jobInfo 表单参数 {@link JobInfo}
     * @throws SchedulerException 异常
     */
    void resumeJob(String jobClassName, String jobGroupName) throws SchedulerException;

    /**
     * 重新配置定时任务
     *
     * @param jobInfo 表单参数 {@link JobInfo}
     * @throws Exception 异常
     */
    void rescheduleJob(String jobClassName, String jobGroupName, String cronExpression) throws Exception;

    /**
     * 查询定时任务列表
     *
     * @param pageNum 当前页
     * @param pageSize 每页条数
     * @return 定时任务列表
     */
    PageInfo<JobAndTrigger> findAll(Integer pageNum, Integer pageSize);
}
