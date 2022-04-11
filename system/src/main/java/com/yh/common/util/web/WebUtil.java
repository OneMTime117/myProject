package com.yh.common.util.web;

import cn.hutool.core.util.StrUtil;

import javax.servlet.http.HttpServletRequest;

public class WebUtil {

	public static String getIp() {
		HttpServletRequest request = WebServletUtil.getRequest();
		if (request == null)
			return "";
		String ip = request.getHeader("X-Requested-For");
		if (StrUtil.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Forwarded-For");
		}
		if (StrUtil.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (StrUtil.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StrUtil.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (StrUtil.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (StrUtil.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}

		if ("0:0:0:0:0:0:0:1".equals(ip)) {
			return "127.0.0.1";
		}
		return ip;
	}
}
