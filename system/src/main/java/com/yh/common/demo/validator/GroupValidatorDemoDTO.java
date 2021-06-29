package com.yh.common.demo.validator;

import com.yh.common.validator.group.ValidationGroups;
import lombok.Data;

import javax.validation.Validation;
import javax.validation.constraints.NotBlank;

@Data
public class GroupValidatorDemoDTO {

	@NotBlank(groups = {ValidationGroups.Update.class, ValidationGroups.Delete.class, ValidationGroups.Select.class})
	private String id;

	@NotBlank(groups = {ValidationGroups.Insert.class, ValidationGroups.Update.class})
	private String name;

}
