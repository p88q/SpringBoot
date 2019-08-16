package com.god.example.service.impl;

import com.github.pagehelper.PageRowBounds;
import com.god.example.dao.UserDao;
import com.god.example.model.UserBean;
import com.god.example.service.UserService;
import com.god.util.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/8/1 18:30
 * @ClassName: UserServiceImpl
 * @Description:
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public PageData<UserBean> queryPageList(UserBean bean, Integer pageNum, Integer pageSize) {

        PageData<UserBean> pageData = new PageData<UserBean>(pageNum, pageSize);
        if (pageNum == null) {
            pageNum = 0;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        pageNum = pageNum - 1;
        if (pageNum < 0) {
            pageNum = 0;
        }
        if (pageSize <= 0) {
            pageSize = 10;
        }

        PageRowBounds rowBounds = new PageRowBounds(pageNum * pageSize, pageSize);
        List<UserBean> rows = userDao.queryList(bean, rowBounds);
        pageData.setTotal(rowBounds.getTotal().longValue());
        pageData.setRows(rows);

        return pageData;
    }

}
