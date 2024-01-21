package com.tht.utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author tianwei
 * @date 2023/6/28 0028 15:49
 */
public class ListVarValidatorForArrayOfInt implements ConstraintValidator<ListVar, String> {

    private Set<String> stringSet = null;

    /**
     * 初始化方法
     *
     * @param constraintAnnotation annotation instance for a given constraint declaration
     */
    @Override
    public void initialize(ListVar constraintAnnotation) {
        stringSet = Arrays.stream(constraintAnnotation.values()).collect(Collectors.toSet());
    }

    /**
     * 校验方法
     *
     * @param value   object to validate
     * @param context context in which the constraint is evaluated
     * @return 校验是否通过
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return stringSet.contains(value);
    }
}
