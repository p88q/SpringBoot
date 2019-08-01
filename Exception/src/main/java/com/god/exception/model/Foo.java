package com.god.exception.model;

import com.god.util.annotation.MyClassAnnotation;
import com.god.util.annotation.MyFieldAnnotation;
import com.god.util.annotation.MyMethodAnnotation;
import org.springframework.stereotype.Component;

import java.util.StringJoiner;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/7/31 15:07
 * @ClassName: Foo
 * @Description:
 */
@Component
@MyClassAnnotation(uri = "com.god.exception.model", desc = "注释在列上")
public class Foo {

    @MyFieldAnnotation(uri = "Foo#id", desc = "字段id")
    public String id;

    public String getId() {
        return id;
    }

    @MyMethodAnnotation(uri = "Foo#setId", desc = "foo设置setID")
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Foo.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .toString();
    }
}
