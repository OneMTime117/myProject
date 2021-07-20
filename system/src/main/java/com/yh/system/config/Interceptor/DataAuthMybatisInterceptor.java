package com.yh.system.config.Interceptor;

import cn.hutool.core.util.StrUtil;
import com.yh.common.exception.AuthenticationAccessException;
import com.yh.common.util.mybatis.MybatisInterceptorUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author yuanhuan
 * @description: 数据权限拦截器，搭配数据权限注解@DataAuth使用
 * @date 2021/4/21 16:55
 */

//对mybatis执行器的查询方法进行拦截
@Intercepts({
		@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
		@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
@Slf4j
public class DataAuthMybatisInterceptor implements Interceptor {

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		log.info("进入拦截器");
		//方法参数:ms、param
		Object[] args = invocation.getArgs();
		MappedStatement ms = (MappedStatement) args[0];
		Object param = args[1];
		//获取BoundSql，sql信息对象
		BoundSql boundSql = ms.getBoundSql(param);

		//判断mapper方法上，是否有@AuthData注解
		DataAuth dataAuth = null;
		try {
			String id = ms.getId();
			String className = id.substring(0, id.lastIndexOf("."));
			String methodName = id.substring(id.lastIndexOf(".") + 1, id.length());
			final Class<?> cls = Class.forName(className);
			final Method[] method = cls.getMethods();
			for (Method me : method) {
				if (me.getName().equals(methodName) && me.isAnnotationPresent(DataAuth.class)) {
					dataAuth = me.getAnnotation(DataAuth.class);
				}
			}
		} catch (Exception e) {
			log.error("通过反射解析mapper方法错误", e);
		}
		if (dataAuth == null) {//不存在则，直接返回
			return invocation.proceed();
		}

		//获取requestHeader,校验权限
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		if (requestAttributes == null) {//跳过非Web接口调用时的权限过滤
			return invocation.proceed();
		}
		HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
		String token = request.getHeader("token");
		if (StrUtil.isBlank(token)) {
			throw new AuthenticationAccessException("权限异常");
		}
		//编写权限过滤sql,todo 伪代码省略，使用原sql
		String newSql = boundSql.getSql();
		//创建新的ms对象
		MappedStatement newMappedStatement = MybatisInterceptorUtil.mappedStatementBuild(ms, newSql);
		//将新的ms对象放在invocation参数中
		args[0] = newMappedStatement;
		return invocation.proceed();
	}


}


