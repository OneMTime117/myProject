package com.yh.common.demo.validator;

import com.yh.common.validator.group.ValidationGroups;
import com.yh.system.config.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;

/**
 * @author yuanhuan
 * @description: HibernateValidator进行controller层数据校验demo
 * @date 2021/5/14 18:16
 */

@Api(tags = "HibernateValidator校验demo")
@RequestMapping("demo/beanValidator")
@RestController
@Validated   //开启容器Bean的方法约束校验
public class BeanValidatorController {

	@ApiOperation("表单传参")
	@PostMapping("/formData")
	public ResponseEntity formData(@Valid @ModelAttribute BeanValidatorDemoDTO dto) {
		return Result.ok();
	}

	@ApiOperation("body传参")
	@PostMapping("/body")
	public ResponseEntity body(@Valid @RequestBody BeanValidatorDemoDTO dto) {
		return Result.ok();
	}

	@ApiOperation("url传参")
	@PostMapping("/urlParam")
	public ResponseEntity urlParam(@Max(10) @RequestParam String id) {
		return Result.ok();
	}

	@ApiOperation("url路径传参")
	@PostMapping("/urlPath/{id}")
	public ResponseEntity urlPath(@Max(10) @PathVariable String id) {
		return Result.ok();
	}

	@ApiOperation("分组校验")
	@PostMapping("/group/insert")
	public ResponseEntity groupInsert(@Validated({ValidationGroups.Insert.class})
	                                  @RequestBody GroupValidatorDemoDTO dto) {
		return Result.ok();
	}
}
