package com.vijay.SpringKafkaMessaging.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vijay.SpringKafkaMessaging.cache.repository.CacheRepository;
import com.vijay.SpringKafkaMessaging.persistence.model.AccessToken;
import com.vijay.SpringKafkaMessaging.persistence.model.User;
import com.vijay.SpringKafkaMessaging.persistence.repository.AccessTokenRepository;
import com.vijay.SpringKafkaMessaging.persistence.repository.UserRepository;

import java.util.Calendar;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    CacheRepository cacheRepository;

    @Autowired
    AccessTokenRepository accessTokenRepository;

    @Autowired
    UserRepository userRepository;
    
    @Override
    public void putAccessToken(String token, Long userId) {

        // store token in the cache
        cacheRepository.putAccessToken(token, String.valueOf(userId));

        // store token in the persistence
        AccessToken accessToken = new AccessToken(token,userId,Calendar.getInstance().getTime());

        accessTokenRepository.save(accessToken);
    }

	@Override
	public Long loginWithAccessToken(String accessToken) {
	    String userIdStr = cacheRepository.getUserIdByAccessToken(accessToken);
	    if (userIdStr == null) {
	      User user = userRepository.findByToken(accessToken);
	      if (user != null)
	        return user.getUserId();
	      else
	        return 0L;
	    }
	    return Long.valueOf(userIdStr);
	}


}