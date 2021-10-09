package com.yh.common.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yuanhuan
 * @description 原生Cors过滤器
 * @date 2021/8/18 11:33
 */

//@WebFilter("/*")
public class CorsFilter implements Filter {
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {

		HttpServletResponse response = (HttpServletResponse) arg1;
		HttpServletRequest request = (HttpServletRequest) arg0;

		/*
		 *当浏览器发出跨域的请求时，会自动添加Origin的请求头，value为请求所在页面域名（ip+端口）
		 *此时服务器端就需要判断该地址是否允许进行访问，允许就将该地址放入响应中，让浏览器判断是否允许跨域
		 *允许当前跨域请求，也可以直接设置为*，表示允许所有跨域请求
		 */
		if (request.getHeader("Origin")!=null){
			response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));

			// 允许请求发送cookie，但此时Access-Control-Allow-Origin就不能设置为*（通过设置为request.getHeader("Origin")，避免这个限制）
			response.setHeader("Access-Control-Allow-Credentials", "true");
			// 允许请求方式、请求头字段
			response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
			response.setHeader("Access-Control-Allow-Headers",
					"Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,Authorization,Token");
			response.setHeader("Access-Control-Max-Age", "5000");// 预检请求有效期
		}
		arg2.doFilter(arg0, arg1);
	}
}
