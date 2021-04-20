package com.yh.system.domain.dto.user;

import com.yh.common.domain.dto.BaseDTO;
import com.yh.system.domain.enums.SexEnum;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@ToString(callSuper = true)
@Data
public class UserInfoDTO extends BaseDTO {

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 昵称
	 */
	private String nickName;

	/**
	 * 手机号码
	 */
	private String phone;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 微信账号openId
	 */
	private String openId;

	/**
	 * 性别 0未知 1男 2女
	 */
//	private Integer sex;
	private SexEnum sex;

	/**
	 * 生日
	 */
//	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthday;
}
