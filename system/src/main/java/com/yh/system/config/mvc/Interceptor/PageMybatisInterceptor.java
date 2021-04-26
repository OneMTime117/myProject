package com.yh.system.config.mvc.Interceptor;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yh.common.util.mybatis.MybatisInterceptorUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author yuanhuan
 * @description: 基于mybatisPlus中的page分页参数对象，手写简单分页功能的mybatis拦截器
 * @date 2021/4/21 16:56
 */

//对mybatis执行器的查询方法进行拦截
@Intercepts({
		@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
		@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
@Slf4j
public class PageMybatisInterceptor implements Interceptor {
	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
	}

	//拦截处理指定处理器方法,进行动态代理
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		log.info("进入拦截器");
		//方法参数:ms、param
		Object[] args = invocation.getArgs();
		MappedStatement ms = (MappedStatement) args[0];
		Object param = args[1];
		//获取BoundSql，sql信息对象
		BoundSql boundSql = ms.getBoundSql(param);

		//获取sql注入参数map(单个参数则使用对象),查询是否含有分页参数IPage
		IPage page = null;
		Object parameterObject = boundSql.getParameterObject();
		if (parameterObject != null) {
			if (parameterObject instanceof Map) {
				Map<?, ?> parameterMap = (Map) parameterObject;
				Iterator entryIterator = parameterMap.entrySet().iterator();

				while (entryIterator.hasNext()) {
					Map.Entry entry = (Map.Entry) entryIterator.next();
					if (entry.getValue() != null && entry.getValue() instanceof IPage) {
						page = (IPage) entry.getValue();
					}
				}
			} else if (parameterObject instanceof IPage) {
				page = (IPage) parameterObject;
			}
		}

		//如果page参数正确，则：
		if (page != null && page.getSize() >= 0L && page.isSearchCount()) {
			log.info("分页参数：{}", JSONUtil.parseObj(page).toString());
			//获取当前sql
			String sql = boundSql.getSql();
			//拼接countSql
			String countSql = "select count(*) from (" + sql + ") a";

			PreparedStatement countPreparedStatement = MybatisInterceptorUtil.preparedStatementBuild(ms, countSql);
//			获取总条数
			ResultSet resultSet = countPreparedStatement.executeQuery();
			int count = 0;
			if (resultSet.next()) {
				count = resultSet.getInt(1);
			}
			page.setTotal(count);

			//拼接分页sql
			String pageSql = sql + " limit " + page.offset() + " , " + page.getSize();
			//创建新的BoundSql、ms对象
			BoundSql pageBoundSql = MybatisInterceptorUtil.boundSqlBuild(ms, pageSql);
			MappedStatement newMappedStatement = MybatisInterceptorUtil.mappedStatementBuild(ms, pageBoundSql);
			//将新的ms对象放在invocation参数中
			args[0] = newMappedStatement;
			//将获取的结果封装
			List result = (List) invocation.proceed();

			//手动关闭countSql执行的连接
			countPreparedStatement.getConnection().close();
			log.info("分页结果集{}", JSONUtil.parseObj(page).toString());
//			return 	page.setRecords(result);//需要对结果为page类型进行额外处理（默认会使用List接口报错）
			return result;
		}
		return invocation.proceed();
	}


}
