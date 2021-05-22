package com.vijay.SpringKafkaMessaging.websocket;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

@Service
public class MessageHandlerImpl implements MessageHandler {

	/*
	 * With MessageHandler we are able to handle adding or removing WebSocketSession sessions 
	 * from the pool as well as sending messages to a target user’s WebSocket sessions.
	 */
	@Override
	public void addSessionToPool(Long userId, WebSocketSession session) {
		
		Set<WebSocketSession> userSessions = WebSocketPool.websockets.get(userId);
		
		if(userId !=null) {
			userSessions.add(session);
			WebSocketPool.websockets.put(userId,userSessions);
		}
		else
		{
			Set<WebSocketSession> newUserSessions = new HashSet<>();
	     	newUserSessions.add(session);
		    WebSocketPool.websockets.put(userId, newUserSessions);
		}

	}

	@Override
	public void sendMessageToUser(Long userId, String message) throws IOException {
		
	    Set<WebSocketSession> userSessions = WebSocketPool.websockets.get(userId);
	    
	    if (userSessions == null) {
	        return;
	      }

	      TextMessage textMessage = new TextMessage(message);
	      for (WebSocketSession session : userSessions) {
	        session.sendMessage(textMessage);
	      }

	}

	@Override
	public void removeFromSessionToPool(Long userId, WebSocketSession session) {
		
	    Set<WebSocketSession> userSessions = WebSocketPool.websockets.get(userId);

	    if (userSessions != null) {
	      for (WebSocketSession sessionItem : userSessions) {
	        if (sessionItem.equals(session)) {
	          userSessions.remove(session);
	        }
	      }
	    }
	    WebSocketPool.websockets.put(userId, userSessions);
	  }

}
