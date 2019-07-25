package com.god.jwtproject.service;

import com.god.jwtproject.base.User;

public interface UserService {

    User findUserById(String id);

    User findByUsername(User user);
}
