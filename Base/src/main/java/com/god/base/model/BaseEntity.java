package com.god.base.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/8/16 14:42
 * @ClassName: BaseEntity
 * @Description:
 *      Bean类的基类实现了序列化接口
 */
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /** 主键ID */
    private String id;
    /** 创建时间 */
    private Date createTime;
    /** 修改时间 */
    private Date modifyTime;
    /** 删除标志位: [0 删除; 1 有效] */
    private Integer dr;
    /** 页码 当前页 */
    @JSONField(serialize = false)
    private Integer pageNum = 0;
    /** 每页显示条数 */
    @JSONField(serialize = false)
    private Integer pageSize = 10;

    /** 主键ID */
    public String getId() {
        return id;
    }

    /** 主键ID */
    public void setId(String id) {
        this.id = id;
    }

    /** 创建时间 */
    public Date getCreateTime() {
        return createTime;
    }

    /** 创建时间 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /** 修改时间 */
    public Date getModifyTime() {
        return modifyTime;
    }

    /** 修改时间 */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /** 删除标志位: [0 删除; 1 有效] */
    public Integer getDr() {
        return dr;
    }

    /** 删除标志位: [0 删除; 1 有效] */
    public void setDr(Integer dr) {
        this.dr = dr;
    }

    /** 页码 当前页 */
    public Integer getPageNum() {
        return pageNum;
    }

    /** 页码 当前页 */
    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    /** 每页显示条数 */
    public Integer getPageSize() {
        return pageSize;
    }

    /** 每页显示条数 */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
