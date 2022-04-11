package com.yh.common.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseEntity {
	/**
	 * uuid
	 */
	@TableId(value = "id", type = IdType.ASSIGN_UUID)
	private String id;

	/**
	 * 创建人
	 */
	@TableField(value = "created_by", fill = FieldFill.INSERT)
	private String createdBy;

	/**
	 * 最后修改人
	 */
	@TableField(value = "last_modified_by", fill = FieldFill.INSERT_UPDATE)
	private String lastModifiedBy;

	/**
	 * 创建时间
	 */
	@TableField(value = "created_date", fill = FieldFill.INSERT)
	private LocalDateTime createdDate;

	/**
	 * 最后修改时间
	 */
	@TableField(value = "last_modified_date", fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime lastModifiedDate;

	/**
	 * 逻辑删除 0否，1是
	 */
	@TableField(value = "deleted")
	private Boolean deleted;

	public static final String COL_ID = "id";

	public static final String COL_CREATED_BY = "created_by";

	public static final String COL_LAST_MODIFIED_BY = "last_modified_by";

	public static final String COL_CREATED_DATE = "created_date";

	public static final String COL_LAST_MODIFIED_DATE = "last_modified_date";

	public static final String COL_DELETED = "deleted";
}
