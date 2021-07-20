package com.yh.common.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BasePageDTO {

	@ApiModelProperty("当前页")
	private Integer current = 1;

	@ApiModelProperty("页面大小")
	private Integer size = 10;
}
