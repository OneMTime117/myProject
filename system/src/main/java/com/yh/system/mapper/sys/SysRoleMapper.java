package com.yh.system.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yh.system.domain.entity.sys.SysRole;

public interface SysRoleMapper extends BaseMapper<SysRole> {

	IPage<SysRole> listPage(Page<?> page);
}