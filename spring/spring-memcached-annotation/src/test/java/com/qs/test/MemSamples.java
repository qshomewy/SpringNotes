package com.qs.test;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.qs.config.MemcacheConfig;

import java.util.concurrent.TimeoutException;

/**
 * 
 * @author : qshomewy
 * @description : Memcached 操作基本对象
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {MemcacheConfig.class})
public class MemSamples {

    @Autowired
    private MemcachedClient memcachedClient;

    @Test
    public void operate() throws InterruptedException, MemcachedException, TimeoutException {
        memcachedClient.set("hello", 0, "Hello,cluster xmemcached");
        String value = memcachedClient.get("hello");
        System.out.println("hello=" + value);
        memcachedClient.delete("hello");
        value = memcachedClient.get("hello");
        System.out.println("hello=" + value);
    }
}
