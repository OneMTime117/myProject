package com.yh.common.domain.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseDTO {
	/**
	 * uuid
	 */
	@TableId(value = "id", type = IdType.ASSIGN_UUID)
	private String id;

	/**
	 * 创建人
	 */
	@TableField(value = "created_by")
	private String createdBy;

	/**
	 * 最后修改人
	 */
	@TableField(value = "last_modified_by")
	private String lastModifiedBy;

	/**
	 * 创建时间
	 */
	@TableField(value = "created_date")
	private LocalDateTime createdDate;

	/**
	 * 最后修改时间
	 */
	@TableField(value = "last_modified_date")
	private LocalDateTime lastModifiedDate;

	/**
	 * 逻辑删除 0否，1是
	 */
	@TableField(value = "deleted")
	private Boolean deleted;
}
