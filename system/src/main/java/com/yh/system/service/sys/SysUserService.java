package com.yh.system.service.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yh.system.domain.dto.user.UserInfoDTO;
import com.yh.system.domain.dto.user.UserInfoQueryDTO;
import com.yh.system.domain.dto.user.UserLoginDTO;
import com.yh.system.domain.entity.sys.SysUser;

public interface SysUserService extends IService<SysUser> {
	UserInfoDTO login(UserLoginDTO dto);

	IPage<UserInfoDTO> listUserInfo(UserInfoQueryDTO queryDTO);

	void updateUserInfo(UserInfoDTO dto);

	void addUserInfo(UserInfoDTO dto);

	void deleteByUserId(String id);

	void updatePassword(UserInfoDTO dto);

}




