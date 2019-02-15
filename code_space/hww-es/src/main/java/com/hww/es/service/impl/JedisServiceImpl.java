package com.hww.es.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hww.es.service.JedisService;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
  
/**  
 *@Title:  
 *@Description:对原Jedis对象进行封装，获得链接，选择DB关闭链接  
 *@Author:付轩 
 *@Since:2016年10月13日  
 *@Version:1.1.0  
 */
@Service
public class JedisServiceImpl implements JedisService {

	@Autowired
	private JedisPool jedisPool;

	@Override	
	public String set(String key, String value) {
		Jedis jedis = jedisPool.getResource();
		String result = jedis.set(key, value);
		jedis.close();
		return result;
	}
	

	@Override
	public String set(String key, String value,int seconds) {
		Jedis jedis = jedisPool.getResource();
		String result = jedis.set(key, value);
		jedis.expire(key, seconds);
		jedis.close();
		return result;
	}

	@Override
	public String get(String key) {
		Jedis jedis = jedisPool.getResource();
		String result = jedis.get(key);
		jedis.close();
		return result;
	}

	@Override
	public Boolean exists(String key) {
		Jedis jedis = jedisPool.getResource();
		Boolean result = jedis.exists(key);
		jedis.close();
		return result;
	}

	@Override
	public Long expire(String key, int seconds) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.expire(key, seconds);
		jedis.close();
		return result;
	}

	@Override
	public Long ttl(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.ttl(key);
		jedis.close();
		return result;
	}

	@Override
	public Long incr(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.incr(key);
		jedis.close();
		return result;
	}

	@Override
	public Long hset(String key, String field, String value) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.hset(key, field, value);
		jedis.close();
		return result;
	}

	@Override
	public String hget(String key, String field) {
		Jedis jedis = jedisPool.getResource();
		String result = jedis.hget(key, field);
		jedis.close();
		return result;
	}
	@Override
	public Long del(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.del(key);
		jedis.close();
		return result;
	}

	@Override
	public Long hdel(String key, String... field) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.hdel(key, field);
		jedis.close();
		return result;
	}
	@Override
	public List<String> lrange(String key,int start,int end) {
		Jedis jedis = jedisPool.getResource();
		List<String> list = jedis.lrange(key, start, end);
		jedis.close();
		return list;
	}
	@Override
	public Long rpush(String key,String... field) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.rpush(key, field);
		jedis.close();
		return result;
	}
}
