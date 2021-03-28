package com.yh.system.controller.sys;

import cn.hutool.crypto.SecureUtil;
import com.yh.system.config.mvc.Result;
import com.yh.system.domain.entity.sys.SysUser;
import com.yh.system.service.sys.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController {

	@Autowired
	private SysUserService sysUserService;

	@PostMapping("/login")
	public Result login(String id) {
		SysUser sysUser = new SysUser();
		sysUser.setSex(1);
		sysUser.setUsername("yuanhuan123");
		sysUser.setPassword(SecureUtil.md5("123456"));
		sysUserService.save(sysUser);
		return Result.ok();
	}
}
