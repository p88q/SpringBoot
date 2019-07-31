package com.god.domain;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    /**
     * 用户id
     * @date Thu Jul 25 18:49:59 CST 2019
     */
    private Integer id;

    /**
     * 用户名称
     * @date Thu Jul 25 18:49:59 CST 2019
     */
    private String username;

    /**
     * 手机号
     * @date Thu Jul 25 18:49:59 CST 2019
     */
    private String telephone;

    /**
     * 邮箱
     * @date Thu Jul 25 18:49:59 CST 2019
     */
    private String mail;

    /**
     * 加密后的密码
     * @date Thu Jul 25 18:49:59 CST 2019
     */
    private String password;

    /**
     * 用户所在部门的id
     * @date Thu Jul 25 18:49:59 CST 2019
     */
    private Integer deptId;

    /**
     * 状态，1：正常，0：冻结状态，2：删除
     * @date Thu Jul 25 18:49:59 CST 2019
     */
    private Integer status;

    /**
     * 备注
     * @date Thu Jul 25 18:49:59 CST 2019
     */
    private String remark;

    /**
     * 操作者
     * @date Thu Jul 25 18:49:59 CST 2019
     */
    private String operator;

    /**
     * 最后一次更新时间
     * @date Thu Jul 25 18:49:59 CST 2019
     */
    private Date operateTime;

    /**
     * 最后一次更新者的ip地址
     * @date Thu Jul 25 18:49:59 CST 2019
     */
    private String operateIp;

    private static final long serialVersionUID = 1L;

    public User(Integer id, String username, String telephone, String mail, String password, Integer deptId, Integer status, String remark, String operator, Date operateTime, String operateIp) {
        this.id = id;
        this.username = username;
        this.telephone = telephone;
        this.mail = mail;
        this.password = password;
        this.deptId = deptId;
        this.status = status;
        this.remark = remark;
        this.operator = operator;
        this.operateTime = operateTime;
        this.operateIp = operateIp;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public Integer getStatus() {
        return status;
    }

    public String getRemark() {
        return remark;
    }

    public String getOperator() {
        return operator;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public String getOperateIp() {
        return operateIp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", telephone=").append(telephone);
        sb.append(", mail=").append(mail);
        sb.append(", password=").append(password);
        sb.append(", deptId=").append(deptId);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", operator=").append(operator);
        sb.append(", operateTime=").append(operateTime);
        sb.append(", operateIp=").append(operateIp);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}