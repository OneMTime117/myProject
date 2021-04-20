package com.yh.system.config.mvc;

import com.yh.common.exception.AuthenticationAccessException;
import com.yh.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	//spring-web异常

	/**
	 * 请求方式不支持
	 */
	@ExceptionHandler({HttpRequestMethodNotSupportedException.class})
	@ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
	public Result handleException(HttpRequestMethodNotSupportedException e) {
		log.error(e.getMessage(), e);
		return Result.error("不支持' " + e.getMethod() + "'请求");
	}

	/**
	 * 请求参数错误（GET）
	 */
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public Result handleException(MissingServletRequestParameterException e) {
		log.error(e.getMessage(), e);
		return Result.error("请求参数错误，请输入正确的参数");
	}

	/**
	 * 请求参数错误（POST）
	 */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public Result handleException(HttpMessageNotReadableException e) {
		log.error(e.getMessage(), e);
		return Result.error("请求参数错误，请输入正确的参数");
	}

	//自定义异常

	/**
	 * 身份授权异常
	 */
	@ExceptionHandler(AuthenticationAccessException.class)
	public Result handleException(AuthenticationAccessException e) {
		return Result.error("身份授权错误");
	}

	/**
	 * 业务异常
	 */
	@ExceptionHandler(BusinessException.class)
	public Result handleException(BusinessException e) {
		return Result.error(e.getMessage());
	}

	// BeanValidation参数校验异常
	/*
	 * @RequestBody 参数校验异常
	 *
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Result handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
		String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
		return Result.error(message);
	}

	/*
	 * @RequestParam 参数校验异常
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	public Result handleConstraintViolation(ConstraintViolationException e) {
		String message = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining());
		return Result.error(message);
	}

	/**
	 * 拦截未知的运行时异常
	 */
	@ExceptionHandler(RuntimeException.class)
	public Result notFount(RuntimeException e) {
		if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
			throw e;
		}
		log.error("运行时异常:", e);
		return Result.error("未知异常:" + e.getMessage());
	}

	/**
	 * 拦截Exception异常
	 */
	@ExceptionHandler(Exception.class)
	public Result handleException(Exception e) throws Exception {
		log.error(e.getMessage(), e);
		return Result.error("服务器错误，请联系管理员");
	}

//	/*
//	 * 处理Get请求中 使用@Valid 验证路径中请求实体校验失败后抛出的异常
//	 */
//	@ExceptionHandler(BindException.class)
//	public Result handleBind(BindException e) {
//		String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
//		return Result.error(message);
//	}
}
