package com.youtao.manager.service;

import java.io.IOException;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * @title: RedisService
 * @description: Redis操作Service
 * @Copyright: Copyright (c) 2018
 * @Company: lgxkdream.github.io
 * @author gang.li
 * @version 1.0.0
 * @since 2018年3月9日 上午10:40:22
 */
@Service
public class RedisService {
	
	private static final ObjectMapper MAPPER = new ObjectMapper();

	@Autowired
	private ShardedJedisPool shardedJedisPool;

	/**
	 * 
	 * @param key 键
	 * @param value 值
	 * @return 状态回复
	 */
	public String set(String key, String value) {
		return this.execute(shardedJedis -> {
			return shardedJedis.set(key, value);
		});
	}

	/**
	 * 
	 * @param key 键
	 * @return 值
	 */
	public String get(String key) {
		return this.execute(shardedJedis -> {
			return shardedJedis.get(key);
		});
	}
	
	/**
	 * 
	 * @param key 键
	 * @param clazz 结果类型Class对象
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public <T> T get(String key, Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {
		String value = this.get(key);
		if (StringUtils.isNotBlank(value)) {
			return MAPPER.readValue(value, clazz);
		}
		return null;
	}

	/**
	 * 
	 * @param key 键
	 * @return
	 */
	public Long del(String key) {
		return this.execute(shardedJedis -> {
			return shardedJedis.del(key);
		});
	}

	/**
	 * 设置过期时间
	 * @param key 键
	 * @param seconds 生存时间(秒)
	 * @return
	 */
	public Long expire(String key, int seconds) {
		return this.execute(shardedJedis -> {
			return shardedJedis.expire(key, seconds);
		});
	}

	/**
	 * 
	 * @param key 键
	 * @param value 值
	 * @param seconds 生存时间(秒)
	 * @return 状态回复
	 */
	public String set(String key, String value, int seconds) {
		return this.execute(shardedJedis -> {
			String result = shardedJedis.set(key, value);
			shardedJedis.expire(key, seconds);
			return result;
		});
	}
	
	/**
	 * 
	 * @param key 键
	 * @param object 值对象
	 * @param seconds 生存时间(秒)
	 * @return 状态回复
	 * @throws JsonProcessingException 
	 */
	public String set(String key, Object object, int seconds) throws JsonProcessingException {
		String value = MAPPER.writeValueAsString(object);
		return this.set(key, value, seconds);
	}
	
	@FunctionalInterface
	private interface Function<T, E> {
		public T callback(E e);
	}
	
	private <T> T execute(Function<T, ShardedJedis> fun) {
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = shardedJedisPool.getResource();
			return fun.callback(shardedJedis);
		} finally {
			if (Objects.nonNull(shardedJedis)) {
				shardedJedis.close();
			}
		}
	}

}
