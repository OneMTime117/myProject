alter table sys_dict ADD `dict_name` varchar(64) DEFAULT NULL COMMENT '字典名称';
alter table sys_dict ADD `dict_code` varchar(32) DEFAULT NULL COMMENT '字典编码';
alter table sys_dict ADD `description` varchar(255) DEFAULT NULL COMMENT '字典描述';
alter table sys_dict ADD `field_key` varchar(32) DEFAULT NULL COMMENT '字段代码';
alter table sys_dict ADD `field_value` varchar(64) DEFAULT NULL COMMENT '字段值';
alter table sys_dict ADD `field_value_backup` varchar(64) DEFAULT NULL COMMENT '字段值备用';