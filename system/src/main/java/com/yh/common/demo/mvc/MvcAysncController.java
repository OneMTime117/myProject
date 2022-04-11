package com.yh.common.demo.mvc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Executors;

@Api(tags="mvc请求异步处理")
@RestController
@RequestMapping("/mvcAysnc")
public class MvcAysncController {

	@ApiOperation(value = "单条查询", notes = "单条查询", httpMethod = "GET")
	@GetMapping("/get")
	public DeferredResult<?> get(@RequestParam String id){

		DeferredResult<String> deferredResult = new DeferredResult<>();
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				//异步处理代码逻辑，保存需要响应输出的结果
				deferredResult.setResult("hhhhSync");
			}
		};

		//指定异步线程
		Executors.newSingleThreadExecutor().submit(runnable);
		return deferredResult;
	}
}
