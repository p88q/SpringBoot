package com.god.myjob.dao;

import com.god.myjob.model.JobAndTrigger;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/6/21 18:41
 * @ClassName: JobDao
 * @Description:
 */
@Mapper
public interface JobDao {

    /**
     * 查询定时作业和触发器列表
     *
     * @return 定时作业和触发器列表
     */
    @Select("SELECT\n" +
            "            QRTZ_JOB_DETAILS.JOB_NAME          AS jobName,\n" +
            "            QRTZ_JOB_DETAILS.JOB_GROUP         AS jobGroup,\n" +
            "            QRTZ_JOB_DETAILS.JOB_CLASS_NAME    AS jobClassName,\n" +
            "            QRTZ_TRIGGERS.TRIGGER_NAME         AS triggerName,\n" +
            "            QRTZ_TRIGGERS.TRIGGER_GROUP        AS triggerGroup,\n" +
            "            QRTZ_TRIGGERS.TRIGGER_STATE       AS triggerState,\n" +
            "            QRTZ_CRON_TRIGGERS.CRON_EXPRESSION AS cronExpression,\n" +
            "            QRTZ_CRON_TRIGGERS.TIME_ZONE_ID    AS timeZoneId\n" +
            "        FROM\n" +
            "            QRTZ_JOB_DETAILS\n" +
            "                JOIN QRTZ_TRIGGERS\n" +
            "                JOIN QRTZ_CRON_TRIGGERS\n" +
            "                     ON QRTZ_JOB_DETAILS.JOB_NAME = QRTZ_TRIGGERS.JOB_NAME\n" +
            "                         AND QRTZ_TRIGGERS.TRIGGER_NAME = QRTZ_CRON_TRIGGERS.TRIGGER_NAME\n" +
            "                         AND QRTZ_TRIGGERS.TRIGGER_GROUP = QRTZ_CRON_TRIGGERS.TRIGGER_GROUP")
    List<JobAndTrigger> findAll();
}
