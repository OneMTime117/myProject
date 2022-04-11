package com.yh.system.domain.dto.user;

import com.yh.common.domain.dto.BaseDTO;
import com.yh.system.domain.entity.sys.SysRole;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@ApiModel(value = "UserInfoDTO", description = "")
@ToString(callSuper = true)
@Data
public class UserInfoDTO extends BaseDTO {

	/**
	 * 用户名
	 */
	@ApiModelProperty(value = "用户名", required = true)
	private String username;

	/**
	 * 密码
	 */
	@ApiModelProperty(value = "密码", required = true)
	private String password;

	/**
	 * 昵称
	 */
	@ApiModelProperty(value = "昵称", required = true)
	private String nickName;

	/**
	 * 手机号码
	 */
	@ApiModelProperty(value = "手机号码", required = true)
	private String phone;

	/**
	 * 邮箱
	 */
	@ApiModelProperty(value = "邮箱", required = true)
	private String email;

	/**
	 * 微信账号openId
	 */
	@ApiModelProperty(value = "微信账号openId", required = true)
	private String openId;

	/**
	 * 性别 0未知 1男 2女
	 */
	@ApiModelProperty(value = "性别0未知1男2女", required = true)
	private Integer sex;

	/**
	 * 生日
	 */
	@ApiModelProperty(value = "生日", required = true)
	private LocalDate birthday;

	private String roleId;

	private SysRole sysRole;

	private String token;
}
