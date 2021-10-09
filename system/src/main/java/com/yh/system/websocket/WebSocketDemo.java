package com.yh.system.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
@ServerEndpoint("/websocket/demo/{userId}")
public class WebSocketDemo {

	// 线程安全的integer，统计连接推送的客户端数
	private static AtomicInteger onlineCount = new AtomicInteger(0);
	// 线程安全的Map，来保存所有客户端对应的webSocket服务对象
	private static ConcurrentSkipListMap<String, WebSocketDemo> webSocketMap = new ConcurrentSkipListMap<String, WebSocketDemo>();
	// 客户端与对于webSocketServer的Session对象，webSocketServer通过它来向客户端发送数据
	private Session session;

	@OnOpen // websocket连接建立成功，执行的方法
	public void onOpen(Session session, @PathParam("userId") String userId) {
		// 获取webSocket连接建立的session
		this.session = session;
		// 将该webSocket放入Map集合
		webSocketMap.put(userId, this);
		// webSocket统计+1
		onlineCount.addAndGet(1);
		log.info("-------webSocket连接开启，当前服务连接数为" + onlineCount);
		try {
			// 服务器主动推送消息
			session.getBasicRemote().sendText("连接成功");
		} catch (IOException e) {
			log.debug("webSocket IO异常");
			e.printStackTrace();
		}
	}

	@OnClose // 当websocket连接关闭时，执行的方法
	public void onClose(@PathParam("userId") String userId) {
		webSocketMap.remove(userId);
		onlineCount.decrementAndGet();
		log.info("-------webSocket连接关闭，当前服务连接数为" + onlineCount);
	}

	@OnError // websocket服务发生错误时，执行方法
	public void onError(Session session, Throwable error) {
		log.error("发生错误");
		error.printStackTrace();
	}

	@OnMessage // 当webSocket收到客户端消息后，执行的方法（可以作为启动消息推送的入口）
	public void onMessage(String message, Session session, @PathParam("userId") String userId) {
		log.info(message);
		String resultMessage = "";
		try {
			sentMessage(resultMessage, session);
		} catch (IOException e) {
			e.printStackTrace();
			log.error("消息发送失败");
		}
	}


	// 点对点发送
	private static void sentMessage(String message, Session session) throws IOException {
		session.getBasicRemote().sendText(message);
	}

	//根据userid进行点对点推送
	public static void sentMessageByUserId(String message, String userId) throws IOException {
		WebSocketDemo webSocketDemo = webSocketMap.get(userId);
		if (webSocketDemo != null) {
			Session session = webSocketDemo.session;
			session.getBasicRemote().sendText(message);
		}
	}

	//全局发送
	public static void pushMessage(String message) throws IOException {
		Set<Map.Entry<String, WebSocketDemo>> entrySet = webSocketMap.entrySet();
		for (Map.Entry<String, WebSocketDemo> entry : entrySet) {
			WebSocketDemo value = entry.getValue();
			sentMessage(message, value.session);
		}
	}
}
