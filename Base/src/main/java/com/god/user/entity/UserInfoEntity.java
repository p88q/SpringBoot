package com.god.user.entity;

import com.god.base.model.BaseEntity;

import java.util.StringJoiner;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/8/16 18:41
 * @ClassName: UserInfoEntity
 * @Description:
 */
public class UserInfoEntity extends BaseEntity {

    /** 用户名 */
    private String userName;
    /** 手机号 */
    private String phone;
    /** 邮箱 */
    private String mail;
    /** 密码 */
    private String password;
    /** 备注 */
    private String remark;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserInfoEntity.class.getSimpleName() + "[", "]")
                .add("userName='" + userName + "'")
                .add("phone='" + phone + "'")
                .add("mail='" + mail + "'")
                .add("password='" + password + "'")
                .add("remark='" + remark + "'")
                .toString();
    }
}
