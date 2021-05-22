package com.vijay.SpringKafkaMessaging.auth;

public interface AuthRepository {
    void putAccessToken(String token, Long userId);

    void loginWithAccessToken(String mobile, String token);
}
