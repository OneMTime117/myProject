package com.yh.system.config.sqlinjector;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;


public interface MyBaseMapper<T> extends BaseMapper<T> {
	//自定义CRUD方法
	int deleteAll();
}
