package com.yh.system.controller.sys;


import com.yh.system.config.result.Result;
import com.yh.system.domain.dto.sys.SysDictDTO;
import com.yh.system.service.sys.SysDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yuanhuan
 * @description 字典表管理
 * @date 2021/7/9 11:06
 */


@Api(value = "/sysDict", tags = {"字典表管理"})
@RestController
@RequestMapping("/sysDict")
public class SysDictController {

	@Autowired
	private SysDictService sysDictService;

	//字典字段数据查询
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", dataType = "string", name = "dictCode", value = "字典编码")
	})
	@ApiOperation(value = "字典字段数据查询", notes = "字典字段数据查询", httpMethod = "GET")
	@GetMapping
	public ResponseEntity<List<SysDictDTO>> getFields(String dictCode) {
		List<SysDictDTO> sysDictDTOS = sysDictService.listFieldByDictCode(dictCode);
		return Result.ok(sysDictDTOS);
	}
}
