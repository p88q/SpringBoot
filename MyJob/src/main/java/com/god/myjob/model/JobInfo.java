package com.god.myjob.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/6/21 18:22
 * @ClassName: JobInfo
 * @Description:
 */
@Data
@Accessors(chain = true)
public class JobInfo {

    /**
     * 定时任务全类名
     */
//    @NotBlank(message = "类名不能为空")
    private String jobClassName;
    /**
     * 任务组名
     */
//    @NotBlank(message = "任务组名不能为空")
    private String jobGroupName;
    /**
     * 定时任务cron表达式
     */
//    @NotBlank(message = "cron表达式不能为空")
    private String cronExpression;
    /**
     * job类型:cron,simple类型表达式
     */
//    @NotBlank(message = "job类型不能为空")
    private String jobType;
    /**
     * 时间类型
     */
//    @NotBlank(message = "时间类型不能为空")
    private Integer timeType;

}
