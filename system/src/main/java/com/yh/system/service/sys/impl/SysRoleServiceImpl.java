package com.yh.system.service.sys.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yh.common.domain.dto.BasePageDTO;
import com.yh.common.util.mybatis.PageUtil;
import com.yh.system.domain.entity.sys.SysRole;
import com.yh.system.mapper.sys.SysRoleMapper;
import com.yh.system.service.sys.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

	@Autowired
	private SysRoleMapper sysRoleMapper;

	@Override
	public IPage<SysRole> listSysRole(BasePageDTO pageDTO) {
		Page<?> page = PageUtil.getPage(pageDTO);
		IPage<SysRole> sysRoleIPage = sysRoleMapper.listPage(page);
		return sysRoleIPage;
	}
}
