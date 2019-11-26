package com.god.ehcache.service.impl;

import com.god.base.mapper.BaseMapper;
import com.god.base.service.IBaseServiceImpl;
import com.god.ehcache.mapper.UserMapper;
import com.god.ehcache.service.UserService;
import com.god.user.entity.UserInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/8/16 18:13
 * @ClassName: UserServiceImpl
 * @Description:
 */
@Service
public class UserServiceImpl extends IBaseServiceImpl<UserInfoEntity> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public BaseMapper<UserInfoEntity> getBaseMapper() {
        return userMapper;
    }
}
