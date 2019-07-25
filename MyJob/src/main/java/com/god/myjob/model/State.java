package com.god.myjob.model;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/6/21 18:29
 * @ClassName: State
 * @Description:
 */
public enum State {

    /** 年 */
    TRI_YEAR(1),
    /** 月 */
    TRI_MONTH(2),
    /** 日 */
    TRI_DAY(3),
    /** 时 */
    TRI_HOUR(4),
    /** 分 */
    TRI_SECOND(5),
    /** 秒 */
    TRI_WEEK(6);

    private Integer value;

    public Integer getValue() {
        return value;
    }

    State(Integer value) {
        this.value = value;
    }
}