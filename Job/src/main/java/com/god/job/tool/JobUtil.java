package com.god.job.tool;

import org.quartz.Job;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/6/21 18:30
 * @ClassName: JobUtil
 * @Description: 简单工具类
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
     * 是否为空
     * @param o
     * @return
     */
    public static boolean isEmpty(Object o) {
        if (null == o) {
            return true;
        }
        if ("".equals(filterNull(o.toString()))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 过滤空NULL
     * @param o
     * @return
     */
    public static String filterNull(Object o) {
        return o != null && !"null".equals(o.toString()) ? o.toString().trim() : "";
    }
}
