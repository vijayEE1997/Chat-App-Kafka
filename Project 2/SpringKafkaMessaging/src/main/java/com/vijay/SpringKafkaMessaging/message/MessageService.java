package com.vijay.SpringKafkaMessaging.message;

import java.util.List;

import com.vijay.SpringKafkaMessaging.persistence.model.Message;

public interface MessageService {
	  public void sendMessage(String accessToken, Long sendTo, String msg);
	  List<Message> getMessageHistory(Long fromUserId, Long toUserId);
}
