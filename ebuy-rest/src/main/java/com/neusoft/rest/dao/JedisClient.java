package com.neusoft.rest.dao;

public interface JedisClient {

	String get(String key);
	String set(String key, String value);
	String hget(String hkey, String key);
	long hset(String hkey, String key, String value);//hash
	long incr(String key);//自增
	long expire(String key, int second);//设置过期时间,单位秒
	long ttl(String key);//生存时间
	long del(String key);//删除
	long hdel(String hkey, String key);
	
}
