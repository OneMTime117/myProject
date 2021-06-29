package com.yh.common.demo.swagger;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "演示内部dto")
@Data
public class SwaggerInnerDemoDTO {

	@ApiModelProperty("id")
	private String id;
}
