package com.god.example;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.Configuration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.xml.XmlConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExampleApplicationTests {

    @Test
    public void contextLoads() {
        // 缓存别名
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder().withCache("proconfigured",
                // 设置缓存堆容纳元素个数
                CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class, ResourcePoolsBuilder.heap(100)).build()).build(true); // 创建之后立即初始化
        // 从缓存中获取预定的缓存
        Cache<Long, String> proconfigured = cacheManager.getCache("proconfigured", Long.class, String.class);
        // 直接从缓存管理器创建一个新的缓存
        Cache<Long, String> myCache = cacheManager.createCache("myCache", CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class, ResourcePoolsBuilder.heap(100)).build());
        // 向缓存里添加缓存键值
        myCache.put(1L, "wuliao");
        // 从指定的缓存里获取键值
        String value = myCache.get(1L);
        cacheManager.close();

    }

    @Test
    public void testLoaderXml() {
        // 从配置文件创建配置对象
        Configuration configuration = new XmlConfiguration(getClass().getResource("/config/ehcache.xml"));
        // 创建缓存管理器
        CacheManager cacheManager = CacheManagerBuilder.newCacheManager(configuration);
        // 从缓存管理器中获取缓存
        Cache<Long, String> myCache1 = cacheManager.getCache("myCache1", Long.class, String.class);
        // 使用缓存
        myCache1.put(1L, "hello world!");
        myCache1.get(1L);
        // 清空缓存，关闭缓存管理器
        myCache1.clear();
        cacheManager.close();
    }

}
