package com.yh.system.controller;

import com.yh.system.config.mvc.R;
import com.yh.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/login")
	public R login(String id){
		userService.mybatisCommonMethods();
		switch (id) {
			case "1":
				return R.ok();
			case "2":
				return R.ok(id);
			case "3":
				return R.error();
			default:
				return R.error(id);
		}
	}
}
