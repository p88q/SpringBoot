package com.god.exception.model;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/7/12 18:01
 * @ClassName: User
 * @Description:
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private Boolean isDier;

    private Integer age;

    private List<String> message;

    private Long money;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getDier() {
        return isDier;
    }

    public void setDier(Boolean dier) {
        isDier = dier;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("name='").append(name).append('\'');
        sb.append(", isDier=").append(isDier);
        sb.append(", age=").append(age);
        sb.append(", message=").append(message);
        sb.append(", money=").append(money);
        sb.append('}');
        return sb.toString();
    }
}
