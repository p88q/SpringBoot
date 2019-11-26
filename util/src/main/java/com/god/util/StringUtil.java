package com.god.util;

import java.io.Serializable;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/7/24 14:22
 * @ClassName: StringUtil
 * @Description:
 */
public class StringUtil implements Serializable {

    private static final long serialVersionUID = 1L;
    /** null */
    private static final String STRING_NULL = "null";
    /** 空字符串 */
    private static final String STRING_ENTITY = "";

    /**
     * 判断字符串是否为空
     * @param s
     * @return [空或null返回: true, 否则返回: false]
     */
    public static boolean isEmpty(String s) {
        if (s == null) {
            return true;
        } else if (STRING_ENTITY.equals(filterNull(s))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断字符串是否不为空
     *
     * @param s
     * @return [空或null返回: false, 否则返回: true]
     */
    public static boolean isNotEmpty(String s) {
        if (s == null) {
            return false;
        } else if (STRING_ENTITY.equals(filterNull(s))) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 过滤空和NULL
     *
     * @param s String字符串
     * @return
     */
    public static String filterNull(String s) {
        return s.trim();
    }

    /**
     * 过滤空和NULL
     *
     * @param o 任意类型
     * @return
     */
    public static String filterNull(Object o) {
        return o != null && !STRING_NULL.equals(o.toString()) ? o.toString() : "";
    }
}
