package com.yh.system.domain.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

@Data
@TableName(value = "sys_user")
public class SysUser {
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

    /**
     * 用户名
     */
    @TableField(value = "username")
    private String username;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 昵称
     */
    @TableField(value = "nick_name")
    private String nickName;

    /**
     * 手机号码
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 微信账号openId
     */
    @TableField(value = "open_id")
    private String openId;

    /**
     * 性别 0未知 1男 2女
     */
    @TableField(value = "sex")
    private Byte sex;

    /**
     * 生日
     */
    @TableField(value = "birthday")
    private Date birthday;

    public static final String COL_ID = "id";

    public static final String COL_CREATED_BY = "created_by";

    public static final String COL_LAST_MODIFIED_BY = "last_modified_by";

    public static final String COL_CREATED_DATE = "created_date";

    public static final String COL_LAST_MODIFIED_DATE = "last_modified_date";

    public static final String COL_DELETED = "deleted";

    public static final String COL_USERNAME = "username";

    public static final String COL_PASSWORD = "password";

    public static final String COL_NICK_NAME = "nick_name";

    public static final String COL_PHONE = "phone";

    public static final String COL_EMAIL = "email";

    public static final String COL_OPEN_ID = "open_id";

    public static final String COL_SEX = "sex";

    public static final String COL_BIRTHDAY = "birthday";
}