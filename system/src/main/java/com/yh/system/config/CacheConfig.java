package com.yh.system.config;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@EnableCaching
@Configuration
public class CacheConfig {

	@Bean
	public CacheManager cacheManager(RedisConnectionFactory factory) {
		ObjectMapper objectMapper = new ObjectMapper();
		JacksonConfig.configureDate(objectMapper);
		JacksonConfig.registerJavaTimeModule(objectMapper);
		//添加额外属性@Class,实现复杂对象的序列化
		objectMapper.activateDefaultTyping(objectMapper.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.WRAPPER_OBJECT);
		//反序列化时,允许未知属性
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);

		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
		jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

		//通过RedisCacheConfiguration对象生成一个默认config对象，并修改其默认属性
		RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig()
				.entryTtl(Duration.ofDays(1))//设置缓存过期时间（1天）
				.disableCachingNullValues()//不允许缓存有空值
				//设置缓存管理器key-value数据序列化策略（key为String，value为json）
				.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
				.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer));

		//通过RedisConnectionFactory、RedisCacheConfiguration来创建RedisCacheManager
		return RedisCacheManager.builder(factory).cacheDefaults(cacheConfig).build();
	}
//
//	//基于JDK ConcurrentMap实现缓存
//	@Bean
//	public CacheManager cacheManager(){
//		ConcurrentMapCacheManager concurrentMapCacheManager = new ConcurrentMapCacheManager();
//		return concurrentMapCacheManager;
//	}

}
