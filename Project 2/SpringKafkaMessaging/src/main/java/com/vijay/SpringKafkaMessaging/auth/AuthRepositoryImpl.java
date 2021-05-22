package com.vijay.SpringKafkaMessaging.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vijay.SpringKafkaMessaging.persistence.model.AccessToken;
import com.vijay.SpringKafkaMessaging.persistence.repository.AccessTokenRepository;
import com.vijay.SpringKafkaMessaging.persistence.repository.CacheRepository;

import java.util.Calendar;

@Service
public class AuthRepositoryImpl implements AuthRepository {

    @Autowired
    CacheRepository cacheRepository;

    @Autowired
    AccessTokenRepository accessTokenRepository;

    @Override
    public void putAccessToken(String token, Long userId) {

        // store token in the cache
        cacheRepository.putAccessToken(token, String.valueOf(userId));

        // store token in the persistence
        AccessToken accessToken = new AccessToken(token,userId,Calendar.getInstance().getTime());

        accessTokenRepository.save(accessToken);
    }

    @Override
    public void loginWithAccessToken(String mobile, String code) {
        // TODO
    }
}