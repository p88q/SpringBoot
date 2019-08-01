package com.god.validator.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/8/1 11:30
 * @ClassName: CheckCaseValidator
 * @Description: 自定校验字段属性是否是“TEST”
 */
public class CheckCaseValidator implements ConstraintValidator<CheckCase, String> {

    private String value;

    @Override
    public void initialize(CheckCase checkCase) {
        this.value = checkCase.value();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) {
            return true;
        }

        if (s.equalsIgnoreCase("TEST")) {
            return false;
        }
        return true;
    }

}
