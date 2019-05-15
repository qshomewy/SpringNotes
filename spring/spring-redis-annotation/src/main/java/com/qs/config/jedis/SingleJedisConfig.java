package com.qs.config.jedis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.qs.config.RedisProperty;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 
 * @author : qshomewy
 * @description : Jedis 单机配置
 */
@Configuration
@ComponentScan(value = "com.qs.*")
public class SingleJedisConfig {

    @Bean
    public JedisPool jedisPool(RedisProperty property) {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(property.getMaxIdle());
        poolConfig.setMaxTotal(property.getMaxTotal());
        return new JedisPool(poolConfig, property.getHost(), property.getPort(), property.getTimeout());
    }

    @Bean(destroyMethod = "close")
    public Jedis jedis(JedisPool jedisPool) {
        return jedisPool.getResource();
    }
}
