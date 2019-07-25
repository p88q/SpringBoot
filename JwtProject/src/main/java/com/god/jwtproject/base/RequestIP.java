package com.god.jwtproject.base;

import lombok.Data;

import java.util.Date;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/6/20 11:56
 * @ClassName: RequestIP
 * @Description:
 */
@Data
public class RequestIP {

    private String ip;

    private Integer count;

    private Long date;
}
