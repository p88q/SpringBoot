package com.god.example.service;

import com.god.example.model.UserBean;
import com.god.util.PageData;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/8/1 18:29
 * @ClassName: UserService
 * @Description:
 */
public interface UserService {

    /**
     * 分页查询数据库
     * @param bean
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageData<UserBean> queryPageList(UserBean bean, Integer pageNum, Integer pageSize);

}
