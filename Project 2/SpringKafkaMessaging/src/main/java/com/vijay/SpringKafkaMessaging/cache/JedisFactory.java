package com.vijay.SpringKafkaMessaging.cache;

import org.springframework.beans.factory.annotation.Value;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisFactory {

	@Value("${spring.cache.redis.host}")
	private static String host;

	@Value("${spring.cache.redis.port}")
	private static Integer port;

	@Value("${spring.cache.redis.timeout}")
	private static Integer timeout;

	@Value("${spring.cache.redis.password}")
	private static String password;

	// hide the constructor
	private JedisFactory() {

	}
	
	private static JedisPool jedisPool;
	
	static {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxTotal(128);
		
		jedisPool = new JedisPool(jedisPoolConfig,host,port,timeout,password);
	}
	
	public static Jedis getConnection() {
        return jedisPool.getResource();
    }

}
