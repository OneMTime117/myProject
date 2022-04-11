package com.yh.system.config.websocket;

import com.yh.common.util.UserInfoUtil;
import com.yh.system.domain.dto.user.UserInfoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@Slf4j
@Component
public class MyHandshakeInterceptor implements HandshakeInterceptor {

	public static final String SESSION_USER="user";

	@Autowired
	private UserInfoUtil userInfoUtil;

	//webSocket创建握手连接
	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
		UserInfoDTO userInfoDTO = userInfoUtil.getUserInfoOfUrl();
		attributes.put(SESSION_USER,userInfoDTO);
		log.info("webSocket发送握手请求");
		return true;
	}

	//webSocket完成握手请求
	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
		System.out.println("webSocket完成握手请求");
	}
}
