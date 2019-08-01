package com.god.validator.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/8/1 14:07
 * @ClassName: ValidateController
 * @Description: 方法参数验证
 *      如果使用了@Validated，那么BeanValidate也会抛出异常而不是之前的封装在BindingResult中
 */
@Validated
@RestController
public class ValidateController {

    @RequestMapping("/check")
    public String check(@Min(value = 2, message = "age必须大于2")int age) {
        return "" + age;
    }

}
