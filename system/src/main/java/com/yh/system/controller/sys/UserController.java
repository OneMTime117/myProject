package com.yh.system.controller.sys;

import com.yh.system.config.mvc.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController {


	@PostMapping("/login")
	public R login(String id){
		return null;
	}
}
