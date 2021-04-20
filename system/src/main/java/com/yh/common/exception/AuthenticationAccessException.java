package com.yh.common.exception;

public class AuthenticationAccessException extends RuntimeException {
	//指定序列化唯一标识符（避免发生变化,影响反序列化）
	static final long serialVersionUID = 1L;

	public AuthenticationAccessException(String message) {
		super(message);
	}
}
