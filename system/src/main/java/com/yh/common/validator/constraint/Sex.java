package com.yh.common.validator.constraint;

import com.yh.common.validator.SexConstraintValidator;
import org.hibernate.validator.constraints.Range;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


/**
 * @author yuanhuan
 * @description: 性别校验约束注解
 * @date 2021/5/14 10:47
 */

//注释三要素
@Documented
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
//约束对应验证器
@Constraint(validatedBy = {SexConstraintValidator.class})
//约束目标（属性元素，方法参数（用于交差参数校验））
@SupportedValidationTarget(ValidationTarget.ANNOTATED_ELEMENT)
//支持可重复
@Repeatable(Sex.List.class)
//使用组合注解时，声明忽略每个注解的错误报告
//@ReportAsSingleViolation
public @interface Sex {

	// 三个必备的基本属性
	String message() default "性别只能为 0 未知,1 男,2 女";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};

	// 重复注解
	@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
	@Retention(RUNTIME)
	@Documented
	public @interface List {
		Sex[] value();
	}

}
