package com.yh.system.websocket;

import com.yh.system.config.websocket.MyHandshakeInterceptor;
import com.yh.system.domain.dto.user.UserInfoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
@Component
public class MyWebSocketHandler extends TextWebSocketHandler {
	private static final CopyOnWriteArrayList<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

	//连接建立后
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		UserInfoDTO userInfoDTO =(UserInfoDTO)session.getAttributes().get(MyHandshakeInterceptor.SESSION_USER);
		log.info("---------->"+userInfoDTO.getUsername()+"进入聊天室");
		sessions.add(session);
		//查询未读消息
		TextMessage textMessage = new TextMessage(userInfoDTO.getUsername()+"的未读消息");
		session.sendMessage(textMessage);
	}

	//服务端收到消息后
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		//保存用户消息,并发送给聊天室所有人
		for (WebSocketSession webSocketSession : sessions) {
			webSocketSession.sendMessage(message);
		}
	}

	//消息传输错误后
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		if (session.isOpen()){
			session.close();
		}
		UserInfoDTO userInfoDTO =(UserInfoDTO)session.getAttributes().get(MyHandshakeInterceptor.SESSION_USER);
		log.info("---------->"+userInfoDTO.getUsername()+"离开聊天室");
		sessions.remove(session);
	}

	//连接关闭后
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		UserInfoDTO userInfoDTO =(UserInfoDTO)session.getAttributes().get(MyHandshakeInterceptor.SESSION_USER);
		log.info("---------->"+userInfoDTO.getUsername()+"离开聊天室");
		sessions.remove(session);
	}

	//给所有在线用户发送请求
	public static void sendMessageToUsers(TextMessage message) {
		for (WebSocketSession webSocketSession : sessions) {
			sendMessageToUser(webSocketSession,message);
		}
	}

	//给某个用户发送消息
	public static void sendMessageToUser(WebSocketSession session, TextMessage message) {
		try {
			if (session.isOpen()) {
				session.sendMessage(message);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//给指定id用户发送消息
	public static void sendMessageToUserId(String userid, TextMessage message) {
		for (WebSocketSession session : sessions) {
			UserInfoDTO userInfoDTO =(UserInfoDTO)session.getAttributes().get(MyHandshakeInterceptor.SESSION_USER);
			if (Objects.equals(userid,userInfoDTO.getId())){
				try {
					if (session.isOpen()) {
						session.sendMessage(message);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}

}
