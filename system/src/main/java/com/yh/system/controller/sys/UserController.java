package com.yh.system.controller.sys;

import cn.hutool.crypto.SecureUtil;
import com.yh.system.config.mvc.result.Result;
import com.yh.system.domain.dto.user.TimeDTO;
import com.yh.system.domain.dto.user.UserInfoDTO;
import com.yh.system.domain.dto.user.UserLoginDTO;
import com.yh.system.domain.entity.sys.SysUser;
import com.yh.system.domain.enums.SexEnum;
import com.yh.system.service.sys.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {

	@Autowired
	private SysUserService sysUserService;

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

	@PostMapping("time")
	public Result add(@RequestBody TimeDTO dto) {
		System.out.println(dto.getDate());
		return Result.ok(dto);
	}

	@GetMapping("/list")
	public Result list() {
		List<SysUser> list = sysUserService.list();
		return Result.ok(list);
	}

	@GetMapping("path")
	public Result path(HttpServletRequest request) {
		System.out.println(request.getSession().getServletContext().getContextPath());
		System.out.println(request.getSession().getServletContext().getRealPath(""));
		System.out.println(request.getSession().getServletContext().getRealPath("/"));
		return Result.ok();
	}

	@GetMapping("mapper")
	public Result mapper() {
//		sysUserService.getById();
		return Result.ok();
	}

	@GetMapping("get")
	public Result get(@RequestParam SexEnum sex) {
		System.out.println(sex);

		SysUser byId = sysUserService.getById("1");
		return Result.ok(byId);
	}

	@GetMapping("servlet")
	public Result servlet() {

		return Result.ok();
	}

}
