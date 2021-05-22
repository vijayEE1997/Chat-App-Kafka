package com.vijay.SpringKafkaMessaging.persistence.repository;


public interface CacheRepository {

    void putAccessToken(String token, String userId);

    String getUserIdByAccessToken(String token);

    void putActivationCode(String mobile, String activationCode);

    String queryMobileActivationCode(String mobile, String activationCode);
}