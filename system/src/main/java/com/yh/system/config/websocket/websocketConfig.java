package com.yh.system.config.websocket;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;


@Configuration
@ConditionalOnProperty(prefix = "websocket", name = "enabled", havingValue = "true")
public class websocketConfig {

	@Bean
	public ServerEndpointExporter getServerEndpointExporter() {
		//管理webSocket服务的组件，交给springboot内置Tomcat
		return new ServerEndpointExporter();
	}
}
