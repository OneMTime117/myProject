package com.yh.system.controller.sys;

import com.yh.system.config.mvc.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController {


	@PostMapping("/login")
	public Result login(String id) {
		return null;
	}
}
