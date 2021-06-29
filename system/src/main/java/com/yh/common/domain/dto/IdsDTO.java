package com.yh.common.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@ApiModel
@Data
public class IdsDTO {

	@NotEmpty(message = "id集合不能为空")
	@ApiModelProperty(value = "id集合", required = true)
	private List<@NotBlank(message = "id不能为空") String> ids;
}
