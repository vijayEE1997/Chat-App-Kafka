package com.vijay.SpringKafkaMessaging.auth;

public interface AuthService {
	  void putAccessToken(String accessToken, Long userId);
	  Long loginWithAccessToken(String accessToken);
}
