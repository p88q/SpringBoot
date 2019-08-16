package com.god.example.dao;

import com.github.pagehelper.PageRowBounds;
import com.god.example.model.UserBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/8/1 18:31
 * @ClassName: UserDao
 * @Description:
 */
@Mapper
public interface UserDao {

    @Select("select * from sys_user")
    List<UserBean> queryList(UserBean userBean, PageRowBounds rowBounds);
}
