package com.yh.system.config.mvc.sqlinjector;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * @author yuanhuan
 * @description: 自定义注入的sql
 * @date 2021/4/23 10:48
 */

public class DeleteAll extends AbstractMethod {

	@Override
	public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
		SqlMethod sqlMethod = SqlMethod.LOGIC_DELETE;
		String methodName = "deleteAll";//对应配置的mapper方法名
		String sql;
		SqlSource sqlSource;
		if (tableInfo.isWithLogicDelete()) {//逻辑删除
			//只拼接表名和逻辑删除条件判断
			sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(), this.sqlLogicSet(tableInfo), "", "");
			sqlSource = this.languageDriver.createSqlSource(this.configuration, sql, modelClass);
			return this.addUpdateMappedStatement(mapperClass, modelClass, this.getMethod(sqlMethod), sqlSource);
		} else {//非逻辑删除
			sqlMethod = SqlMethod.DELETE;
			//只拼接表名
			sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(), "", "");
			sqlSource = this.languageDriver.createSqlSource(this.configuration, sql, modelClass);
			return this.addDeleteMappedStatement(mapperClass, methodName, sqlSource);
		}
	}
}
