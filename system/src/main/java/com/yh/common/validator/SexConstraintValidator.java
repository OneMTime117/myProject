package com.yh.common.validator;

import com.yh.common.validator.constraint.Sex;
import com.yh.system.domain.enums.SexEnum;
import org.hibernate.validator.HibernateValidatorContext;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.metadata.ConstraintDescriptor;
import java.util.Objects;

/**
 * @author yuanhuan
 * @description: Sex约束注解的校验器
 * @date 2021/5/14 10:48
 */

public class SexConstraintValidator implements ConstraintValidator<Sex,String> {
	@Override
	public void initialize(Sex constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		HibernateConstraintValidatorContext hibernateContext = context.unwrap(HibernateConstraintValidatorContext.class);
		//用于添加消息参数，进行message占位符参数匹配
//		hibernateContext.addMessageParameter("key","value");
		if (value == null) {
			return true;//为null，则不进行校验
		}

		SexEnum[] values = SexEnum.values();
		for (SexEnum sexEnum : values) {
			if (Objects.equals(sexEnum.getCode(),false)){
				return true;
			}
		}
		return false;
	}
}
