package com.vijay.SpringKafkaMessaging.websocket;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.web.socket.WebSocketSession;

public class WebSocketPool {
	//WebSocketPool holds client sessions in a map of <user_id, <set of WebSocketSession>>
	public static Map<Long, Set<WebSocketSession>> websockets = new HashMap<>();
}
