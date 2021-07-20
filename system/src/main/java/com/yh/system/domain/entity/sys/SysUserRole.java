package com.yh.system.domain.entity.sys;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yh.common.domain.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_user_role")
public class SysUserRole extends BaseEntity {
	/**
	 * 用户id
	 */
	@TableField(value = "user_id")
	private String userId;

	/**
	 * 角色id
	 */
	@TableField(value = "role_id")
	private String roleId;
}