package com.yh.system.config.result;

import com.yh.common.exception.AuthenticationAccessException;
import com.yh.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * @author yuanhuan
 * @description: 全局异常处理器
 * @date 2021/4/21 16:58
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	//spring-web异常

	/**
	 * 请求方式不支持
	 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity handleException(HttpRequestMethodNotSupportedException e) {
		log.error(e.getMessage(), e);
		return Result.error("不支持' " + e.getMethod() + "'请求");
	}

	/**
	 * 请求参数错误（GET）
	 */
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity handleException(MissingServletRequestParameterException e) {
		log.error(e.getMessage(), e);
		return Result.error("请求参数错误，请输入正确的参数");
	}

	/**
	 * 请求参数错误（POST）
	 */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity handleException(HttpMessageNotReadableException e) {
		log.error(e.getMessage(), e);
		return Result.error("请求参数错误，请输入正确的参数");
	}

	// BeanValidation参数校验异常
	/*
	 * @RequestBody Bean约束校验异常
	 *
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
		String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(","));
		return Result.error(message);
	}

	/*
	 * @ModelAttribute Bean约束校验异常
	 */
	@ExceptionHandler(BindException.class)
	public ResponseEntity handleBind(BindException e) {
		String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(","));
		return Result.error(message);
	}

	/*
	 * @RequestParam/@PathVariable 方法参数约束校验异常
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity handleConstraintViolation(ConstraintViolationException e) {
		String message = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(","));
		return Result.error(message);
	}

	//自定义异常
	/**
	 * 身份授权异常
	 */
	@ExceptionHandler(AuthenticationAccessException.class)
	public ResponseEntity handleException(AuthenticationAccessException e) {
		return Result.unauthorized(e.getMessage());
	}

	/**
	 * 业务异常
	 */
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity handleException(BusinessException e) {
		return Result.error(e.getMessage());
	}

	/**
	 * 拦截未知的运行时异常
	 */
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity notFount(RuntimeException e) {
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
	public ResponseEntity handleException(Exception e) throws Exception {
		log.error(e.getMessage(), e);
		return Result.error("服务器错误，请联系管理员");
	}


}
