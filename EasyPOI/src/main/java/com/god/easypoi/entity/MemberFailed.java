package com.god.easypoi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/7/24 17:23
 * @ClassName: Member
 * @Description: 当发生使用校验Excel表格信息的时候，如果有错误信息，则会将错误信息存到errorMsg中，返回给前端
 */
public class MemberFailed extends User{
    @Excel(name = "错误信息")
    private String errorMsg;

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }

    @Override
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public static MemberFailed member2MemberFailed(User member) {
        MemberFailed failed = new MemberFailed();
        failed.setErrorMsg(member.getErrorMsg());
        failed.setAge(member.getAge());
        failed.setBirthday(member.getBirthday());
        failed.setId(member.getId());
        failed.setLoginName(member.getLoginName());
        failed.setName(member.getName());
        failed.setPhone(member.getPhone());
        failed.setPic(member.getPic());
        failed.setSex(member.getSex());
        return failed;
    }

    public static List<MemberFailed> members2MemberFaileds(List<User> members) {
        List<MemberFailed> list = new ArrayList<>();
        for (User member : members) {
            list.add(member2MemberFailed(member));
        }
        return list;
    }
}
