package com.god.base.service;

import com.github.pagehelper.PageRowBounds;
import com.god.base.model.BaseEntity;
import com.god.util.PageData;
import com.god.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/8/16 15:45
 * @ClassName: IBaseServiceImpl
 * @Description: 基础业务实现基础类
 */
public abstract class IBaseServiceImpl<T extends BaseEntity> implements IBaseService<T> {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(IBaseServiceImpl.class);

    @Override
    public long add(T bean) {
        String id = bean.getId();
        if (StringUtil.isEmpty(id)) {
            id = UUID.randomUUID().toString().replace("-", "");
            bean.setId(id);
        }
        logger.debug("id:{}", id);
        return getBaseMapper().add(bean);
    }

    @Override
    public long update(T bean) {
        return getBaseMapper().update(bean);
    }

    @Override
    public long remove(String id) {
        return getBaseMapper().remove(id);
    }

    @Override
    public long revert(String id) {
        return getBaseMapper().revert(id);
    }

    @Override
    public T queryById(String id) {
        return getBaseMapper().queryById(id);
    }

    @Override
    public T queryOne(T bean) {
        return getBaseMapper().queryOne(bean);
    }

    @Override
    public List<T> queryList(T bean) {
        return getBaseMapper().queryList(bean);
    }

    @Override
    public PageData<T> queryPageList(T bean) {
        return this.queryPageList(bean, bean.getPageNum(), bean.getPageSize());
    }

    @Override
    public PageData<T> queryPageList(T bean, Integer pageNum, Integer pageSize) {
        PageData<T> pageData = new PageData<T>(pageNum, pageSize);
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
        List<T> rows = getBaseMapper().queryList(bean, rowBounds);
        pageData.setTotal(rowBounds.getTotal().longValue());
        pageData.setRows(rows);

        return pageData;
    }

    @Override
    public Long queryCount(T bean) {
        return getBaseMapper().queryCount(bean);
    }
}
