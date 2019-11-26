package com.god.base.mapper;

import org.apache.ibatis.session.RowBounds;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/8/16 16:28
 * @ClassName: BaseMapper
 * @Description:
 *      基础Mapper数据接口
 */
public interface BaseMapper<T> extends Serializable {

    /**
     * 新增
     *
     * @param bean
     * @return
     */
    long add(T bean);

    /**
     * 更新
     *
     * @param bean
     * @return
     */
    long update(T bean);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    long remove(String id);

    /**
     * 恢复
     *
     * @param id
     * @return
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
     * @param rowBounds
     * @return
     */
    List<T> queryList(T bean, RowBounds rowBounds);

    /**
     * 条件查询数量
     *
     * @param bean
     * @return
     */
    Long queryCount(T bean);
}
