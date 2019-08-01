package com.god.validator.model;

import com.god.validator.annotation.CheckCase;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/8/1 11:24
 * @ClassName: User
 * @Description:
 */
public class User implements Serializable {

    @CheckCase("test")
    private String name;

    @CheckCase("t")
    @Length(min = 3, max = 6)
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("password='" + password + "'")
                .toString();
    }
}
