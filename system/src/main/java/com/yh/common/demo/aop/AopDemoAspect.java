package com.yh.common.demo.aop;

import com.yh.common.exception.AuthenticationAccessException;
import com.yh.common.util.UserInfoUtil;
import com.yh.system.domain.dto.user.UserInfoDTO;
import com.yh.system.domain.dto.user.UserLoginDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.Duration;
import java.time.Instant;

@Component
@Aspect
@Slf4j
public class AopDemoAspect {

	@Autowired
	private UserInfoUtil userInfoUtil;


	//登录日志记录
	@After("execution(* com.yh.system.controller.sys.UserController.login(..))")
	public void afterLogin(JoinPoint joinPoint) {
		UserLoginDTO loginDTO = (UserLoginDTO) joinPoint.getArgs()[0];
		log.info(loginDTO.getUsername() + "登录成功");
	}

	//接口调用日志记录
	@Before("execution(* com.yh.system.controller..*(..))")
	public void beforeController(JoinPoint joinPoint) throws NoSuchMethodException {
		Class<?> targetClass = joinPoint.getTarget().getClass();
		Api targetClassAnnotation = targetClass.getAnnotation(Api.class);
		String apiName = targetClassAnnotation.tags()[0];
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		ApiOperation annotation = method.getAnnotation(ApiOperation.class);
		apiName += "-" + annotation.value();

		//获取当前用户信息
		String username = "匿名用户";
		try {
			UserInfoDTO userInfo = userInfoUtil.getUserInfo();
			username = userInfo.getUsername();
		} catch (Exception e) {
		}
		log.info(username + "调用接口:" + apiName);
	}

	//接口调用性能分析
	@Around("execution(* com.yh.system.controller..*(..))")
	public Object beforeController(ProceedingJoinPoint joinPoint) throws Throwable {
		Class<?> targetClass = joinPoint.getTarget().getClass();
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		String apiName = targetClass.getName() + "." + signature.getName();

		Instant before = Instant.now();
		Object proceed = joinPoint.proceed();
		Instant after = Instant.now();
		long millis = Duration.between(before, after).toMillis();

		log.info(apiName + "-接口调用时长:" + millis + "ms");
		return proceed;
	}

	//需要鉴权接口的拦截
	@Before("execution(* com.yh.system.controller..*(..))&&!within(com.yh.system.controller.sys.UserController)")
	public void authController(JoinPoint joinPoint){
		UserInfoDTO userInfo=null;
		try {
			 userInfo = userInfoUtil.getUserInfo();
		} catch (Exception e) {

		}
		if (userInfo == null) {
			throw new AuthenticationAccessException("token失效");
		}
	}
}
