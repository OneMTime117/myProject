package com.yh.system.config.sqlinjector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;

import java.util.List;

/**
 * @author yuanhuan
 * @description: 自定义mybatisPlus的sql注入器
 * @date 2021/4/23 10:42
 */
public class MySqlInjector extends DefaultSqlInjector {

	@Override
	public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
		//注入BaseMapper的sql
		List<AbstractMethod> methodList = super.getMethodList(mapperClass);
		//注入自定义sql
		methodList.add(new DeleteAll());
		return methodList;
	}
}
