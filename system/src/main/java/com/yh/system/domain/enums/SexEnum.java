package com.yh.system.domain.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SexEnum implements IEnum<String> {

	NONE("0", "未知"),
	MAN("1", "男"),
	WOMAN("2", "女");


	private String code;
	private String msg;

	//作为数据库字段的数据
	@Override
	public String getValue() {
		return this.code;
	}

	@Override
	public String toString() {
		return this.msg;
	}
}
