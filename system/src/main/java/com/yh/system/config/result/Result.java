package com.yh.system.config.result;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author yuanhuan
 * @description: @responseBody请求响应的结果集
 * @date 2021/4/21 16:58
 */

@Getter
public class Result {

	private Object data;
	private Integer code;
	private String msg = "";

	public static ResponseEntity ok() {
		Result result = new Result();
		result.code = HttpStatus.OK.value();//200
		return new ResponseEntity(result, HttpStatus.OK);
	}

	public static ResponseEntity ok(Object data) {
		Result result = new Result();
		result.code = HttpStatus.OK.value();//200
		result.data = data;
		return new ResponseEntity(result, HttpStatus.OK);
	}

	public static ResponseEntity error() {
		Result result = new Result();
		result.code = HttpStatus.INTERNAL_SERVER_ERROR.value();//500
		return new ResponseEntity(result, HttpStatus.OK);
	}

	public static ResponseEntity error(String msg) {
		Result result = new Result();
		result.code = HttpStatus.INTERNAL_SERVER_ERROR.value();//500
		result.msg = msg;
		return new ResponseEntity(result, HttpStatus.OK);
	}
}
