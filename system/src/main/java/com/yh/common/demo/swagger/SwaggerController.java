package com.yh.common.demo.swagger;

import com.yh.system.config.mvc.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Api(tags = "swagger文档接口demo")
@RestController
@RequestMapping("/demo/swagger")
public class SwaggerController {

	@GetMapping("/demo1")
	@ApiOperation(value = "示例1", response = SwaggerDemoDTO.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "name", value = "姓名", required = true, paramType = "query"),
			@ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "query"),
			@ApiImplicitParam(name = "time", value = "时间", required = false, paramType = "query")
	})
	public Result demoResult(@RequestParam String name, @RequestParam String password, LocalDateTime time) {
		SwaggerDemoDTO dto = new SwaggerDemoDTO();
		dto.setName(name);
		dto.setPassword(password);
		dto.setTime(time);
		return Result.ok(dto);
	}

	@PostMapping("/demo2")
	@ApiOperation("示例2")
	public SwaggerDemoDTO demoDemoDTO(@RequestBody SwaggerDemoDTO dto) {
		return dto;
	}
}
