package com.yh.common.validator.constraint;

import com.yh.common.constant.RegularExpressionConstant;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Pattern;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author yuanhuan
 * @description: 手机号检验约束注解
 * @date 2021/5/14 10:48
 */

@Documented
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
//没有约束器时，则不指定
@Constraint(validatedBy = {})
@SupportedValidationTarget(ValidationTarget.ANNOTATED_ELEMENT)
@Repeatable(PhoneNumber.List.class)
//组合注解
@Pattern(regexp = RegularExpressionConstant.PHONE_NUMBER)
@ReportAsSingleViolation
public @interface PhoneNumber {

	// 三个必备的基本属性
	String message() default "手机号格式错误";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	// 重复注解
	@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
	@Retention(RUNTIME)
	@Documented
	public @interface List {
		PhoneNumber[] value();
	}
}
