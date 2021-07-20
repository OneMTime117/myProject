package com.yh.system.domain.entity.sys;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yh.common.domain.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_role")
public class SysRole extends BaseEntity {
	/**
	 * 角色名称
	 */
	@TableField(value = "role_name")
	private String roleName;

	/**
	 * 角色描述
	 */
	@TableField(value = "description")
	private String description;
}