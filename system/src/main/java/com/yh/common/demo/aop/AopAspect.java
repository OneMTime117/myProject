package com.yh.common.demo.aop;

import com.yh.common.exception.BusinessException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Aspect
public class AopAspect {

	@Pointcut("execution(private * com.yh.common.demo.aop.AopTarget.getName(..))")
	public void name() {
	}

	@Before("name()&&args(fixed)")
	public void beforeAop(JoinPoint joinPoint,String fixed) {
		System.out.println(fixed);
		System.out.println("前置通知");
	}

	@After(value = "name()")
	public void afterAop(JoinPoint joinPoint) {
		System.out.println("最终通知");
	}

	@AfterReturning(value = "name()", returning = "retVal")
	public void afterAop(JoinPoint joinPoint, Object retVal) {
		System.out.println("返回值:"+retVal);
		((Map<String, String>) retVal).put("name","yh");
		System.out.println("返回前通知");
	}

	@AfterThrowing(value = "name()",throwing = "e")
	public void afterAop(JoinPoint joinPoint, BusinessException e) {
		System.out.println("异常处理");
	}

	@Around(value = "name()")
	public Object around(ProceedingJoinPoint proceedingJoinPoint)  {
		System.out.println("环绕通知-方法执行前");

		Object proceed = null;
		try {
			proceed = proceedingJoinPoint.proceed();
		} catch (Throwable throwable) {
			System.out.println("环绕通知-处理异常");
		}
		System.out.println("环绕通知-方法执行前");
		return proceed;
	}
}
