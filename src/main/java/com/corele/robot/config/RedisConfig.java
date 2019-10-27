package com.corele.robot.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.corele.robot.common.RedisComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author liujun
 */
@Configuration
public class RedisConfig {

    @Autowired
    private RedisTemplate redisTemplate;

    @Bean
    public RedisComponent redisComponent(){
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new FastJsonRedisSerializer<>(Object.class));
        redisTemplate.setStringSerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return new RedisComponent(redisTemplate);
    }
}
