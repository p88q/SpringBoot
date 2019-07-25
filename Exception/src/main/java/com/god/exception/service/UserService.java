package com.god.exception.service;

import com.god.exception.model.User;
import com.god.util.Result;
import com.god.util.exception.BaseException;
import org.springframework.stereotype.Service;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/7/23 17:40
 * @ClassName: UserService
 * @Description:
 */
@Service
public class UserService {


    public Result ping2(User user) {
        throw new BaseException("123");
    }

    public Result ping3() {
        int i = 3/0;
        return Result.getSuccess();
    }
}
