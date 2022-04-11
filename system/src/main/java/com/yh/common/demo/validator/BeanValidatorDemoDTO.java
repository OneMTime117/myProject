package com.yh.common.demo.validator;

import com.yh.common.validator.constraint.PhoneNumber;
import com.yh.common.validator.constraint.Sex;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;
import java.util.List;

@Data
public class BeanValidatorDemoDTO {

	@AssertTrue
	private Boolean isT;

	@AssertFalse
	private Boolean isF;

	@Sex
	private String sex;

	@Max(value = 10)
	private Integer age;

	@Range(min = 0,max = 10)
	private Integer num;

	@NotEmpty
	private List<@NotBlank String> listName;

	@PhoneNumber
	private String phoneNumber;

}
