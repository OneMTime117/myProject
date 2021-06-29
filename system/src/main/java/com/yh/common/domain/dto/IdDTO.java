package com.yh.common.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel
@Data
public class IdDTO {

	@ApiModelProperty(value = "id", required = true)
	@NotBlank(message = "id不能为null")
	private String id;
}
