package com.qs.jedis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import redis.clients.jedis.JedisCluster;

/**
 * 
 * @author : qshomewy
 * @description : redis 集群测试
 */
@RunWith(SpringRunner.class)
@ContextConfiguration({"classpath:springApplication.xml"})
public class JedisClusterSamples {

    @Autowired
    private JedisCluster jedisCluster;

    @Test
    public void Set() {
        jedisCluster.set("hello", "spring");
    }

    @Test
    public void Get() {
        String s = jedisCluster.get("hello");
        System.out.println(s);
    }

    @Test
    public void setEx() {
        String s = jedisCluster.setex("spring", 10, "我会在10秒后过期");
        System.out.println(s);
    }


}
