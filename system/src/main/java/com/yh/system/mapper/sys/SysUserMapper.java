package com.yh.system.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yh.system.config.Interceptor.DataAuth;
import com.yh.system.domain.dto.user.QueryUserInfoDTO;
import com.yh.system.domain.dto.user.UserInfoDTO;
import com.yh.system.domain.dto.user.UserRoleDTO;
import com.yh.system.domain.entity.sys.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper extends BaseMapper<SysUser> {

	@DataAuth(dataAuthSql = "")
	IPage<UserInfoDTO> pageUserInfoDTO(Page<?> page, @Param("dto") QueryUserInfoDTO dto);

	List<UserRoleDTO> listUserRole(String username);

}