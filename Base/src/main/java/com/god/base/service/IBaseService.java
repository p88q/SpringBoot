package com.god.base.service;

import com.god.base.mapper.BaseMapper;
import com.god.util.PageData;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/8/16 14:44
 * @ClassName: BaseService
 * @Description:
 *      service层基础业务接口
 */
public interface IBaseService<T> extends Serializable {

    /**
     * 重写此方法，注入Mapper业务接口
     *
     * @return
     */
    BaseMapper<T> getBaseMapper();

    /**
     * 新增
     *
     * @param bean
     */
    long add(T bean);

    /**
     * 更新
     *
     * @param bean
     */
    long update(T bean);

    /**
     * 删除(逻辑)
     *
     * @param id
     */
    long remove(String id);

    /**
     * 恢复
     *
     * @param id
     */
    long revert(String id);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    T queryById(String id);

    /**
     * 条件查询单个对象
     *
     * @param bean
     * @return
     */
    T queryOne(T bean);

    /**
     * 条件查询
     *
     * @param bean
     * @return
     */
    List<T> queryList(T bean);

    /**
     * 分页条件查询
     *
     * @param bean
     * @return
     */
    PageData<T> queryPageList(T bean);

    /**
     * 分页条件查询
     *
     * @param bean
     * @param pageNum 页码
     * @param pageSize 每页条数
     * @return
     */
    PageData<T> queryPageList(T bean, Integer pageNum, Integer pageSize);

    /**
     * 条件查询数量
     *
     * @param bean
     * @return
     */
    Long queryCount(T bean);
}
