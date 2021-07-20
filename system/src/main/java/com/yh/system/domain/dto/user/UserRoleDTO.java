package com.yh.system.domain.dto.user;

import com.yh.system.domain.entity.sys.SysRole;
import com.yh.system.domain.enums.SexEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class UserRoleDTO {

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
//	private Integer sex;
	@ApiModelProperty(value = "性别0未知1男2女", required = true)
	private SexEnum sex;

	/**
	 * 生日
	 */
//	@JsonFormat(pattern = "yyyy-MM-dd")
	@ApiModelProperty(value = "生日", required = true)
	private LocalDate birthday;

	//角色
	private List<SysRole> sysRoles;
}
