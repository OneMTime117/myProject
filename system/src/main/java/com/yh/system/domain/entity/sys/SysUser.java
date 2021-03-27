package com.yh.system.domain.entity.sys;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yh.common.domain.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_user")
public class SysUser extends BaseEntity {
	/**
	 * 用户名
	 */
	@TableField(value = "username")
	private String username;

	/**
	 * 密码
	 */
	@TableField(value = "password")
	private String password;

	/**
	 * 昵称
	 */
	@TableField(value = "nick_name")
	private String nickName;

	/**
	 * 手机号码
	 */
	@TableField(value = "phone")
	private String phone;

	/**
	 * 邮箱
	 */
	@TableField(value = "email")
	private String email;

	/**
	 * 微信账号openId
	 */
	@TableField(value = "open_id")
	private String openId;

	/**
	 * 性别 0未知 1男 2女
	 */
	@TableField(value = "sex")
	private Integer sex;

	/**
	 * 生日
	 */
	@TableField(value = "birthday")
	private LocalDate birthday;

	public static final String COL_USERNAME = "username";

	public static final String COL_PASSWORD = "password";

	public static final String COL_NICK_NAME = "nick_name";

	public static final String COL_PHONE = "phone";

	public static final String COL_EMAIL = "email";

	public static final String COL_OPEN_ID = "open_id";

	public static final String COL_SEX = "sex";

	public static final String COL_BIRTHDAY = "birthday";
}