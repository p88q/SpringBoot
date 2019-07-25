package com.god.easypoi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import cn.afterturn.easypoi.handler.inter.IExcelModel;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/7/24 17:09
 * @ClassName: User
 * @Description:
 */
@ExcelTarget(value = "user")
public class User implements IExcelModel, Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * isImportField校验字段，看看这个字段是否是导入Excel中有，如果没有说明是错误的Excel，读取失败，参数类型：boolean，支持类型：name_id
     * height行高(后续版本可能不支持)
     * width列宽
     * name单元格列名
     * replace：替换，导出时{a_id,b_id}导入反过来
     *
     */

    @Excel(name = "用户id")
    @NotNull
    private Long id;

    @Excel(name = "用户姓名")
    @NotEmpty
    private String name;

    @Excel(name = "生日", format = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;

    @Excel(name = "性别", replace = {"男_0", "女_1"}) //replace使用的时候直接"实际含义_数据库实际内容"即可，导入导出均适用
    @Pattern(regexp = "^[0-1]$", message = "性别输入有误")
    private String sex;

    @Excel(name = "用户年龄")
    @Pattern(regexp = "^\\d{1,3}$", message = "年龄输入有误")
    private String age;

    @Excel(name = "电话", width = 16)
    @Pattern(regexp = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$", message = "电话输入有误")
    private String phone;

    @Excel(name = "用户账号", width = 16)
    private String loginName;

    @Excel(name = "用户头像", width = 32, height = 32, type = 2)
    private String pic;

    public User() {}

    public User(Long id, String name, String sex, Date birthday, String age, String phone, String loginName, String pic) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.birthday = birthday;
        this.age = age;
        this.phone = phone;
        this.loginName = loginName;
        this.pic = pic;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", birthday=").append(birthday);
        sb.append(", sex='").append(sex).append('\'');
        sb.append(", age='").append(age).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", loginName='").append(loginName).append('\'');
        sb.append(", pic='").append(pic).append('\'');
        sb.append(", errorMsg='").append(errorMsg).append('\'');
        sb.append('}');
        return sb.toString();
    }

    //自定义errorMsg接收IExcelModel.setErrorMsg传过来的errorMsg
    private String errorMsg;

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }

    @Override
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
