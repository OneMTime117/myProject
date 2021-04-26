package com.yh.common.util.web;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yuanhuan
 * @description: 通过springMVC提供的RequestContextHolder工具类，获取当前线程的Servlet对象
 * @date 2021/4/22 15:29
 */

public class WebServletUtil {

	//获取ServletRequestAttributes对象
	public static ServletRequestAttributes getRequestAttributes() {
		RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
		if (attributes == null) {
			throw new RuntimeException("无法获取当前请求对象");
		}
		return (ServletRequestAttributes) attributes;
	}

	/**
	 * 获取request
	 */
	public static HttpServletRequest getRequest() {
		return getRequestAttributes().getRequest();
	}

	/**
	 * 获取response
	 */
	public static HttpServletResponse getResponse() {
		return getRequestAttributes().getResponse();
	}

	/**
	 * 设置ServletRequest properties
	 */
	public static void setRequestAttribute(String name, Object value) {
		getRequest().setAttribute(name, value);
	}

	/**
	 * 获取ServletRequest properties中的值
	 */
	public static Object getRequestAttribute(String name) {
		return getRequest().getAttribute(name);
	}
}
