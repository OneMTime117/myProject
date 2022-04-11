package com.yh.system.config.websocket;

import com.yh.system.websocket.MyWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

@ConditionalOnProperty(prefix = "websocket", name = "enabled", havingValue = "true")
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

	@Autowired
	private MyWebSocketHandler myWebSocketHandler;

	@Autowired
	private MyHandshakeInterceptor myHandshakeInterceptor;

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(myWebSocketHandler, "/webSocket/test")
				.setAllowedOrigins("*")
				.addInterceptors(myHandshakeInterceptor);
	}

	@Bean
	public ServletServerContainerFactoryBean createWebSocketContainer() {
		ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
		//设置servlet容器允许的最大消息缓存大小、空闲时间
		container.setMaxTextMessageBufferSize(8192);
		container.setMaxBinaryMessageBufferSize(8192);
		container.setMaxSessionIdleTimeout(10 * 1000L);
		return container;
	}

}
