package com.vijay.SpringKafkaMessaging.cache.repository;
import org.springframework.stereotype.Service;

import com.vijay.SpringKafkaMessaging.cache.JedisFactory;

import redis.clients.jedis.Jedis;

@Service
public class CacheRepositoryImpl implements CacheRepository {

	@Override
	public void putAccessToken(String token, String userId) {

		try(Jedis jedis = JedisFactory.getConnection()){
			jedis.set(token,userId);
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public String getUserIdByAccessToken(String token) {
		
		try(Jedis jedis = JedisFactory.getConnection()){
			return jedis.get(token);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void putActivationCode(String mobile, String activationCode) {
		
		try(Jedis jedis = JedisFactory.getConnection()){

            jedis.hset(mobile, String.valueOf(activationCode), "");
            jedis.expire(mobile, 15 * 60);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String queryMobileActivationCode(String mobile, String activationCode) {
		
        try (Jedis jedis = JedisFactory.getConnection()) {

            return jedis.hget(mobile, activationCode);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
	}

}
