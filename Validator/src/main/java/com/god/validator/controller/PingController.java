package com.god.validator.controller;

import com.god.validator.model.User;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/8/1 12:09
 * @ClassName: PingController
 * @Description:
 *      SpringBoot在内部通过集成hibernate-validation 已经实现了JSR-349验证规范接口，在SpringBoot项目中只要直接使用就行了。
 *
 *      一般用在Controller中用于验证前端传来的参数。
 *
 *      验证分两种：对封装的Bean进行验证  或者  对方法简单参数的验证
 */
@RestController
public class PingController {

    @RequestMapping("/ping1")
    public String ping1( @RequestBody User user) {
        return user.toString();
    }

    @RequestMapping("/ping2")
    public String ping2(@RequestBody @Valid User user, BindingResult result) {
        return user.toString();
    }

    /**
     * @Valid 和 BindingResult 是一一对应的，如果有多个@Valid，那么每个@Valid后面跟着的BindingResult就是这个@Valid的验证结果，顺序不能乱
     *
     * @param user
     * @param result
     * @return
     */
    @RequestMapping("/say")
    public String say(@RequestBody User user, BindingResult result) {
        System.out.println(result);
        return  result.hasErrors() ? result.getFieldError().getDefaultMessage() : "incorrect";
    }

}
