package com.yh.common.demo.swagger;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@ApiModel(description = "演示dto")
public class SwaggerDemoDTO {

	@ApiModelProperty(value = "名字", required = true)
	private String name;

	@ApiModelProperty(value = "密码", required = true)
	private String password;

	@ApiModelProperty(value = "时间", required = true)
	private LocalDateTime time;

	@ApiModelProperty("单个inner")
	private SwaggerInnerDemoDTO inner;

	@ApiModelProperty("inner集合")
	private List<SwaggerInnerDemoDTO> swaggerInnerDemoDTOList;
}
