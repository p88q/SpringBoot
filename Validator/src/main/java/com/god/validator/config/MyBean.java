package com.god.validator.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/8/1 14:05
 * @ClassName: MyBean
 * @Description:
 */
@Configuration
public class MyBean {

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

    /**
     * 默认模式会校验完整所有的属性，然后返回所有的验证失败信息
     * 快速失败返回模式只要有一个验证失败，则失败
     * @return
     */
    @Bean
    public Validator validator(){
        ValidatorFactory validatorFactory = Validation.byProvider( HibernateValidator.class )
                .configure()
                .addProperty( "hibernate.validator.fail_fast", "false" )// 参数false是默认模式，返回所有的失败信息，true返回头一个错误信息
                .buildValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        return validator;
    }
}
