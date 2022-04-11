package com.yh.system.domain.dto.user;

import com.yh.common.domain.dto.BasePageDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserInfoQueryDTO extends BasePageDTO {

	//用户名
	@ApiModelProperty("用户名")
	private String username;

	//角色id
	@ApiModelProperty("角色id")
	private String roleId;



}
