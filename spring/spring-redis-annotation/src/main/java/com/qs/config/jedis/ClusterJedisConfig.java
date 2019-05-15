package com.qs.config.jedis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.qs.config.RedisProperty;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author : qshomewy
 * @description : Jedis 集群配置
 */
@Configuration
@ComponentScan(value = "com.qs.*")
public class ClusterJedisConfig {

    @Bean
    public JedisCluster jedisCluster(RedisProperty property) {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(property.getMaxIdle());
        poolConfig.setMaxTotal(property.getMaxTotal());
        Set<HostAndPort> nodes = new HashSet<HostAndPort>();
        nodes.add(new HostAndPort("127.0.0.1", 6379));
        nodes.add(new HostAndPort("127.0.0.1", 6380));
        return new JedisCluster(nodes, 2000);
    }
}
