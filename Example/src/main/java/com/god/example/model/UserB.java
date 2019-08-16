package com.god.example.model;

import java.util.Date;
import java.util.StringJoiner;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/8/14 15:53
 * @ClassName: UserB
 * @Description:
 */
public class UserB {

    private Integer id;
    private Integer count;
    private String username;
    private Long telephone;
    private String mail;
    private String password;
    private Integer deptId;
    private String status;
    private String remark;
    private String operator;
    private String operateTime;
    private String operateIp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getTelephone() {
        return telephone;
    }

    public void setTelephone(Long telephone) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
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
        return new StringJoiner(", ", UserB.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("count=" + count)
                .add("username='" + username + "'")
                .add("telephone=" + telephone)
                .add("mail='" + mail + "'")
                .add("password='" + password + "'")
                .add("deptId=" + deptId)
                .add("status='" + status + "'")
                .add("remark='" + remark + "'")
                .add("operator='" + operator + "'")
                .add("operateTime='" + operateTime + "'")
                .add("operateIp='" + operateIp + "'")
                .toString();
    }
}
