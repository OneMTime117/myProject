package com.yh.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TimeTypeEnum {

	TO_DAY("0", "今日"),
	TO_WEEK("0", "本周"),
	LAST_WEEK("0", "上周"),
	TO_MONTH("0", "本月"),
	LAST_MONTH("0", "上月"),
	TO_YEAR("0", "本年"),
	LAST_YEAR("0", "去年");

	private final String code;

	private final String msg;
}
