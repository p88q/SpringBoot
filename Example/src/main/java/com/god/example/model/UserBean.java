package com.god.example.model;

import org.dozer.Mapping;

import java.util.Date;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/8/1 18:29
 * @ClassName: UserBean
 * @Description:
 */
public class UserBean {
    /**
     * 用户id
     * @date Fri Aug 02 11:05:15 CST 2019
     */
    @Mapping("operateIp")
    private Integer id;

    /**
     * 用户名称
     * @date Fri Aug 02 11:05:15 CST 2019
     */
    private String username;

    /**
     * 手机号
     * @date Fri Aug 02 11:05:15 CST 2019
     */
    private String telephone;

    /**
     * 邮箱
     * @date Fri Aug 02 11:05:15 CST 2019
     */
    private String mail;

    /**
     * 加密后的密码
     * @date Fri Aug 02 11:05:15 CST 2019
     */
    private String password;

    /**
     * 用户所在部门的id
     * @date Fri Aug 02 11:05:15 CST 2019
     */
    private Integer deptId;

    /**
     * 状态，1：正常，0：冻结状态，2：删除
     * @date Fri Aug 02 11:05:15 CST 2019
     */
    private Integer status;

    /**
     * 备注
     * @date Fri Aug 02 11:05:15 CST 2019
     */
    private String remark;

    /**
     * 操作者
     * @date Fri Aug 02 11:05:15 CST 2019
     */
    private String operator;

    /**
     * 最后一次更新时间
     * @date Fri Aug 02 11:05:15 CST 2019
     */
    private Date operateTime;

    /**
     * 最后一次更新者的ip地址
     * @date Fri Aug 02 11:05:15 CST 2019
     */
    private String operateIp;

    private static final long serialVersionUID = 1L;

    public UserBean() {}

    public UserBean(Integer id, String username, String telephone, String mail, String password, Integer deptId, Integer status, String remark, String operator, Date operateTime, String operateIp) {
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

    public void setId(Integer id) {
        this.id = id;
    }

    @Mapping("remark")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public String getOperateIp() {
        return operateIp;
    }

    public void setOperateIp(String operateIp) {
        this.operateIp = operateIp;
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
