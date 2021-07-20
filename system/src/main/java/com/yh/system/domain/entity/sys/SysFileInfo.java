package com.yh.system.domain.entity.sys;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yh.common.domain.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统-文件信息表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_file_info")
public class SysFileInfo extends BaseEntity {
	/**
	 * 原始文件名
	 */
	@TableField(value = "origin_name")
	private String originName;

	/**
	 * 文件名
	 */
	@TableField(value = "name")
	private String name;

	/**
	 * 业务类型
	 */
	@TableField(value = "model")
	private String model;

	/**
	 * 相对路径
	 */
	@TableField(value = "path")
	private String path;

	/**
	 * 文件后缀
	 */
	@TableField(value = "ext")
	private String ext;

	/**
	 * 文件大小（KB）
	 */
	@TableField(value = "file_size")
	private Long fileSize;

	/**
	 * 资源访问路径
	 */
	@TableField(value = "url")
	private String url;
}