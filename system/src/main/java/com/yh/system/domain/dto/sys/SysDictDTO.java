package com.yh.system.domain.dto.sys;

import com.baomidou.mybatisplus.annotation.TableField;
import com.yh.common.domain.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel
@Data
public class SysDictDTO extends BaseDTO implements Serializable {
	/**
	 * 字典名称
	 */
	@ApiModelProperty("字典名称")
	@TableField(value = "DICT_NAME")
	private String dictName;

	/**
	 * 字典编码
	 */
	@ApiModelProperty("字典编码")
	@TableField(value = "DICT_CODE")
	private String dictCode;

	/**
	 * 字典描述
	 */
	@ApiModelProperty("字典描述")
	@TableField(value = "DESCRIPTION")
	private String description;

	/**
	 * 字段代码
	 */
	@ApiModelProperty("字段代码")
	@TableField(value = "FIELD_KEY")
	private String fieldKey;

	/**
	 * 字段值
	 */
	@ApiModelProperty("字段值")
	@TableField(value = "FIELD_VALUE")
	private String fieldValue;

	/**
	 * 字段值备用
	 */
	@ApiModelProperty("字段值备用")
	@TableField(value = "FIELD_VALUE_BACKUP")
	private String fieldValueBackup;


}
