package com.god.myjob.tool;

import org.quartz.DateBuilder;
import org.quartz.Job;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/6/21 18:30
 * @ClassName: JobUtil
 * @Description:
 */
public class JobUtil {

    /**
     * 根据全类名获取Job实例
     *
     * @param classname Job全类名
     * @return {@link Job} 实例
     * @throws Exception 泛型获取异常
     */
    public static Job getClass(String classname) throws Exception {
        Class<?> clazz = Class.forName(classname);
        return (Job)clazz.newInstance();
    }

    /**
     * 产生时间类型信息
     * @param timeType
     * @return
     */
    public static DateBuilder.IntervalUnit verification(Integer timeType) {
        switch (timeType) {
            case 1:
                DateBuilder.IntervalUnit year = DateBuilder.IntervalUnit.YEAR;
                return year;
            case 2:
                DateBuilder.IntervalUnit month = DateBuilder.IntervalUnit.MONTH;
                return month;
            case 3:
                DateBuilder.IntervalUnit day = DateBuilder.IntervalUnit.DAY;
                return day;
            case 4:
                DateBuilder.IntervalUnit hour = DateBuilder.IntervalUnit.HOUR;
                return hour;
            case 5:
                DateBuilder.IntervalUnit second = DateBuilder.IntervalUnit.MINUTE;
                return second;
            case 6:
                DateBuilder.IntervalUnit week = DateBuilder.IntervalUnit.WEEK;
                return week;
            default:
                return DateBuilder.IntervalUnit.SECOND;
        }
    }

}
