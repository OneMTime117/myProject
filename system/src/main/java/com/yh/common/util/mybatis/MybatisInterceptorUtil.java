package com.yh.common.util.mybatis;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author yuanhuan
 * @description: mybatis拦截器工具类，用于封装mybatis核心对象的相关创建代码
 * @date 2021/4/22 17:18
 */

public class MybatisInterceptorUtil {

	//通过修改后的sql,构建新的BoundSql
	public static BoundSql boundSqlBuild(MappedStatement ms, String newSql) {
		Configuration configuration = ms.getConfiguration();
		BoundSql boundSql = ms.getBoundSql(ms.getParameterMap());
		BoundSql newBoundSql = new BoundSql(configuration, newSql, boundSql.getParameterMappings(), boundSql.getParameterObject());
		return newBoundSql;
	}

	//通过修改后的sql,构建新的MappedStatement对象
	public static MappedStatement mappedStatementBuild(MappedStatement ms, String newSql) {
		return mappedStatementBuild(ms, boundSqlBuild(ms, newSql));
	}

	//通过修改后的sql,构建新的MappedStatement对象
	public static MappedStatement mappedStatementBuild(MappedStatement ms, BoundSql newBoundSql) {
		//创建sqlSource对象
		MySqlSource mySqlSource = new MySqlSource(newBoundSql);
		//创建新的ms对象
		MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(), ms
				.getId(), mySqlSource, ms.getSqlCommandType());
		builder.resource(ms.getResource());
		builder.fetchSize(ms.getFetchSize());
		builder.statementType(ms.getStatementType());
		builder.keyGenerator(ms.getKeyGenerator());
		if (ms.getKeyProperties() != null && ms.getKeyProperties().length != 0) {
			StringBuilder keyProperties = new StringBuilder();
			for (String keyProperty : ms.getKeyProperties()) {
				keyProperties.append(keyProperty).append(",");
			}
			keyProperties.delete(keyProperties.length() - 1, keyProperties.length());
			builder.keyProperty(keyProperties.toString());
		}
		builder.timeout(ms.getTimeout());
		builder.parameterMap(ms.getParameterMap());
		builder.resultMaps(ms.getResultMaps());
		builder.resultSetType(ms.getResultSetType());
		builder.cache(ms.getCache());
		builder.flushCacheRequired(ms.isFlushCacheRequired());
		builder.useCache(ms.isUseCache());
		return builder.build();
	}

	/**
	 * 通过修改后的sql和原MappedStatement对象,创建PreparedStatement
	 * 需要保证newSql的注入参数没有发生变化
	 */
	public static PreparedStatement preparedStatementBuild(MappedStatement ms, String newSql) throws SQLException {
		BoundSql boundSql = boundSqlBuild(ms, newSql);
		//获取原参数执行器
		DefaultParameterHandler defaultParameterHandler = new DefaultParameterHandler(ms, ms.getParameterMap(), boundSql);
		PreparedStatement preparedStatement = ms.getConfiguration().getEnvironment().getDataSource().getConnection().prepareStatement(newSql);
		//进行参数注入
		defaultParameterHandler.setParameters(preparedStatement);
		return preparedStatement;
	}

	/**
	 * @author yuanhuan
	 * @description: 用于mybatis拦截器，使用BoundSql生成SqlSource对象
	 * @date 2021/4/21 16:55
	 */
	public static class MySqlSource implements SqlSource {
		private BoundSql boundSql;

		public MySqlSource(BoundSql boundSql) {
			this.boundSql = boundSql;
		}

		@Override
		public BoundSql getBoundSql(Object parameterObject) {
			return boundSql;
		}
	}

}
