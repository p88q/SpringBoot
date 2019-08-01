package com.god.validator.config;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Auther: shenzhaoyang
 * @Date: 2019/8/1 16:24
 * @ClassName: BeanValidtor
 * @Description:
 */
public class BeanValidtor {

    /**
     * 验证某个bean的参数
     * @param object 被校验的参数
     * @param <T>
     * @throws ValidationException 如果参数校验不成功则抛出此异常
     */
    public static <T> void validate(T object) {
        // 获得验证器
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        // 执行验证
        Set<ConstraintViolation<T>> constraintViolationSet = validator.validate(object);
        // 如果有验证信息，则取出来包装成功异常返回
        if (constraintViolationSet == null) {
            return;
        }
        throw new ValidationException(convertErrorMsg(constraintViolationSet));
    }

    /**
     * 转换异常信息
     * @param set
     * @param <T>
     * @return
     */
    private static <T> String convertErrorMsg(Set<ConstraintViolation<T>> set) {
        Map<String, StringBuilder> errorMap = new HashMap<>();
        String property;
        for (ConstraintViolation<T> cv : set) {
            // 循环获取错误信息，可以自定义格式
            property = cv.getPropertyPath().toString();
            if (errorMap.get(property) != null) {
                errorMap.get(property).append("," + cv.getMessage());
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(cv.getMessage());
                errorMap.put(property, sb);
            }
        }
        return errorMap.toString();
    }

}
