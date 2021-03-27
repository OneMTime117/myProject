package com.yh.system.domain.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

@Data
@TableName(value = "sys_base")
public class SysBase {
    /**
     * uuid
     */
    @TableId(value = "id", type = IdType.INPUT)
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
    private Date createdDate;

    /**
     * 最后修改时间
     */
    @TableField(value = "last_modified_date")
    private Date lastModifiedDate;

    /**
     * 逻辑删除 0否，1是
     */
    @TableField(value = "deleted")
    private Byte deleted;

    public static final String COL_ID = "id";

    public static final String COL_CREATED_BY = "created_by";

    public static final String COL_LAST_MODIFIED_BY = "last_modified_by";

    public static final String COL_CREATED_DATE = "created_date";

    public static final String COL_LAST_MODIFIED_DATE = "last_modified_date";

    public static final String COL_DELETED = "deleted";
}