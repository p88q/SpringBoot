package com.god.jwtproject.mapper;

import com.god.jwtproject.base.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from user where id = #{id}")
    User findById(String id);

    @Select("select * from user where userName = #{userName}")
    User findByUserName(User user);
}
