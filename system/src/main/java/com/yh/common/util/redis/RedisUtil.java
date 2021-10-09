package com.yh.common.util.redis;

import com.yh.common.util.json.JacksonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author yuanhuan
 * @description: redis工具类，简化value、hash数据类型的set、get方法
 * 内部使用jackson进行对象的序列化和反序列化
 * @date 2021/7/6 15:07
 */

@Component
public class RedisUtil {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Autowired
	private HashOperations<String, String, Object> hashOperations;

	@Autowired
	private ValueOperations<String, Object> valueOperations;

	//单位默认使用 s,默认1天
	public static final long DEFAULT_TIME_OUT = 24 * 60 * 10;

	public static final long NOT_TIME_OUT = -1;

	/**
	 * 设置hash值，默认过期时间
	 */
	public void setHashObject(String key, String hashKey, Object obj) {
		hashOperations.put(key, hashKey, obj);
		redisTemplate.expire(key, DEFAULT_TIME_OUT, TimeUnit.SECONDS);
	}

	/**
	 * 设置hash值，添加过期时间
	 * 当timeout=-1时，为永不过期
	 * redis只支持对顶层key设置过期时间，因此hashOperations不提供设置过期时间的api
	 */
	public void setHashObject(String key, String hashKey, Object obj, long timeout) {
		hashOperations.put(key, hashKey, obj);
		if (timeout == NOT_TIME_OUT) {
			//设置为用不过期
			redisTemplate.persist(key);
		} else {
			redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
		}
	}


	/**
	 * 获取hash值
	 */
	public <T> T getHashObject(String key, String hashKey, Class<T> clazz) throws IOException {
		Object o = hashOperations.get(key, hashKey);
		//object为json反序列化为Object的结果,需要进一步转化为实体类
		if (null == o) {
			return null;
		}
		return JacksonUtil.objectToBean(o, clazz);
	}

	/**
	 * 获取hash所有值
	 */
	public <T> Map<String, T> getAllHashObject(String key, Class<T> clazz) throws IOException {
		HashMap<String, T> hashMap = new HashMap<>();
		hashOperations.putAll(key, hashMap);
		return hashMap;
	}

	/**
	 * 获取hash值,指定泛型,转为List集合
	 */
	public <T> List<T> getHashObjectList(String key, Class<T> clazz) throws IOException {
		Object o = valueOperations.get(key);
		//object为json序列化的字符串,需要进一步反序列化为对象
		if (null == o) {
			return null;
		}
		String jsonStr = JacksonUtil.toJsonStr(o);
		return JacksonUtil.toList(jsonStr, clazz);
	}

	/**
	 * 设置value值,默认过期时间
	 */
	public void setObject(String key, Object obj) {
		valueOperations.set(key, obj, DEFAULT_TIME_OUT, TimeUnit.SECONDS);
	}

	/**
	 * 设置value值,指定过期时间
	 * timeout=-1时,为永不过期
	 */
	public void setObject(String key, Object obj, long timeout) {
		if (timeout == NOT_TIME_OUT) {
			valueOperations.set(key, obj);
		} else {
			valueOperations.set(key, obj, timeout, TimeUnit.SECONDS);
		}
	}

	/**
	 * 获取value值
	 */
	public <T> T getObject(String key, Class<T> clazz) throws IOException {
		Object o = valueOperations.get(key);
		//object为json序列化的字符串,需要进一步反序列化为对象
		if (null == o) {
			return null;
		}
		return JacksonUtil.objectToBean(o, clazz);
	}

	/**
	 * 获取value值,指定泛型,转为List集合
	 */
	public <T> List<T> getObjectList(String key, Class<T> clazz) throws IOException {
		Object o = valueOperations.get(key);
		//object为json序列化的字符串,需要进一步反序列化为对象
		if (null == o) {
			return null;
		}
		String jsonStr = JacksonUtil.toJsonStr(o);
		return JacksonUtil.toList(jsonStr, clazz);
	}


	/**
	 * 更新value的过期时间
	 */
	public void expireObject(String key) {
		redisTemplate.expire(key, DEFAULT_TIME_OUT, TimeUnit.SECONDS);
	}


	/**
	 * 更新value的过期时间
	 */
	public void expireObject(String key,long timeout) {
		redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
	}
}
