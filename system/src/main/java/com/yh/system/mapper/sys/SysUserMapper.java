package com.yh.system.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yh.system.domain.dto.user.UserInfoDTO;
import com.yh.system.domain.dto.user.UserInfoQueryDTO;
import com.yh.system.domain.entity.sys.SysUser;
import org.apache.ibatis.annotations.Param;

public interface SysUserMapper extends BaseMapper<SysUser> {

	IPage<UserInfoDTO> listPage(Page<?> page, @Param("dto") UserInfoQueryDTO queryDTO);
}