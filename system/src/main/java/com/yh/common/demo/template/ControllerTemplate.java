package com.yh.common.demo.template;

import com.yh.common.domain.dto.BaseDTO;
import com.yh.common.domain.dto.BasePageDTO;
import com.yh.common.domain.dto.IdDTO;
import com.yh.common.domain.dto.IdsDTO;
import com.yh.system.config.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags="controller接口模板")
@RestController
@RequestMapping("controllerTemplate")
public class ControllerTemplate {
	@ApiOperation(value = "综合查询", notes = "综合查询", httpMethod = "POST")
	@PostMapping("/list")
	public ResponseEntity<?> list(@RequestBody BasePageDTO queryDto){
		return Result.ok();
	}

	@ApiOperation(value = "单条查询", notes = "单条查询", httpMethod = "GET")
	@GetMapping("/get")
	public ResponseEntity<?> get(@RequestParam String id){
		return Result.ok();
	}

	@ApiOperation(value = "更新", notes = "更新", httpMethod = "POST")
	@PostMapping("/update")
	public ResponseEntity<?> update(@RequestBody BaseDTO dto){
		return Result.ok();
	}

	@ApiOperation(value = "单条删除", notes = "单条删除", httpMethod = "POST")
	@PostMapping("delete")
	public ResponseEntity<?> delete(@RequestBody IdDTO dto){
		return Result.ok();
	}

	@ApiOperation(value = "批量删除", notes = "批量删除", httpMethod = "POST")
	@PostMapping("deleteBatch")
	public ResponseEntity<?> deleteBatch(@RequestBody IdsDTO dto){
		return Result.ok();
	}

}
