package com.god.job.controller;

import com.github.pagehelper.PageInfo;
import com.god.job.model.JobAndTrigger;
import com.god.job.model.JobForm;
import com.god.job.model.Result;
import com.god.job.service.JobService;
import com.god.job.tool.JobUtil;
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
 * @Description: 定时任务controller层
 */
@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobService jobService;

    /**
     * 保存定时任务
     * @param form
     * @return
     */
    @PostMapping
    public ResponseEntity<Result> addJob(@Valid JobForm form) {
        try {
            jobService.addJob(form);
        } catch (Exception e) {
            return new ResponseEntity<>(Result.getFail(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(Result.getSuccess(), HttpStatus.CREATED);
    }

    /**
     * 删除定时任务
     * @param form
     * @return
     * @throws SchedulerException
     */
    @DeleteMapping
    public ResponseEntity<Result> deleteJob(JobForm form) throws SchedulerException {
        if (JobUtil.isEmpty(form.getJobGroupName()) || JobUtil.isEmpty(form.getJobClassName())) {
            return new ResponseEntity<>(Result.getFail("参数不能为空"), HttpStatus.BAD_REQUEST);
        }

        jobService.deleteJob(form);
        return new ResponseEntity<>(Result.getSuccess(), HttpStatus.OK);
    }

    /**
     * 暂停定时任务
     * @param form
     * @return
     * @throws SchedulerException
     */
    @PutMapping(params = "pause")
    public ResponseEntity<Result> pauseJob(JobForm form) throws SchedulerException {

        if (JobUtil.isEmpty(form.getJobGroupName()) || JobUtil.isEmpty(form.getJobClassName())) {
            return new ResponseEntity<>(Result.getFail("参数不能为空"), HttpStatus.BAD_REQUEST);
        }
        jobService.pauseJob(form);
        return new ResponseEntity<>(Result.getSuccess(), HttpStatus.OK);
    }

    /**
     * 恢复定时任务
     * @param form
     * @return
     * @throws SchedulerException
     */
    @PutMapping(params = "resume")
    public ResponseEntity<Result> resumeJob(JobForm form) throws SchedulerException {
        if (JobUtil.isEmpty(form.getJobGroupName()) || JobUtil.isEmpty(form.getJobClassName())) {
            return new ResponseEntity<>(Result.getFail("参数不能为空"), HttpStatus.BAD_REQUEST);
        }

        jobService.resumeJob(form);
        return new ResponseEntity<>(Result.getSuccess(), HttpStatus.OK);
    }

    /**
     * 修改定时任务，定时时间，并自动重启
     * @param form
     * @return
     */
    @PutMapping(params = "cron")
    public ResponseEntity<Result> cronJob(@Valid JobForm form) {
        try {
            jobService.cronJob(form);
        } catch (Exception e) {
            return new ResponseEntity<>(Result.getFail(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(Result.getSuccess(), HttpStatus.OK);
    }

    /**
     * 查询定时任务
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping
    public ResponseEntity<Result> jobList(Integer currentPage, Integer pageSize) {
        if (JobUtil.isEmpty(currentPage)) {
            currentPage = 1;
        }
        if (JobUtil.isEmpty(pageSize)) {
            pageSize = 10;
        }
        PageInfo<JobAndTrigger> all = jobService.list(currentPage, pageSize);
        Map<String, Object> map = new HashMap<>();
        map.put("total", all.getTotal());
        map.put("data", all.getList());
        return ResponseEntity.ok(Result.getSuccess(map));
    }

}
