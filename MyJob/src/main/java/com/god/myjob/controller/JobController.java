package com.god.myjob.controller;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.god.myjob.model.JobAndTrigger;
import com.god.myjob.model.JobInfo;
import com.god.myjob.model.Result;
import com.god.myjob.service.JobService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/6/21 19:21
 * @ClassName: JobController
 * @Description:
 */
@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobService jobService;

    /**
     * 保存定时任务
     */
    @PostMapping
    public ResponseEntity<Result> addJob(@Valid JobInfo jobInfo) {
        try {

            if (jobInfo != null) {
                jobService.addJob(jobInfo);
            } else {
                return new ResponseEntity<>(Result.getFail("传递参数不能为空!"), HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(Result.getFail("保存定时任务失败"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(Result.getSuccess(), HttpStatus.CREATED);
    }

    /**
     * 删除定时任务
     */
    @DeleteMapping
    public ResponseEntity<Result> deleteJob(JobInfo jobInfo) throws SchedulerException {
        String jobGroupName = jobInfo.getJobGroupName();
        String jobClassName = jobInfo.getJobClassName();
        if (StringUtil.isEmpty(jobClassName) || StringUtil.isEmpty(jobGroupName)) {
            return new ResponseEntity<>(Result.getFail("参数不能为空"), HttpStatus.BAD_REQUEST);
        }

        jobService.deleteJob(jobClassName, jobGroupName);
        return new ResponseEntity<>(Result.getSuccess(), HttpStatus.OK);
    }

    /**
     * 暂停定时任务
     */
    @PutMapping(params = "pause")
    public ResponseEntity<Result> pauseJob(JobInfo jobInfo) throws SchedulerException {
        String jobGroupName = jobInfo.getJobGroupName();
        String jobClassName = jobInfo.getJobClassName();
        if (StringUtil.isEmpty(jobClassName) || StringUtil.isEmpty(jobGroupName)) {
            return new ResponseEntity<>(Result.getFail("参数不能为空"), HttpStatus.BAD_REQUEST);
        }
        jobService.pauseJob(jobClassName, jobGroupName);
        return new ResponseEntity<>(Result.getSuccess(), HttpStatus.OK);
    }

    /**
     * 恢复定时任务
     */
    @PutMapping(params = "resume")
    public ResponseEntity<Result> resumeJob(JobInfo jobInfo) throws SchedulerException {
        String jobGroupName = jobInfo.getJobGroupName();
        String jobClassName = jobInfo.getJobClassName();
        if (StringUtil.isEmpty(jobClassName) || StringUtil.isEmpty(jobGroupName)) {
            return new ResponseEntity<>(Result.getFail("参数不能为空"), HttpStatus.BAD_REQUEST);
        }
        jobService.resumeJob(jobClassName, jobGroupName);
        return new ResponseEntity<>(Result.getSuccess(), HttpStatus.OK);
    }

    /**
     * 修改定时任务，定时时间
     */
    @PutMapping(params = "cron")
    public ResponseEntity<Result> cronJob(@Valid JobInfo jobInfo) {
        try {
            String jobGroupName = jobInfo.getJobGroupName();
            String jobClassName = jobInfo.getJobClassName();
            String cronExpression = jobInfo.getCronExpression();
            if (StringUtil.isEmpty(jobClassName) || StringUtil.isEmpty(jobGroupName) || StringUtil.isEmpty(cronExpression)) {
                return new ResponseEntity<>(Result.getFail("参数不能为空"), HttpStatus.BAD_REQUEST);
            }
            jobService.rescheduleJob(jobClassName, jobGroupName, cronExpression);
        } catch (Exception e) {
            return new ResponseEntity<>(Result.getFail(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(Result.getSuccess(), HttpStatus.OK);
    }

    /**
     * 查询所有
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping
    public ResponseEntity<Result> jobList(Integer currentPage, Integer pageSize) {
        if (null == currentPage || currentPage == 0) {
            currentPage = 1;
        }
        if (null == pageSize || pageSize == 0) {
            pageSize = 10;
        }
        PageInfo<JobAndTrigger> all = jobService.findAll(currentPage, pageSize);
        Map<String, Object> map = new HashMap<>();
        map.put("total", all.getTotal());
        map.put("data", all.getList());
        return ResponseEntity.ok(new Result(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), map));
    }

}
