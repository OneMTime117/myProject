package com.yh.system.service.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yh.common.domain.dto.BasePageDTO;
import com.yh.system.domain.entity.sys.SysRole;

public interface SysRoleService extends IService<SysRole> {

	IPage<SysRole> listSysRole(BasePageDTO pageDTO);
}
