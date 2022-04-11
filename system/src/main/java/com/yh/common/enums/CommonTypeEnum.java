package com.yh.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum  CommonTypeEnum {
	NOT_COMPLETE("0","未完成"),
	COMPLETE("1","完成");

	private final String code;

	private final String msg;
}
