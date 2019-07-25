package com.god.job.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.god.job.dao.JobDao;
import com.god.job.model.JobAndTrigger;
import com.god.job.model.JobForm;
import com.god.job.service.JobService;
import com.god.job.tool.JobUtil;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/6/21 19:21
 * @ClassName: JobServiceImpl
 * @Description: service实现类
 */
@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobDao jobDao;
    @Autowired
    private Scheduler scheduler;

    @Override
    public void addJob(JobForm form) throws Exception {
        // 启动调度器
        scheduler.start();

        // 构建Job信息
        JobDetail jobDetail = JobBuilder.newJob(JobUtil.getClass(form.getJobClassName()).getClass())
            .withIdentity(form.getJobClassName(), form.getJobGroupName()).build();

        // Cron表达式调度构建器(即任务执行的时间)
        CronScheduleBuilder cron = CronScheduleBuilder.cronSchedule(form.getCronExpression());

        // 根据Cron表达式构建一个Trigger
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(form.getJobClassName(), form.getJobGroupName())
            .withSchedule(cron).build();

        scheduler.scheduleJob(jobDetail, trigger);

    }

    @Override
    public void deleteJob(JobForm form) throws SchedulerException {
        scheduler.pauseTrigger(TriggerKey.triggerKey(form.getJobClassName(), form.getJobGroupName()));
        scheduler.unscheduleJob(TriggerKey.triggerKey(form.getJobClassName(), form.getJobGroupName()));
        scheduler.deleteJob(JobKey.jobKey(form.getJobClassName(), form.getJobGroupName()));
    }

    @Override
    public void pauseJob(JobForm form) throws SchedulerException {
        scheduler.pauseJob(JobKey.jobKey(form.getJobClassName(), form.getJobGroupName()));
    }

    @Override
    public void resumeJob(JobForm form) throws SchedulerException {
        scheduler.resumeJob(JobKey.jobKey(form.getJobClassName(), form.getJobGroupName()));
    }

    @Override
    public void cronJob(JobForm form) throws Exception {

        TriggerKey triggerKey = TriggerKey.triggerKey(form.getJobClassName(), form.getJobGroupName());
        // 表达式调度构建器
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(form.getCronExpression());

        CronTrigger trigger = (CronTrigger)scheduler.getTrigger(triggerKey);

        // 根据Cron表达式构建一个Trigger
        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

        // 按新的trigger重新设置job执行
        scheduler.rescheduleJob(triggerKey, trigger);

    }

    @Override
    public PageInfo<JobAndTrigger> list(Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<JobAndTrigger> list = jobDao.list();
        return new PageInfo<>(list);
    }
}
