package com.didispace.config;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableCaching//开启注解
public class RedisConfig {
//private static Logger logger = Logger.getLogger(RedisConfig.class);
	
	@Bean
	@ConfigurationProperties(prefix="spring.redis")
	public JedisPoolConfig getRedisConfig(){
		JedisPoolConfig config = new JedisPoolConfig();
		return config;
	}
	
	@Bean
	@ConfigurationProperties(prefix="spring.redis")
	public JedisConnectionFactory getConnectionFactory(){
		JedisConnectionFactory factory = new JedisConnectionFactory();
		JedisPoolConfig config = getRedisConfig();
		factory.setPoolConfig(config);
		//logger.info("JedisConnectionFactory bean init success.");
		return factory;
	}
	
	
	@Bean
	public RedisTemplate<?, ?> getRedisTemplate(){
		RedisTemplate<?,?> template = new StringRedisTemplate(getConnectionFactory());
		 /* String c = (String) template.opsForValue().get("a");
		  System.out.println("**************"+c+"*******************************");*/
		return template;
	}

	}

