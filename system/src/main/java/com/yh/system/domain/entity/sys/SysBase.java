package com.yh.system.domain.entity.sys;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yh.common.domain.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_base")
public class SysBase extends BaseEntity {
	public static final String COL_ID = "id";
	public static final String COL_CREATED_BY = "created_by";
	public static final String COL_LAST_MODIFIED_BY = "last_modified_by";
	public static final String COL_CREATED_DATE = "created_date";
	public static final String COL_LAST_MODIFIED_DATE = "last_modified_date";
	public static final String COL_DELETED = "deleted";
}