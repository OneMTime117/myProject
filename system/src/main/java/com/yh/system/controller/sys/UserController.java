package com.yh.system.controller.sys;

import cn.hutool.crypto.SecureUtil;
import com.yh.common.domain.dto.IdsDTO;
import com.yh.system.config.mvc.result.Result;
import com.yh.system.domain.dto.user.TimeDTO;
import com.yh.system.domain.dto.user.UserInfoDTO;
import com.yh.system.domain.dto.user.UserLoginDTO;
import com.yh.system.domain.entity.sys.SysUser;
import com.yh.system.domain.enums.SexEnum;
import com.yh.system.service.sys.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Api("用户管理")
@RequestMapping("/user")
@RestController
public class UserController {

	@Autowired
	private SysUserService sysUserService;

	@ApiOperation("用户登录")
	@PostMapping("/login")
	public Result login(@RequestParam String id, @RequestBody UserLoginDTO dto) {
		SysUser sysUser = new SysUser();
		sysUser.setUsername("yuanhuan123");
		sysUser.setPassword(SecureUtil.md5("123456"));
		sysUserService.save(sysUser);
		return Result.ok();
	}

	@PostMapping("add")
	public Result add(@RequestBody UserInfoDTO dto) {
		System.out.println(dto);
		return Result.ok();
	}

	@PostMapping("test")
	public Result test(@Valid @RequestBody IdsDTO dto, HttpServletResponse response) throws IOException {
		List<String> ids = dto.getIds();
		return Result.ok();
	}
}
