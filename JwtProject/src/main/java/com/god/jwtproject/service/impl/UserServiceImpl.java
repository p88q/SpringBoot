package com.god.jwtproject.service.impl;

import com.god.jwtproject.base.User;
import com.god.jwtproject.mapper.UserMapper;
import com.god.jwtproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/6/19 16:58
 * @ClassName: UserServiceImpl
 * @Description:
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserById(String id) {
        return userMapper.findById(id);
    }

    @Override
    public User findByUsername(User user) {
        return userMapper.findByUserName(user);
    }

}
