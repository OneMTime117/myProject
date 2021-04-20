package com.yh.system.config.mvc;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;

//自定义identifierGenerator,id生成器
public class MybatisPlusIdGenerator implements IdentifierGenerator {
	@Override
	public Number nextId(Object entity) {
		return null;
	}

	@Override
	public String nextUUID(Object entity) {
		return null;
	}
}
