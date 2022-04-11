package com.yh.system.domain.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
    * 系统接口日志表
    */
@Data
@TableName(value = "sys_api_log")
public class SysApiLog {
    /**
     * 接口名
     */
    @TableField(value = "api_name")
    private String apiName;

    /**
     * 接口url
     */
    @TableField(value = "api_url")
    private String apiUrl;

    /**
     * 接口请求方式:POST、GET、NONE
     */
    @TableField(value = "api_method")
    private String apiMethod;

    /**
     * 接口控制层类名
     */
    @TableField(value = "api_controller_name")
    private String apiControllerName;

    /**
     * 创建人
     */
    @TableField(value = "created_by")
    private String createdBy;

    /**
     * 创建时间
     */
    @TableField(value = "created_date")
    private LocalDateTime createdDate;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;
}