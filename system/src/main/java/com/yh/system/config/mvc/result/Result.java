package com.yh.system.config.mvc.result;

import lombok.Getter;
import org.springframework.http.HttpStatus;

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

	public static Result ok() {
		Result result = new Result();
		result.code = HttpStatus.OK.value();//200
		return result;
	}

	public static Result ok(Object data) {
		Result result = new Result();
		result.code = HttpStatus.OK.value();//200
		result.data = data;
		return result;
	}

	public static Result error() {
		Result result = new Result();
		result.code = HttpStatus.INTERNAL_SERVER_ERROR.value();//500
		return result;
	}

	public static Result error(String msg) {
		Result result = new Result();
		result.code = HttpStatus.INTERNAL_SERVER_ERROR.value();//500
		result.msg = msg;
		return result;
	}
}
