package com.yh.system.domain.entity.sys;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yh.common.domain.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统-字典表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_dict")
public class SysDict extends BaseEntity {
	/**
	 * 字典名称
	 */
	@TableField(value = "dict_name")
	private String dictName;

	/**
	 * 字典编码
	 */
	@TableField(value = "dict_code")
	private String dictCode;

	/**
	 * 字典描述
	 */
	@TableField(value = "description")
	private String description;

	/**
	 * 字段代码
	 */
	@TableField(value = "field_key")
	private String fieldKey;

	/**
	 * 字段值
	 */
	@TableField(value = "field_value")
	private String fieldValue;

	/**
	 * 字段值备用
	 */
	@TableField(value = "field_value_backup")
	private String fieldValueBackup;
}