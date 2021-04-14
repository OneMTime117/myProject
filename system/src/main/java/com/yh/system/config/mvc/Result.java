package com.yh.system.config.mvc;

import lombok.Getter;
import org.springframework.http.HttpStatus;

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
