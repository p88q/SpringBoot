package com.god.myjob.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.god.myjob.dao.JobDao;
import com.god.myjob.model.JobAndTrigger;
import com.god.myjob.model.JobInfo;
import com.god.myjob.service.JobService;
import com.god.myjob.tool.JobUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.quartz.DateBuilder.futureDate;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/6/21 18:40
 * @ClassName: JobServiceImpl
 * @Description:
 */
@Slf4j
@Service("jobService")
public class JobServiceImpl implements JobService {

    @Autowired
    private JobDao jobDao;
    @Autowired
    private Scheduler scheduler;

    /**
     * 添加定时任务
     * @param jobInfo 表单参数 {@link JobInfo}
     * @throws Exception
     */
    @Override
    public void addJob(JobInfo jobInfo) throws Exception {
        // 判断添加的任务类型是cron还是simple类型
        if (jobInfo.getTimeType() == null) {
            this.addCronJob(jobInfo);
        } else {
            this.addSimpleJob(jobInfo);
        }
    }

    /**
     *
     * 删除定时任务
     * @param jobClassName
     * @param jobGroupName
     * @throws SchedulerException
     */
    @Override
    public void deleteJob(String jobClassName, String jobGroupName) throws SchedulerException {
        scheduler.pauseTrigger(TriggerKey.triggerKey(jobClassName, jobGroupName));
        scheduler.unscheduleJob(TriggerKey.triggerKey(jobClassName, jobGroupName));
        scheduler.deleteJob(JobKey.jobKey(jobClassName, jobGroupName));
    }

    /**
     * 暂停任务
     * @param jobClassName
     * @param jobGroupName
     * @throws SchedulerException
     */
    @Override
    public void pauseJob(String jobClassName, String jobGroupName) throws SchedulerException {
        scheduler.pauseJob(JobKey.jobKey(jobClassName, jobGroupName));
    }

    /**
     * 恢复任务
     * @param jobClassName
     * @param jobGroupName
     * @throws SchedulerException
     */
    @Override
    public void resumeJob(String jobClassName, String jobGroupName) throws SchedulerException {
        scheduler.resumeJob(JobKey.jobKey(jobClassName, jobGroupName));
    }

    /**
     * 更新任务
     * @param jobClassName
     * @param jobGroupName
     * @param cronExpression
     * @throws Exception
     */
    @Override
    public void rescheduleJob(String jobClassName, String jobGroupName, String cronExpression) throws Exception {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobClassName, jobGroupName);
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            log.error("更新定时任务失败, 失败job的表达式为:{0},失败信息为{1}", cronExpression, e.getMessage());
            throw new Exception("更新定时任务失败");
        }
    }

    /**
     * 查询所有定时任务
     * @param pageNum 当前页
     * @param pageSize 每页条数
     * @return
     */
    @Override
    public PageInfo<JobAndTrigger> findAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<JobAndTrigger> list = jobDao.findAll();
        PageInfo<JobAndTrigger> page = new PageInfo<>(list);
        return page;
    }

    /**
     * CronTrigger
     * @param jobInfo
     */
    private void addCronJob(JobInfo jobInfo) throws Exception {

        // 启动调度器
        scheduler.start();

        //构建job信息
        JobDetail jobDetail = JobBuilder.newJob(JobUtil.getClass(jobInfo.getJobClassName()).getClass()).
                withIdentity(jobInfo.getJobClassName(), jobInfo.getJobGroupName())
                .build();

        //表达式调度构建器(即任务执行的时间)
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(jobInfo.getCronExpression());
        //按新的cronExpression表达式构建一个新的trigger
        CronTrigger trigger = TriggerBuilder.newTrigger().
                withIdentity(jobInfo.getJobClassName(), jobInfo.getJobGroupName())
                .withSchedule(scheduleBuilder)
                .build();

        try {
            scheduler.scheduleJob(jobDetail, trigger);

        } catch (Exception e) {
            log.error("创建cron定时任务失败, 失败job的表达式为:{0},失败信息为{1}", jobInfo.getCronExpression(), e.getMessage());
            throw new Exception("创建cron定时任务失败");
        }
    }

    /**
     * Simple Trigger
     * @param jobInfo
     */
    private void addSimpleJob(JobInfo jobInfo) throws Exception {
        // 启动调度器
        scheduler.start();

        //构建job信息
        JobDetail jobDetail = JobBuilder.newJob(JobUtil.getClass(jobInfo.getJobClassName()).getClass())
                .withIdentity(jobInfo.getJobClassName(), jobInfo.getJobGroupName())
                .build();

        DateBuilder.IntervalUnit verDate = JobUtil.verification(jobInfo.getTimeType());
        SimpleTrigger simpleTrigger = (SimpleTrigger) TriggerBuilder.newTrigger()
                .withIdentity(jobInfo.getJobClassName(), jobInfo.getJobGroupName())
                .startAt(futureDate(Integer.parseInt(jobInfo.getCronExpression()), verDate))
                .forJob(jobInfo.getJobClassName(), jobInfo.getJobGroupName())
                .build();

        try {
            scheduler.scheduleJob(jobDetail, simpleTrigger);
        } catch (SchedulerException e) {
            log.error("创建simple定时任务失败, 失败job的表达式为:{},失败信息为{}", jobInfo.getCronExpression(), e.getMessage());
            throw new Exception("创建simple定时任务失败");
        }
    }
}
