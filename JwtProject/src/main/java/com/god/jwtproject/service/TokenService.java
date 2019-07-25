package com.god.jwtproject.service;

import com.god.jwtproject.base.User;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/6/19 17:11
 * @ClassName: TokenService
 * @Description:
 */
public interface TokenService {

    String getToken(User user);


}
