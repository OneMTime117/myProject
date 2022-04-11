package com.yh.system.controller.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yh.common.domain.dto.BasePageDTO;
import com.yh.common.domain.dto.IdDTO;
import com.yh.system.config.result.Result;
import com.yh.system.domain.dto.user.UserInfoDTO;
import com.yh.system.domain.dto.user.UserInfoQueryDTO;
import com.yh.system.domain.dto.user.UserLoginDTO;
import com.yh.system.domain.entity.sys.SysRole;
import com.yh.system.domain.entity.sys.SysUser;
import com.yh.system.service.sys.SysRoleService;
import com.yh.system.service.sys.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "用户管理")
@RequestMapping("/user")
@RestController
public class UserController {


	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysRoleService sysRoleService;

	@ApiOperation("用户登录")
	@PostMapping("/login")
	public ResponseEntity<SysUser> login(@RequestBody UserLoginDTO dto) {
		UserInfoDTO login = sysUserService.login(dto);
		return Result.ok(login);
	}

	@ApiOperation(value = "新增", notes = "新增", httpMethod = "POST")
	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody UserInfoDTO dto) {
		sysUserService.addUserInfo(dto);
		return Result.ok();
	}

	@ApiOperation(value = "角色列表", notes = "角色列表", httpMethod = "POST")
	@PostMapping("/listRole")
	public ResponseEntity<IPage<SysRole>> listRole(@RequestBody BasePageDTO queryDto) {
		IPage<SysRole> sysRoleIPage = sysRoleService.listSysRole(queryDto);
		return Result.ok(sysRoleIPage);
	}

	@ApiOperation(value = "用户列表", notes = "用户列表", httpMethod = "POST")
	@PostMapping("/listUser")
	public ResponseEntity<IPage<UserInfoDTO>> list(@RequestBody UserInfoQueryDTO queryDto) {
		IPage<UserInfoDTO> userInfoDTOIPage = sysUserService.listUserInfo(queryDto);
		return Result.ok(userInfoDTOIPage);
	}

	@ApiOperation(value = "更新", notes = "更新", httpMethod = "POST")
	@PostMapping("/update")
	public ResponseEntity<?> update(@RequestBody UserInfoDTO dto) {
		sysUserService.updateUserInfo(dto);
		return Result.ok();
	}

	@ApiOperation(value = "单条删除", notes = "单条删除", httpMethod = "POST")
	@PostMapping("delete")
	public ResponseEntity<?> delete(@RequestBody IdDTO dto) {
		sysUserService.deleteByUserId(dto.getId());
		return Result.ok();
	}

	@ApiOperation(value = "修改密码", notes = "修改密码", httpMethod = "POST")
	@PostMapping("/updatePassword")
	public ResponseEntity<?> updatePassword(@RequestBody UserInfoDTO dto) {
		sysUserService.updatePassword(dto);
		return Result.ok();
	}
}
