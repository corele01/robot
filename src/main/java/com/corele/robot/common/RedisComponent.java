package com.corele.robot.common;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author liujun
 */
public class RedisComponent<T> {

    private RedisTemplate<String,T> redisTemplate;

    public RedisComponent(RedisTemplate<String,T> redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    public T getKey(String key){
        T value = this.redisTemplate.opsForValue().get(key);
        return value;
    }

    public void set(String key,T value){
        this.redisTemplate.opsForValue().set(key,value);
    }

    public void set(String key, T value,long seconds){
        this.redisTemplate.opsForValue().set(key,value,seconds, TimeUnit.SECONDS);
    }
}
